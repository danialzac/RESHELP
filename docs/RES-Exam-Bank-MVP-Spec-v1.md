# RES Exam Bank

## MVP Spec v1

## 1. Purpose

This document defines the first buildable MVP for RES Exam Bank.

It exists to remove ambiguity before implementation.

The MVP goal is:

`Help Singapore RES candidates practice Paper 1 and Paper 2 through structured question banks, quiz sessions, mock exams, explanations, and basic progress tracking.`

This is not a generic LMS, not a tuition replacement platform, and not a large community product yet.

It is a focused revision tool.

---

## 2. Product Name

Use this naming consistently:

- Product name: `RES Exam Bank`

Avoid older names:

- RES Ace
- RES Question Banks

---

## 3. Intended User

Primary user:

- a Singapore RES exam candidate who wants structured self-practice

Priority user types:

- first-time candidates
- working adults
- repeat candidates

User goal:

- quickly practice relevant questions
- understand mistakes
- improve by topic
- feel more prepared for the exam

---

## 4. MVP Success Criteria

The MVP is successful if a user can:

1. sign up and log in
2. choose Paper 1 or Paper 2
3. choose a topic or practice mode
4. answer questions
5. submit and see score + explanations
6. view simple progress data
7. return for another session

Business-level MVP success means:

- the app is usable
- the flows are understandable
- the question format is credible
- the product can be shown to testers and early users

---

## 5. MVP Features

### Included

- user registration
- user login
- JWT-based authenticated session
- Paper 1 question bank
- Paper 2 question bank
- topic listing
- topic-based quiz mode
- random practice mode
- timed mock exam mode
- answer explanations
- score summary
- basic progress tracking
- admin-side seed content support

### Excluded

- payment
- subscriptions
- AI explanations
- flashcards
- leaderboard
- discussion/forum
- tutor marketplace
- agency dashboards
- push notifications
- native apps
- advanced analytics

---

## 6. User Flows

### Flow 1: First-Time User

1. User lands on login/register page
2. User creates account
3. User reaches dashboard
4. User sees Paper 1 and Paper 2 choices
5. User chooses a paper
6. User chooses a topic or mock exam
7. User completes a quiz
8. User sees score and explanations
9. User sees a simple prompt to continue revision

### Flow 2: Returning User

1. User logs in
2. User lands on dashboard
3. User sees progress overview
4. User resumes a topic or starts another practice session

### Flow 3: Mock Exam Practice

1. User selects Paper 1 or Paper 2
2. User starts mock exam
3. User answers timed set of questions
4. User submits
5. User sees final score, wrong answers, and explanations

---

## 7. Core Pages

### 1. Login Page

Purpose:

- authenticate existing users

Required elements:

- email
- password
- login button
- link to register

### 2. Register Page

Purpose:

- create new account

Required elements:

- full name
- email
- password
- register button
- link to login

### 3. Dashboard

Purpose:

- main entry point after login

Required elements:

- welcome header
- Paper 1 card
- Paper 2 card
- quick progress summary
- continue practice CTA
- recent activity or latest result block

### 4. Paper Selection / Practice Hub

Purpose:

- allow user to choose practice path

Required elements:

- paper title
- topic list
- topic-based practice option
- random practice option
- mock exam option

### 5. Quiz Session Page

Purpose:

- active question answering flow

Required elements:

- question text
- answer options
- next button
- progress indicator
- optional timer for mock exam mode

### 6. Results Page

Purpose:

- show session outcome

Required elements:

- score
- correct / incorrect count
- topic or paper label
- answer review
- explanation for each question
- retry or continue CTA

### 7. Progress Page

Purpose:

- show simple revision performance

Required elements:

- total questions attempted
- average score
- Paper 1 vs Paper 2 performance
- topic-level accuracy summary

---

## 8. Frontend UI Direction

The UI should feel:

- clean
- credible
- calm
- slightly energizing
- easy to use on mobile

### Avoid

- cartoonish visuals
- loud gaming UI
- clutter
- excessive motion
- too many controls on one screen

### Good UX Signals

- clear paper separation
- obvious next actions
- visible score feedback
- readable explanations
- short path from login to practice

---

## 9. Backend Domain Model

Use practical entities only.

