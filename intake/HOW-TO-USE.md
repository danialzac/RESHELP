# RES Exam Bank — Bulk Question Intake

Drop your notes and practice questions here as plain `.txt` files.
Run one command and they appear in the app.

---

## Format

Each `.txt` file can contain **one or more questions**.  
Separate multiple questions with a line of `===`.

```
PAPER: 1
TOPIC: housing
DIFFICULTY: HARD
SOURCE: Mock Paper 3 Q5

Cheryl is a SC aged 34. She and her SPR husband Axel (aged 44)
are buying a resale HDB flat with 40 years of lease remaining.
Their combined income is $8,000/month. What is the maximum HDB loan?

A) $234,146
B) $226,829
C) $219,512 *
D) $243,694

===

PAPER: 1
TOPIC: tax
DIFFICULTY: MEDIUM
SOURCE: CEA Notes Page 12

A buyer purchases a second residential property. Which stamp duty applies?

A) BSD only
B) ABSD only
C) Both BSD and ABSD *
D) Neither
```

**Rules:**
- Mark the correct answer with `*` at the end of the option line
- `PAPER:` is 1 or 2
- `TOPIC:` must be one of the valid topics below
- `DIFFICULTY:` is EASY, MEDIUM, or HARD
- `SOURCE:` is optional (e.g. "Mock Paper 2 Q3", "CEA Notes Ch4")
- `SUBTOPIC:` optional
- `FREQUENCY:` HIGH, MEDIUM, or LOW (optional, defaults to MEDIUM)
- `PREMIUM:` true or false (optional, defaults to false)

---

## Valid Topics

| TOPIC value   | Maps to                        | Paper |
|---------------|-------------------------------|-------|
| `legislation` | Real Estate Legislation        | 1     |
| `ownership`   | Property Ownership & Tenure    | 1     |
| `market`      | Property Market Overview       | 1     |
| `housing`     | Housing Policies & HDB         | 1     |
| `tax`         | Property Taxation              | 1     |
| `agency`      | Property Agency Law            | 2     |
| `marketing`   | Marketing & Property Listings  | 2     |
| `negotiation` | Negotiation & Sales Process    | 2     |
| `ethics`      | Ethics & Professional Conduct  | 2     |
| `client`      | Client Relationship Management | 2     |

---

## Steps

```bash
# 1. Drop your .txt files into intake/raw/
# 2. Run the intake script
node scripts/intake.mjs

# 3. Validate the output
node scripts/validate-question-bank.mjs

# 4. Tell Claude Code to enrich the questions
#    (it will fill in cavemanVersion, examShortcut, memoryRule etc.)

# 5. Restart the backend
cd backend && mvn spring-boot:run
```

After intake, your files move to `intake/done/` automatically.

---

## Tips

- Paste an **entire PDF page** of questions into one `.txt` file — the script handles multiple questions
- Name files by source: `csa-mock-paper-1.txt`, `cea-notes-ch4.txt`
- You don't need perfect formatting — as long as `PAPER:`, `TOPIC:`, question text, and options with `*` are there, it works
- Questions with blank learning fields (cavemanVersion etc.) still appear in the app — Claude Code enriches them in batch
