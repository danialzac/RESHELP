# RES Exam Bank

## Execution Roadmap v1

## 1. Purpose

This roadmap converts the business plan and MVP spec into a practical build sequence.

The goal is to keep execution focused and prevent project drift.

---

## 2. Core Objective

Ship the first usable MVP of RES Exam Bank:

- auth
- Paper 1 and Paper 2 structure
- topic-based practice
- mock exam mode
- explanations
- basic progress tracking

---

## 3. Execution Principles

- Keep it KISS
- Prefer shipping over polishing
- Build only what supports the MVP
- Do not mix unrelated portfolio/demo goals into the product
- Use one product name consistently: `RES Exam Bank`

---

## 4. Workstreams

There are 5 main workstreams:

- product cleanup
- backend foundation
- frontend foundation
- content seeding
- testing and launch prep

---

## 5. Phase 1: Product Cleanup

### Goal

Turn the current scaffold into a clean RES Exam Bank codebase.

### Tasks

- remove or replace leftover property-listing language
- remove starter CRUD assumptions tied to listings
- confirm naming consistency
- align README with current direction
- preserve only reusable auth and app-shell structure

### Output

- clean product naming
- reduced confusion
- stable starting point for real feature work

---

## 6. Phase 2: Backend Foundation

### Goal

Replace generic property CRUD domain with exam-prep domain.

### Tasks

- define entities:
  - User
  - Topic
  - Question
  - AnswerOption
  - QuizAttempt
  - QuizAttemptAnswer
- create repositories
- create DTOs
- create services
- create REST endpoints
- keep JWT auth working

### Priority Order

1. auth stays working
2. topics endpoint
3. question retrieval endpoints
4. attempt submission
5. progress summary

### Output

- working backend API for the MVP

---

## 7. Phase 3: Frontend Foundation

### Goal

Convert the UI into a usable exam-practice product.

### Tasks

- keep login/register flows
- replace generic dashboard content
- add Paper 1 / Paper 2 selection
- add topic practice view
- add quiz session flow
- add results page
- add progress page

### UI Rules

- simple
- mobile-friendly
- serious but approachable
- visible actions and progress

### Output

- working frontend for practice and review

---

## 8. Phase 4: Seed Content

### Goal

Load enough realistic placeholder content to test the product properly.

### Tasks

- define Paper 1 topics
- define Paper 2 topics
- create sample questions
- create answer choices
- create explanations

### Minimum Target

- 6 to 10 total topics
- 30 to 50 sample questions total

This is enough for meaningful internal testing.

### Output

- credible seed content for beta testing

---

## 9. Phase 5: Progress and Review

### Goal

Make user improvement visible.

### Tasks

- record attempts
- calculate total score
- calculate topic accuracy
- calculate paper-level summary
- show recent attempt history if simple

### Output

- basic but useful revision feedback

---

## 10. Phase 6: Internal QA

### Goal

Make the MVP trustworthy before showing it to testers.

### Tasks

- test register/login
- test protected routes
- test question retrieval
- test quiz submission
- test score calculation
- test results explanations
- test progress summary
- test mobile layout

### Output

- fewer obvious broken flows

---

## 11. Phase 7: Beta Launch Prep

### Goal

Prepare the app to be shown to early users.

### Tasks

- confirm product naming
- improve landing/dashboard copy
- make seed content feel coherent
- add clear empty states
- add support contact path if needed
- prepare short onboarding message

### Output

- tester-ready MVP

---

## 12. Suggested 4-Week Build Sequence

### Week 1

- cleanup old domain language
- define backend schema
- implement Topic and Question model
- implement auth review

### Week 2

- implement practice endpoints
- implement quiz attempt submission
- create frontend Paper 1 / Paper 2 flow

### Week 3

- implement results and explanations
- implement progress summary
- seed realistic placeholder questions

### Week 4

- QA and polish
- mobile fixes
- beta prep
- documentation update

---

## 13. Deliverables by End of MVP

The build phase should produce:

- a working frontend app
- a working backend API
- seeded question data
- usable practice flow
- mock exam flow
- results + explanations
- simple progress tracking
- updated docs

---

## 14. Risks During Execution

### Risk: Overbuilding

Mitigation:

- do not add payment yet
- do not add AI yet
- do not add advanced gamification yet

### Risk: Confused naming

Mitigation:

- keep `RES Exam Bank` everywhere

### Risk: Bad content slowing product credibility

Mitigation:

- prioritize smaller, cleaner question sets first

### Risk: Time lost in architecture

Mitigation:

- choose practical solutions
- avoid unnecessary abstractions

---

## 15. Immediate Next Build Step

The single best next implementation step is:

`Replace the property-listing domain in the backend and dashboard with the exam-practice domain.`

That change unlocks almost everything else.

---

## 16. Founder Operating Reminder

At this stage, the product does not need to look big.

It needs to:

- work
- make sense
- feel useful
- support revision

The goal is not to impress with scope.
The goal is to create something learners would actually practice with.