### User

Fields:

- id
- name
- email
- password hash
- role
- createdAt

### Topic

Fields:

- id
- name
- slug
- paper
- description

### Question

Fields:

- id
- paper
- topicId
- questionText
- explanation
- difficulty
- active

### AnswerOption

Fields:

- id
- questionId
- optionLabel
- optionText
- isCorrect

### QuizAttempt

Fields:

- id
- userId
- paper
- mode
- topicId nullable
- score
- totalQuestions
- correctAnswers
- startedAt
- completedAt

### QuizAttemptAnswer

Fields:

- id
- attemptId
- questionId
- selectedOptionId
- isCorrect

---

## 10. MVP API Endpoints

### Auth

- `POST /api/auth/register`
- `POST /api/auth/login`

### Topics

- `GET /api/topics`
- `GET /api/topics?paper=PAPER_1`
- `GET /api/topics?paper=PAPER_2`

### Questions / Practice

- `GET /api/practice/papers`
- `GET /api/practice/topics/{topicId}/questions`
- `GET /api/practice/random?paper=PAPER_1`
- `GET /api/practice/mock?paper=PAPER_2`

### Attempt Submission

- `POST /api/attempts`

Request should include:

- paper
- mode
- topicId if relevant
- answers

Response should include:

- score
- correctAnswers
- totalQuestions
- reviewed answers
- explanations

### Progress

- `GET /api/progress/summary`
- `GET /api/progress/topics`

---

## 11. Quiz Modes

### Topic Practice

- filtered by a single topic
- untimed
- smaller set of questions

### Random Practice

- random questions by paper
- untimed

### Mock Exam

- larger fixed-size set
- timed
- exam-like flow

For MVP, mock exam can be simplified and need not perfectly simulate the official exam.

---

## 12. Progress Tracking Rules

For MVP, track only useful essentials:

- total attempts
- questions attempted
- questions correct
- score by paper
- score by topic

Do not build complex analytics yet.

---

## 13. Seed Data Rules

Because real licensed exam content may not yet be ready, seed with clearly marked placeholder content.

### Placeholder Content Requirements

- use realistic RES-style question formatting
- label clearly as sample/demo content internally if needed
- cover both Paper 1 and Paper 2
- include explanations
- include topic assignment

### Minimum Seed Content for MVP

- 3 to 5 topics for Paper 1
- 3 to 5 topics for Paper 2
- at least 5 questions per topic

This is enough to test user flow without pretending the content library is complete.

---

## 14. Admin Requirements

MVP admin does not need a polished admin dashboard.

Minimum acceptable admin capability:

- easy seed insertion
- easy database editing
- clear schema

If an internal admin UI is added, keep it simple.

---

## 15. Authentication Rules

Use:

- Spring Security
- JWT authentication

For MVP:

- normal email/password auth is enough
- no social login
- no password reset flow required initially unless fast to add

---

## 16. Non-Functional Requirements

### Performance

- pages should feel fast
- practice flow should not feel heavy

### Mobile Responsiveness

- all primary flows must work on mobile

### Clarity

- product naming must remain consistent
- no leftover property-listing language

### Maintainability

- clean folder structure
- readable entities and DTOs
- easy to expand later

---

## 17. Out-of-Scope Warnings

If implementation drifts into these, stop and reassess:

- full LMS features
- excessive gamification
- advanced recommendation engines
- community forum systems
- multi-tenant agency systems
- AI-heavy tooling

The first product is a revision engine, not a whole education ecosystem.

---

## 18. MVP Build Priority

Build in this order:

1. auth
2. topic and question schema
3. question retrieval
4. quiz session flow
5. attempt submission
6. results page
7. progress summary
8. seed content improvements

---

## 19. Definition of MVP Done

The MVP is “done enough” when:

- a user can register and log in
- a user can choose Paper 1 or Paper 2
- a user can complete a topic quiz
- a user can complete a mock exam
- explanations are visible after submission
- basic progress can be viewed
- the app can be shown to testers with confidence

---

## 20. Final Rule

When in doubt, prioritize:

- clarity
- question quality
- working revision flow
- speed to usable product

The MVP must feel like a real exam-practice tool, not a repurposed CRUD demo.
