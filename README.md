# RES Exam Bank

A web-based exam preparation platform for Singapore Real Estate Salesperson (RES) candidates.

Helps candidates practise Paper 1 and Paper 2 through structured question banks, topic-based sessions, quick practice, mock exams, answer explanations, and basic progress tracking.

---

## Stack

| Layer       | Technology                              |
|-------------|------------------------------------------|
| Frontend    | React 18 + Vite + React Router           |
| Backend     | Spring Boot 3 + Spring Security + JWT    |
| Database    | MySQL 8                                  |
| Auth        | JWT (email + password)                   |

---

## Project Structure

```
├── backend/          Spring Boot API
├── frontend/         React + Vite client
├── docs/             Business plan, MVP spec, execution roadmap
└── postman/          API collection for testing
```

---

## Running Locally

### Prerequisites

- Java 21+
- Maven 3.9+
- Node 18+
- MySQL 8 running locally for the default profile

### 1. Database

MySQL will auto-create the `res_exam_bank` database on first boot.

Default connection (override with env vars):

```
DB_URL=jdbc:mysql://localhost:3306/res_exam_bank?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Singapore
DB_USERNAME=root
DB_PASSWORD=password
JWT_SECRET=<base64-encoded-secret>
```

### 2. Backend

```bash
cd backend
mvn spring-boot:run
```

Runs on: `http://localhost:8080`

On first startup, the `DataInitializer` seeds placeholder topics and questions for both papers automatically.

Optional local smoke-test mode without MySQL:

```bash
cd backend
SPRING_PROFILES_ACTIVE=local SERVER_PORT=8081 mvn spring-boot:run
```

This uses an in-memory H2 database and is useful for quick verification.

### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

Runs on: `http://localhost:5174` (or next available port)

If your backend is running on a non-default port, point the frontend at it like this:

```bash
cd frontend
VITE_API_BASE_URL=http://127.0.0.1:8081/api npm run dev -- --port 5175
```

---

## MVP Features (current)

| Feature                     | Status       |
|-----------------------------|--------------|
| User registration & login   | Done         |
| JWT-authenticated sessions  | Done         |
| Paper 1 & Paper 2 structure | Done         |
| Topic listing               | Done         |
| Topic-based practice        | Done         |
| Quick random practice       | Done         |
| Timed mock exam mode        | Done         |
| Answer explanations         | Done         |
| Score summary               | Done         |
| Basic progress tracking     | Done         |
| Seed content (placeholder)  | Done         |

---

## API Endpoints

### Auth
```
POST /api/auth/register
POST /api/auth/login
```

### Topics
```
GET /api/topics
GET /api/topics?paper=PAPER_1
GET /api/topics?paper=PAPER_2
```

### Practice
```
GET /api/practice/topics/{topicId}/questions
GET /api/practice/random?paper=PAPER_1
GET /api/practice/mock?paper=PAPER_2
```

### Attempts
```
POST /api/attempts
```

### Progress
```
GET /api/progress/summary
```

All endpoints except `/api/auth/**` require a `Bearer <token>` header.

---

## Seed Content

On first startup, the backend seeds:

**Paper 1 topics:** Real Estate Legislation, Property Ownership & Tenure, Property Market Overview, Housing Policies & HDB, Property Taxation — 5 questions each

**Paper 2 topics:** Property Agency Law, Marketing & Property Listings, Negotiation & Sales Process, Ethics & Professional Conduct, Client Relationship Management — 5 questions each

This is clearly marked placeholder content. Replace with licensed exam-aligned questions before public launch.

---

## What Remains to Build

- Payment / access packages (90-day / 120-day)
- Admin panel for question management
- Password reset flow
- Practice streak tracking
- Mobile app (future)
- Advanced analytics

---

## Environment Variables

| Variable       | Default                                        |
|----------------|------------------------------------------------|
| `DB_URL`       | `jdbc:mysql://localhost:3306/res_exam_bank...` |
| `DB_USERNAME`  | `root`                                         |
| `DB_PASSWORD`  | `password`                                     |
| `JWT_SECRET`   | Dev fallback (change in production)            |
| `SERVER_PORT`  | `8080`                                         |
| `VITE_API_BASE_URL` | `http://localhost:8080/api`               |
