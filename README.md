# 🎓 ILEIC — School Management System

A full-stack management system for OFPPT-style vocational training institutes, built to
handle multiple training centres under a single administration. **React** on the
frontend, **Spring Boot** on the backend.

---

## ✨ Features

- **🏢 Multi-Centre Administration** — One admin oversees every centre; each centre has
  exactly one secretary
- **👥 Stagiaire (Student) Management** — Registration, filière assignment, profiles,
  status tracking
- **👨‍🏫 Formateur (Trainer) Management** — Trainers can be assigned across multiple
  centres and courses
- **📚 Programs, Filières & Cours** — Structured academic hierarchy: program → filière →
  cours
- **📋 Attendance Tracking** — Absence recording with automatic at-risk flagging
  (10+ unjustified absences)
- **📊 Grades & Exams (Contrôles / Notes)** — Grade entry with 0–20 range validation
- **💳 Payments** — Monthly payment tracking per stagiaire, with duplicate-payment
  protection
- **📄 Documents** — Upload and approve/reject stagiaire documents
- **🗓️ Schedule (Emploi du Temps)** — Weekly timetable per filière, centre, and
  formateur
- **📈 Admin Dashboard** — System-wide stats, per-centre summaries, at-risk stagiaires,
  payment/absence overviews
- **📝 Admin Audit Log** — Every administrative action is recorded with who, what, and
  when
- **🔐 Secure Authentication** — Role-based access (`ADMIN`, `SECRETAIRE`, `FORMATEUR`,
  `STAGIAIRE`) with short-lived access tokens + long-lived, revocable refresh tokens

---

## 📁 Project Structure

```
/
├── frontend/                  # React + Vite + Tailwind CSS
│   └── src/
│       ├── components/
│       ├── pages/
│       ├── routes/
│       └── App.jsx
│
└── backend/                   # Spring Boot + Java 17 + Maven
    └── src/main/java/com/ProjetILEIC/ILIEC/
        ├── controller/        # REST endpoints
        ├── service/           # Business logic
        ├── entity/            # JPA entities
        ├── repository/        # Spring Data repositories
        ├── dto/                # Request/response DTOs (+ dto/auth, dto/dashboard)
        ├── security/          # JWT, filters, security config
        ├── exception/         # Global exception handling
        └── IliecApplication.java
```

---

## 🛠️ Tech Stack

| Layer      | Technology                                              |
|------------|-----------------------------------------------------------|
| Frontend   | React, Vite, Tailwind CSS, Lucide-React                   |
| Backend    | Spring Boot 3.2.5, Spring Security, Spring Data JPA, Maven |
| Auth       | JWT (access token) + server-side refresh tokens            |
| Database   | PostgreSQL (hosted on NeonDB)                              |
| API Docs   | springdoc-openapi (Swagger UI)                             |

---

## 🔌 API Documentation (Swagger)

The backend exposes live, always-accurate API documentation via **Springdoc OpenAPI** —
this is the source of truth for exact request/response shapes, not a hand-written table
(those go stale fast; this project has learned that the hard way).

1. Start the backend.
2. Open: **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

**Using it:**
- Click the **Authorize** padlock (top right) and paste `Bearer <your_access_token>` to
  call protected endpoints.
- Scroll to **Schemas** at the bottom to see the exact JSON shape for any
  `*RequestDTO`/`*DTO`.

### Resource groups (see Swagger for exact paths and payloads)

`auth` · `users` · `centres` · `programs` · `filieres` · `secretaries` · `formateurs` ·
`stagiaires` · `cours` · `controles` · `notes` · `absences` · `payments` · `documents` ·
`emploidetemp` · `module-formateur` · `admin/dashboard` · `admin/audit-logs`

All endpoints live under `/api/...` (each controller sets its own prefix — there is no
global `server.servlet.context-path`).

---

## 💻 Getting Started

### Prerequisites

