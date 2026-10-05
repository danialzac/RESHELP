# RES Exam Bank — Content Pipeline

This pipeline is here to make sure study work does not disappear into chat history.

## Source Of Truth

The canonical structured content file is:

`/Users/danial/Desktop/RES-Exam-Bank/backend/src/main/resources/content/question-bank.json`

That file is the closest thing we have right now to "questions automatically go into the product."

## How The Pipeline Works

1. A question arrives from screenshot or pasted text.
2. It gets transformed into:
   - tested concept
   - noise reduction
   - plain English
   - caveman version
   - minimal exam version
   - explanation
   - trap
   - memory rule
   - exam shortcut
   - interactive format
   - premium/free placement
3. The transformed content is saved into `question-bank.json`.
4. The backend importer upserts those entries into the database on app startup.
5. The app can then serve those richer learning fields during practice and review.

## Why This Matters

This means the user's study process is now producing:

- revision help right now
- app seed content later
- subscription-grade premium material over time

## Current Limitation

Questions are not OCR-imported from screenshots automatically yet.

For now, the "automatic as possible" workflow is:

- user sends screenshot
- assistant transforms it
- assistant appends it into the JSON content bank
- backend imports it into the product

That is not zero-touch automation, but it is no longer throwaway work.

## Question Entry Shape

Each question in `question-bank.json` should include:

- `contentKey`
- `paper`
- `topic`
- `sourceReference`
- `subtopic`
- `principleTested`
- `difficulty`
- `examFrequency`
- `questionText`
- `explanation`
- `plainEnglish`
- `cavemanVersion`
- `minimalVersion`
- `memoryRule`
- `examShortcut`
- `examTrap`
- `interactiveFormat`
- `premium`
- `active`
- `answerOptions`

## Premium Rule Of Thumb

Free:
- a few sample questions
- a light taste of the learning style

Premium:
- high-frequency exam concepts
- formula traps
- stronger drills
- richer memory systems
- concepts with multiple useful variations

## Validation

Use:

```bash
node /Users/danial/Desktop/RES-Exam-Bank/scripts/validate-question-bank.mjs
```

This checks for:
- duplicate `contentKey`
- missing correct options
- invalid paper values
- missing required fields

## Next Automation Upgrade

The next upgrade after this pipeline is:

1. add admin upload/import flow
2. parse AI-transformed content into the database without touching files manually
3. optionally support image-to-structured-question intake
