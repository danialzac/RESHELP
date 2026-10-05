/**
 * RES Exam Bank — Bulk Intake Script
 *
 * Reads .txt files from intake/raw/, parses raw questions,
 * and appends them to question-bank.json ready for enrichment.
 *
 * Run: node scripts/intake.mjs
 * Then: node scripts/validate-question-bank.mjs
 * Then: restart backend
 */

import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const ROOT = path.resolve(__dirname, '..');
const INTAKE_DIR = path.join(ROOT, 'intake', 'raw');
const BANK_PATH = path.join(ROOT, 'backend', 'src', 'main', 'resources', 'content', 'question-bank.json');
const DONE_DIR = path.join(ROOT, 'intake', 'done');

// ─── topic slug lookup ────────────────────────────────────────────────────────
const TOPIC_MAP = {
  // paper 1
  legislation:  { slug: 'p1-legislation', name: 'Real Estate Legislation',     paper: 'PAPER_1', description: 'Real estate laws and regulations in Singapore.' },
  ownership:    { slug: 'p1-ownership',   name: 'Property Ownership & Tenure',  paper: 'PAPER_1', description: 'Types of property ownership, tenure, and title in Singapore.' },
  market:       { slug: 'p1-market',      name: 'Property Market Overview',     paper: 'PAPER_1', description: 'Property market dynamics, indicators, and investment analysis.' },
  housing:      { slug: 'p1-housing',     name: 'Housing Policies & HDB',       paper: 'PAPER_1', description: 'Government housing policies, HDB schemes, and eligibility rules.' },
  tax:          { slug: 'p1-tax',         name: 'Property Taxation',            paper: 'PAPER_1', description: 'Stamp duties, property tax, GST implications for real estate.' },
  // paper 2
  agency:       { slug: 'p2-agency-law',  name: 'Property Agency Law',          paper: 'PAPER_2', description: 'Agency relationships, duties, and legal obligations.' },
  marketing:    { slug: 'p2-marketing',   name: 'Marketing & Property Listings', paper: 'PAPER_2', description: 'Property marketing regulations, listing agreements, and advertising rules.' },
  negotiation:  { slug: 'p2-negotiation', name: 'Negotiation & Sales Process',  paper: 'PAPER_2', description: 'Negotiation techniques and property transaction processes.' },
  ethics:       { slug: 'p2-ethics',      name: 'Ethics & Professional Conduct', paper: 'PAPER_2', description: 'CEHA code of ethics and professional standards.' },
  client:       { slug: 'p2-client',      name: 'Client Relationship Management', paper: 'PAPER_2', description: 'Managing client relationships and after-sales service.' },
};

// ─── slugify a short concept label from question text ─────────────────────────
function slugify(text) {
  return text
    .toLowerCase()
    .replace(/[^a-z0-9 ]/g, '')
    .trim()
    .split(/\s+/)
    .slice(0, 5)
    .join('-');
}