- **Git**
- **Node.js** (v16+) and **npm**
- **JDK 17+**
- A **NeonDB** account (cloud-hosted PostgreSQL) — [neon.com](https://neon.com)
- **IntelliJ IDEA** or **VS Code** (recommended)

### Clone the repo

```bash
git clone https://github.com/yozanSAN/Project_ILEIC.git
cd Project_ILEIC
```

### Database

1. Create a NeonDB project (or get added to the existing one by an existing
   contributor).
2. No manual schema setup needed — tables are created automatically on first backend
   startup via Hibernate (`ddl-auto=update`). This is convenient for local development;
   for anything beyond local dev, schema changes should go through a reviewed migration
   instead of relying on auto-update.

---

### 🔧 Environment Variables

The backend reads all sensitive/environment-specific config from environment variables
— nothing is hardcoded. Set these (e.g. in a `.env` file used by `docker-compose`, or as
actual environment variables when running locally):

```env
# Database (from your NeonDB connection string)
SPRING_DATASOURCE_URL=jdbc:postgresql://<your-neon-host>/<dbname>?sslmode=require
SPRING_DATASOURCE_USERNAME=<your-neon-username>
SPRING_DATASOURCE_PASSWORD=<your-neon-password>

# JWT
JWT_SECRET=<a-long-random-secret-string>
JWT_EXPIRATION=900000          # access token lifetime, ms (example: 15 min)
JWT_REFRESH_EXPIRATION=604800000  # refresh token lifetime, ms (example: 7 days)

# Frontend origin, for CORS
APP_FRONTEND_URL=http://localhost:5173
```

### Frontend `.env` (`frontend/.env`)

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

---

### 🎨 Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Runs at `http://localhost:5173`.

```bash
npm run dev      # development server
npm run build    # production build
npm run preview  # preview production build
npm run lint     # ESLint
```

---

### 🔌 Backend Setup

#### Option 1 — IntelliJ IDEA (recommended)

1. Open `/backend` as a project.
2. Confirm JDK 17+ is configured.
3. Let Maven resolve dependencies (right-click `pom.xml` → **Add as Maven Project**).
4. Set the environment variables above (Run Configuration → Environment Variables, or a
   local `.env`/IDE plugin).
5. Run the application.

#### Option 2 — Command line

```bash
cd backend
./mvnw clean install
./mvnw spring-boot:run
```

Runs at `http://localhost:8080`.

**Troubleshooting:**
- `mvnw` not executable → `chmod +x mvnw`
- Build issues → `./mvnw clean install` again after confirming JDK 17 is active
  (`java -version`)
- Connection refused to the database → double-check the `SPRING_DATASOURCE_*` values
  match your NeonDB connection string exactly, including `?sslmode=require`

---

## 🐳 Docker

```bash
docker-compose up --build
```

This builds and runs the **backend** and **frontend** containers. It does **not** run a
local database container — the backend connects out to your NeonDB instance using the
`SPRING_DATASOURCE_*` values in your `.env` file. Make sure that `.env` file exists at
the project root with all the variables listed above before running this.

### Building a production JAR manually

```bash
cd backend
./mvnw clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

---

## 🔐 Authentication Flow

The backend uses a two-token model:

1. `POST /api/auth/login` → returns a short-lived **access token** (sent as
   `Authorization: Bearer <token>` on every subsequent request) and a longer-lived
   **refresh token**.
2. When the access token expires, `POST /api/auth/refresh` with the refresh token
   returns a new access token — no re-entering credentials.
3. `POST /api/auth/logout` revokes the refresh token server-side, ending the session
   immediately.

Deactivated accounts (`isActive = false`) are rejected both at login and on every
refresh — a deactivated user is signed out on their next token refresh, not just
blocked from logging in again.

---

## 🤝 Contributing

### Branch naming

```
feature/feature-name
bugfix/bug-description
refactor/refactor-task
docs/documentation-task
```

### Commit messages

```
feat: add stagiaire enrollment endpoint
fix: correct absence risk threshold
refactor: simplify secretary duplicate-centre check
docs: update README
```

### Pull request process

1. Branch from `main`: `git checkout -b feature/your-feature-name`
2. Commit with clear messages
3. Before pushing: frontend → `npm run lint`; backend → `./mvnw clean install` builds
   clean
4. Push and open a PR describing what changed, why, and how to test it
5. Get at least one review before merging

### General guidelines

- Pull `main` before starting new work
- Write descriptive commit messages
- Keep PRs focused — avoid 500+ line PRs where possible
- Comment non-obvious logic
- Confirm the backend builds locally before pushing

---

## 📋 Project Status

**Current status:** 🚧 In active development.

Core CRUD across all resources (stagiaires, formateurs, secretaries, centres, filières,
programs, cours, contrôles, notes, absences, payments, documents, schedule) is in place,
along with role-based access control, the dual-token auth flow, and the admin dashboard
+ audit log. No automated test suite yet — changes are currently verified manually
through Swagger.

---

## 📄 License

MIT License — see [LICENSE](LICENSE).

---

## 📞 Support & Contact

- **Issues:** [GitHub Issues](https://github.com/yozanSAN/Project_ILEIC/issues)
- **Discussions:** [GitHub Discussions](https://github.com/yozanSAN/Project_ILEIC/discussions)
- **Email:** ayoubpro183@gmail.com

---

## 👥 Team

- **Project Lead:** yozanSAN
- **Contributors:** [See contributors](https://github.com/yozanSAN/Project_ILEIC/graphs/contributors)
