# RES Exam Bank — Agent Coordination Guide

> **Read this file in full before touching any code.**
> This repo is actively worked on by two AI agents in parallel.
> Skipping this file causes merge conflicts, duplicated work, and broken builds.

---

## 🤝 Two-Agent Setup

| Agent | Tool | Primary Responsibility |
|---|---|---|
| **Claude Code** | Anthropic Claude | Frontend (React/CSS), seed data (DataInitializer), DTOs, learning-framework fields, Postman collection |
| **Codex** | OpenAI | Backend services, controllers, repositories, entity models, Spring Security / JWT config, build config |

Both agents are live at the same time. **Always assume the other agent may have edited a file since you last saw it.**

---

## ⚠️ Rule #1: Read Before You Write

Before editing **any** file:

```bash
# 1. See what the other agent may have changed
git status
git diff

# 2. Read the actual current file — never rely on memory
cat path/to/File.java
```

If the file contains content you did not write, **merge carefully** — do not overwrite.

---

## 📁 File Ownership Map

### Codex owns — edit freely
```
backend/src/main/java/com/resace/backend/
  ├── model/          ← all JPA entities
  ├── repository/     ← Spring Data repositories
  ├── service/        ← business logic services
  ├── controller/     ← REST controllers
  └── config/         ← SecurityConfig, JwtFilter, JwtUtil, CorsConfig

backend/src/main/resources/application.yml
backend/pom.xml
```

### Claude Code owns — coordinate before changing
```
frontend/src/                        ← ALL React pages, components, hooks, CSS
backend/src/main/java/com/resace/backend/DataInitializer.java   ← seed data
postman/                             ← API collection
```

### Shared — communicate before touching
```
backend/src/main/java/com/resace/backend/dto/    ← DTOs (both sides read these)
backend/src/main/java/com/resace/backend/model/Question.java    ← entity extended by both
README.md
```

When you must edit a shared file, leave a comment at the top of your change block:
```java
// CODEX EDIT 2025-XX-XX: added X field
```

---

## 🏗️ Project Overview

**Product:** RES Exam Bank — Singapore Real Estate Salesperson (RES) exam prep platform.
**Branding rule:** The product is called **"RES Exam Bank"** only. Never write "RES Ace", "Kanze", "RES Question Banks", or any other variant.

### Tech Stack
- **Backend:** Spring Boot 3, Java 21, Spring Security (stateless JWT), Spring Data JPA, Hibernate (ddl-auto: update), MySQL 8
- **Frontend:** React 18, Vite 6, React Router v7, plain CSS (no Tailwind, no component library)
- **Auth:** JWT via `jjwt 0.12.6` — Bearer token in `Authorization` header
- **DB name:** `res_exam_bank`
- **Backend port:** 8080 (env: `SERVER_PORT`)
- **Frontend port:** 5173 (Vite default)

### Package / artifact names
- Maven artifactId: `res-exam-bank-backend`
- Base Java package: `com.resace.backend`
- Spring Boot main class: `ResExamBankApplication`

---

## 📐 Domain Model (current state)

```
AppUser          — id, email, password (BCrypt), role (USER/ADMIN)
Topic            — id, name, slug (unique), paper (PAPER_1|PAPER_2), description
Question         — id, paper, topic(ManyToOne), questionText, explanation,
                   plainEnglish, memoryRule, examTrap, examFrequency,
                   difficulty, active, answerOptions(OneToMany CascadeAll EAGER)
AnswerOption     — id, question(ManyToOne), optionLabel, optionText, correct(boolean)
QuizAttempt      — id, user, paper, mode(TOPIC|RANDOM|MOCK), topic(nullable),
                   score, totalQuestions, correctAnswers, startedAt, completedAt
QuizAttemptAnswer — id, attempt, question, selectedOption(nullable), correct
```

**Enums:** `Paper { PAPER_1, PAPER_2 }` · `QuizMode { TOPIC, RANDOM, MOCK }`

---

## 🔌 API Endpoints (current state — do not duplicate)

### Auth (`/api/auth`)
- `POST /api/auth/register` → `{ email, password, firstName, lastName }` → JWT
- `POST /api/auth/login`    → `{ email, password }` → JWT

### Topics (`/api/topics`)
- `GET /api/topics?paper=PAPER_1` → `List<TopicDto>`

### Practice (`/api/practice`)
- `GET /api/practice/topics/{topicId}/questions` → `List<QuestionDto>` (all questions for topic)
- `GET /api/practice/random?paper=PAPER_1`       → 10 random questions (MySQL RAND())
- `GET /api/practice/mock?paper=PAPER_1`         → 30 random questions

### Attempts (`/api/attempts`)
- `POST /api/attempts` — submit quiz, returns `AttemptResultResponse`

### Progress (`/api/progress`)
- `GET /api/progress/summary` → `ProgressSummaryDto`

All endpoints except `/api/auth/**` require `Authorization: Bearer <jwt>`.

---

## 📦 DTOs — current shape (do not break these)

### `QuestionDto`
```java
record QuestionDto(Long id, String questionText, String difficulty,
    String examFrequency, List<AnswerOptionDto> answerOptions)
// AnswerOptionDto: Long id, String optionLabel, String optionText
// NOTE: correct field is intentionally NOT exposed to the client before submission
```

