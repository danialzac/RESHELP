import fs from 'node:fs';
import path from 'node:path';

const filePath = path.resolve('/Users/danial/Desktop/RES-Exam-Bank/backend/src/main/resources/content/question-bank.json');

const payload = JSON.parse(fs.readFileSync(filePath, 'utf8'));
const errors = [];
const seenKeys = new Set();
const validPapers = new Set(['PAPER_1', 'PAPER_2']);

if (!Array.isArray(payload.questions)) {
  errors.push('`questions` must be an array.');
} else {
  payload.questions.forEach((question, index) => {
    const label = `${question.contentKey || `question[${index}]`}`;

    for (const field of ['contentKey', 'paper', 'questionText', 'difficulty']) {
      if (!question[field]) {
        errors.push(`${label}: missing required field \`${field}\`.`);
      }
    }

    if (question.contentKey) {
      if (seenKeys.has(question.contentKey)) {
        errors.push(`${label}: duplicate contentKey.`);
      }
      seenKeys.add(question.contentKey);
    }

    if (question.paper && !validPapers.has(question.paper)) {
      errors.push(`${label}: invalid paper value \`${question.paper}\`.`);
    }

    if (!question.topic?.slug || !question.topic?.name) {
      errors.push(`${label}: topic.slug and topic.name are required.`);
    }

    if (!Array.isArray(question.answerOptions) || question.answerOptions.length < 2) {
      errors.push(`${label}: must have at least 2 answer options.`);
      return;
    }

    const correctCount = question.answerOptions.filter(option => option.correct === true).length;
    if (correctCount !== 1) {
      errors.push(`${label}: must have exactly 1 correct answer option (found ${correctCount}).`);
    }

    question.answerOptions.forEach((option, optionIndex) => {
      if (!option.optionLabel || !option.optionText) {
        errors.push(`${label}: answer option ${optionIndex + 1} is missing optionLabel or optionText.`);
      }
    });
  });
}

if (errors.length) {
  console.error('Question bank validation failed:\n');
  for (const error of errors) {
    console.error(`- ${error}`);
  }
  process.exit(1);
}

console.log(`Question bank validation passed: ${payload.questions.length} question(s).`);
