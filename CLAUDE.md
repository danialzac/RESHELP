# RES Exam Bank — Claude Code Project Memory

## What This Project Is
Singapore RES (Real Estate Salesperson) exam preparation platform.
Brand name: **RES Exam Bank** — never "RES Ace", "Kanze", or "RES Question Banks".

## Active Project Path
`/Users/danial/Desktop/RES-Exam-Bank/`

## Two-Agent Workflow
Codex (OpenAI) runs concurrently on the backend. **Always `Read` a file before editing it.**
See `AGENTS.md` for full ownership map and coordination rules.

## Claude Code Owns
- `frontend/src/` — all React pages, components, hooks, CSS
- `backend/src/main/java/com/resace/backend/DataInitializer.java` — seed data
- `backend/src/main/java/com/resace/backend/dto/` — DTOs (coordinate with Codex on schema changes)
- `postman/` — Postman collection

## Key Conventions
- **Seed questions:** use `saveQ(topic, paper, questionText, difficulty, examFrequency, explanation, plainEnglish, memoryRule, examTrap, options[][])`
- **Learning framework fields on every question:** `plainEnglish` (≤100 words, no jargon), `memoryRule` ("If you see X, think Y" — one line), `examTrap` (why students pick wrong answer), `examFrequency` (HIGH / MEDIUM / LOW)
- **Names in questions:** use interesting real-world names — Singapore celebrities (Dick Lee, Stefanie Sun, JJ Lin, Gurmit Singh, Kit Chan, Nathan Hartono), or internationally recognisable names. Avoid generic "John Smith" / "Mary Tan".
- **Frontend:** no component library — all styles in `frontend/src/styles.css`
- **Auth token key:** `res-exam-bank-auth` (localStorage)
- **API calls:** `frontend/src/api/client.js`

## Question Enrichment Workflow
When the user pastes raw questions:
1. Identify the core legal/regulatory concept
2. Write `plainEnglish` — explain like a smart 14-year-old, ≤100 words, zero jargon
3. Write `memoryRule` — one line: "If you see [trigger], think [answer]"
4. Write `examTrap` — the specific cognitive shortcut that trips students up
5. Assign `examFrequency` — HIGH if it's a classic exam staple, MEDIUM if occasional, LOW if rare
6. Swap any generic names for interesting Singapore/international names
7. Write the full `saveQ(...)` call and update DataInitializer.java directly

## Current Seed Data State
- 10 topics (5 × Paper 1, 5 × Paper 2), 50 questions total — all fully enriched
- DataInitializer is guarded: skips if `topicRepository.count() > 0`
- Do NOT add a second guard or change the guard logic without checking with user

## Frontend Page Map
| Route | Component | Purpose |
|---|---|---|
| `/` | redirect | → `/dashboard` |
| `/login` | LoginPage | JWT login |
| `/register` | RegisterPage | New account |
| `/dashboard` | DashboardPage | Paper 1/2 cards + stats |
| `/practice/:paper` | PracticeHubPage | Topic list + Quick/Mock modes |
| `/quiz` | QuizSessionPage | Active quiz (state via router) |
| `/results` | ResultsPage | Score + enriched answer review |
| `/progress` | ProgressPage | Per-paper + per-topic accuracy |

## ResultsPage Learning Panels (added — do not remove)
Each reviewed answer shows:
- `explanation` — full exam explanation
- `plainEnglish` — 💡 blue panel, always shown
- `memoryRule` — 🧠 purple panel, always shown  
- `examTrap` — ⚠️ amber panel, **only shown when the student got the answer wrong**