// ─── parse one question block ─────────────────────────────────────────────────
// Expected format (see intake/HOW-TO-USE.md):
//
//   PAPER: 1
//   TOPIC: housing
//   DIFFICULTY: HARD
//   SOURCE: Mock Paper 3 Q5
//
//   Question text goes here, can be
//   multiple lines.
//
//   A) Option text
//   B) Option text *
//   C) Option text
//   D) Option text
//
// The * marks the correct answer. One answer must be marked.
function parseBlock(block, fileLabel, blockIndex) {
  const lines = block.split('\n').map(l => l.trim()).filter(Boolean);

  const meta = {};
  const optionLines = [];
  const questionLines = [];
  let inQuestion = false;

  for (const line of lines) {
    const metaMatch = line.match(/^(PAPER|TOPIC|DIFFICULTY|SOURCE|SUBTOPIC|PREMIUM|FREQUENCY):\s*(.+)$/i);
    if (metaMatch) {
      meta[metaMatch[1].toUpperCase()] = metaMatch[2].trim();
      continue;
    }

    // ! some Nomad questions punya 5-6 options (A-F), not just A-D
    const optionMatch = line.match(/^([A-F])[).]\s*(.+)$/);
    if (optionMatch) {
      inQuestion = false;
      optionLines.push({ label: optionMatch[1], text: optionMatch[2].trim() });
      continue;
    }

    if (!inQuestion && Object.keys(meta).length > 0) {
      inQuestion = true;
    }

    if (inQuestion) {
      questionLines.push(line);
    }
  }

  const errors = [];
  if (!meta.PAPER)   errors.push('missing PAPER');
  if (!meta.TOPIC)   errors.push('missing TOPIC');
  if (optionLines.length < 2) errors.push('fewer than 2 answer options');
  if (questionLines.length === 0) errors.push('no question text');

  const correctCount = optionLines.filter(o => o.text.endsWith('*')).length;
  if (correctCount !== 1) errors.push(`exactly 1 option must end with * (found ${correctCount})`);

  if (errors.length) {
    console.warn(`  ⚠ ${fileLabel} block ${blockIndex + 1} skipped: ${errors.join(', ')}`);
    return null;
  }

  const topicKey = meta.TOPIC.toLowerCase().replace(/-law$/, '').replace(/^p[12]-/, '');
  const topicData = TOPIC_MAP[topicKey];
  if (!topicData) {
    console.warn(`  ⚠ ${fileLabel} block ${blockIndex + 1} skipped: unknown TOPIC "${meta.TOPIC}". Valid: ${Object.keys(TOPIC_MAP).join(', ')}`);
    return null;
  }

  const paperNum = meta.PAPER.trim().replace('PAPER_', '').replace('PAPER ', '');
  const paper = `PAPER_${paperNum}`;

  const questionText = questionLines.join(' ');
  // ! contentKey must be unique — first 5 words alone can collide
  // ! (contoh: two "what is the minimum plot..." questions). So kita tambah
  // ! the source question number as a suffix when SOURCE has one.
  const conceptSlug = slugify(questionText);
  const sourceNum = (meta.SOURCE || '').match(/[QP](\d+)\s*$/i);
  const contentKey = sourceNum
    ? `${topicData.slug}-${conceptSlug}-q${sourceNum[1]}`
    : `${topicData.slug}-${conceptSlug}`;

  const answerOptions = optionLines.map(o => ({
    optionLabel: o.label,
    optionText: o.text.replace(/\s*\*$/, '').trim(),
    correct: o.text.endsWith('*'),
  }));

  return {
    contentKey,
    paper,
    topic: {
      slug: topicData.slug,
      name: topicData.name,
      description: topicData.description,
    },
    sourceReference: meta.SOURCE || `Intake — ${fileLabel}`,
    subtopic:        meta.SUBTOPIC || '',
    principleTested: '',
    difficulty:      (meta.DIFFICULTY || 'MEDIUM').toUpperCase(),
    examFrequency:   (meta.FREQUENCY  || 'MEDIUM').toUpperCase(),
    questionText,
    explanation:     '',
    plainEnglish:    '',
    cavemanVersion:  '',
    minimalVersion:  '',
    memoryRule:      '',
    examShortcut:    '',
    examTrap:        '',
    interactiveFormat: '',
    premium:  meta.PREMIUM ? meta.PREMIUM.toLowerCase() === 'true' : false,
    active:   true,
    answerOptions,
    _needsEnrichment: true,  // flag for Claude Code to enrich later
  };
}

// ─── main ──────────────────────────────────────────────────────────────────────
function main() {
  if (!fs.existsSync(INTAKE_DIR)) {
    console.error(`intake/raw/ folder not found at ${INTAKE_DIR}`);
    process.exit(1);
  }

  fs.mkdirSync(DONE_DIR, { recursive: true });

  const txtFiles = fs.readdirSync(INTAKE_DIR).filter(f => f.endsWith('.txt'));
  if (txtFiles.length === 0) {
    console.log('No .txt files found in intake/raw/ — nothing to process.');
    return;
  }

  const bank = JSON.parse(fs.readFileSync(BANK_PATH, 'utf8'));
  const existingKeys = new Set(bank.questions.map(q => q.contentKey));

  let added = 0;
  let skipped = 0;
  let errors = 0;

  for (const file of txtFiles) {
    const filePath = path.join(INTAKE_DIR, file);
    const content = fs.readFileSync(filePath, 'utf8');
    const blocks = content.split(/^===+$/m).map(b => b.trim()).filter(Boolean);

    console.log(`\nProcessing ${file} (${blocks.length} question block(s))...`);

    const parsed = blocks.map((block, i) => parseBlock(block, file, i)).filter(Boolean);

    for (const q of parsed) {
      if (existingKeys.has(q.contentKey)) {
        console.log(`  → skip (already exists): ${q.contentKey}`);
        skipped++;
        continue;
      }
      bank.questions.push(q);
      existingKeys.add(q.contentKey);
      console.log(`  + added: ${q.contentKey}`);
      added++;
    }

    errors += blocks.length - parsed.length;

    // move processed file to done/
    fs.renameSync(filePath, path.join(DONE_DIR, file));
    console.log(`  → moved to intake/done/${file}`);
  }

  bank.generatedAt = new Date().toISOString().split('T')[0];
  fs.writeFileSync(BANK_PATH, JSON.stringify(bank, null, 2));

  console.log(`\n────────────────────────────────`);
  console.log(`Added:   ${added} question(s)`);
  console.log(`Skipped: ${skipped} (already in bank)`);
  console.log(`Failed:  ${errors} block(s) — check warnings above`);
  console.log(`\nNext steps:`);
  console.log(`  1. node scripts/validate-question-bank.mjs`);
  console.log(`  2. Ask Claude Code to enrich questions where _needsEnrichment: true`);
  console.log(`  3. Restart backend: cd backend && mvn spring-boot:run`);
}

main();