### `SubmitAttemptRequest`
```java
record SubmitAttemptRequest(String paper, String mode, Long topicId,
    List<AnswerSubmission> answers)
// AnswerSubmission: Long questionId, Long selectedOptionId (nullable = skipped)
```

### `AttemptResultResponse`
```java
record AttemptResultResponse(Long id, String paper, String mode,
    int score, int totalQuestions, int correctAnswers,
    List<ReviewedAnswer> reviewedAnswers)

record ReviewedAnswer(Long questionId, String questionText,
    Long selectedOptionId, Long correctOptionId, boolean correct,
    String explanation, String plainEnglish, String memoryRule, String examTrap,
    List<ReviewedOption> answerOptions)

record ReviewedOption(Long id, String optionLabel, String optionText, boolean correct)
```

### `ProgressSummaryDto`
```java
record ProgressSummaryDto(int totalSessions, int totalQuestionsAttempted,
    int totalCorrect, double overallAccuracy,
    PaperStats paper1Stats, PaperStats paper2Stats,
    List<TopicStat> topicStats)
```

---

## 🌱 Seed Data

`DataInitializer.java` seeds on first run (guarded by `topicRepository.count() > 0`).

- **10 topics total:** 5 for Paper 1, 5 for Paper 2
- **50 questions total:** 5 per topic, each enriched with `explanation`, `plainEnglish`, `memoryRule`, `examTrap`, `examFrequency`

**Codex must NOT re-seed or modify DataInitializer.java.** Claude Code owns that file.

---

## 🧑‍💻 Code Style Rules

### Java / Spring Boot
- Use **Lombok** everywhere: `@Builder`, `@Getter`, `@Setter`, `@RequiredArgsConstructor`, `@Slf4j`
- Use `@Builder.Default` for any `List` field on an entity
- Use **Java 21 records** for all DTOs — no classes, no getters
- `@Transactional` on service methods that write to DB
- No `System.out.println` — use `@Slf4j` + `log.info()`
- Field name for boolean on `AnswerOption` is `correct` (not `isCorrect`) — Lombok generates `isCorrect()` getter automatically
- `JoinColumn` names follow snake_case: `topic_id`, `attempt_id`, `question_id`, `selected_option_id`

### Repository conventions
- JPQL queries: use entity field names (camelCase), not column names
- Native queries: required only for `ORDER BY RAND()` — use `nativeQuery = true`
- Aggregate queries return `Long` (nullable) — always null-check with `!= null ? value : 0`

### Controller conventions
- All controllers annotated `@RestController @RequestMapping("/api/...") @RequiredArgsConstructor`
- Use `@AuthenticationPrincipal UserDetails` to extract the logged-in user's email
- Return `ResponseEntity<T>` only when you need to control status codes; otherwise return the type directly

### Frontend (Claude Code handles this — listed for awareness)
- React functional components only, no class components
- No component libraries — custom CSS only via `frontend/src/styles.css`
- Auth token stored in `localStorage` under key `res-exam-bank-auth`
- API base URL via `VITE_API_BASE_URL` env var (default: `http://localhost:8080`)

---

## 🚫 Things You Must Never Do

1. **Never rename** the product, package, or brand — it is `RES Exam Bank` / `com.resace.backend`
2. **Never remove** the `plainEnglish`, `memoryRule`, `examTrap`, `examFrequency` fields from `Question.java` — these are core to the learning framework
3. **Never expose** `AnswerOption.correct` in `QuestionDto` (pre-submission) — this would leak answers to the frontend
4. **Never change** `ddl-auto` to `create` or `create-drop` on a branch with real seed data
5. **Never add** `@JsonIgnore` to relationships without checking if the frontend depends on the field
6. **Never use** `FetchType.LAZY` on `Question.answerOptions` — it must stay `EAGER` (quiz sessions need options loaded in-request)
7. **Never commit** `.env` files, credentials, or `application-local.yml` with real passwords
8. **Never re-run** DataInitializer logic from Codex — duplicate seeds will corrupt the question bank

---

## ✅ Checklist Before Submitting a Change

- [ ] `git diff` reviewed — no accidental deletions of the other agent's work
- [ ] No hardcoded credentials or localhost URLs in production code paths
- [ ] New entity fields added to the relevant DTO if the frontend needs them
- [ ] `@Builder.Default` added to any new `List<>` field on an entity
- [ ] No new endpoints duplicate existing ones listed in this file
- [ ] Brand name is "RES Exam Bank" throughout — no "Kanze", "RES Ace", etc.
- [ ] If you touched `Question.java`, confirm `DataInitializer.java` still compiles (check the `saveQ` call signature)

---

## 🔄 How to Flag a Conflict

If you find a file in an unexpected state (content doesn't match what you expected), **do not overwrite**. Instead:

1. Read the file fully
2. Identify which lines are yours vs the other agent's
3. Merge the intent of both — keep all working logic
4. Add a comment: `// MERGED: Codex + Claude Code changes`

When in doubt, **preserve more code rather than less.**

---

## 💬 Questions?

Check `README.md` for local dev setup instructions.
Check `postman/RES-Exam-Bank-API.postman_collection.json` for all endpoint examples with sample payloads.
