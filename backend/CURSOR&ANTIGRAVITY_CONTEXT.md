# Project ILEIC - Backend Context for Frontend Development (Cursor & Antigravity)

This document provides a comprehensive overview of the Spring Boot backend architecture, data models, authentication flow, and API endpoints for the ILEIC project. It is designed to help frontend developers using Cursor and Antigravity understand how to interact with the backend effectively.

## 1. Architecture Overview

The backend is built with **Java Spring Boot** and uses **PostgreSQL** as the database. It follows a standard layered architecture:
- **Controllers (`/controller`)**: Expose RESTful API endpoints.
- **Services (`/service`)**: Contain business logic.
- **Repositories (`/repository`)**: Handle database operations using Spring Data JPA.
- **Entities (`/entity`)**: Map to database tables.
- **DTOs (`/dto`)**: Data Transfer Objects used for API requests and responses to prevent exposing internal database structures.
- **Security (`/security`)**: Handles JWT-based authentication and role-based access control (RBAC).

The backend runs on port `8080` (as defined in `docker-compose.yml`), and the frontend is expected to run on port `5173` (Vite default) or `3000` (Docker).

## 2. Authentication & Security

The application uses **JWT (JSON Web Tokens)** for stateless authentication.

### Authentication Flow
1. **Login**: The frontend sends a `POST` request to `/api/auth/login` with `{ "email": "...", "password": "..." }`.
2. **Tokens**: The backend responds with an `AuthResponseDTO` containing:
   - `accessToken`: Short-lived token for API requests.
   - `refreshToken`: Long-lived token to get a new access token.
   - User details (ID, email, role, etc.).
3. **API Requests**: The frontend must include the access token in the `Authorization` header for protected routes:
   ```http
   Authorization: Bearer <access_token>
   ```
4. **Refresh Token**: When the access token expires, the frontend sends a `POST` request to `/api/auth/refresh` with `{ "refreshToken": "..." }` to get a new access token.
5. **Logout**: The frontend sends a `POST` request to `/api/auth/logout` with the refresh token to invalidate the session.

### Roles & Permissions
The system uses Role-Based Access Control (RBAC) with four distinct roles:
- `ADMIN`: Full access to all endpoints, including dashboard stats.
- `SECRETAIRE`: Can manage students (Stagiaires), payments, absences, and documents.
- `FORMATEUR`: Can view their courses, manage grades (Notes), and view student lists.
- `STAGIAIRE`: Read-only access to their own grades, schedule, and payments.

## 3. Core Entities & Data Models

The database schema is built around the educational institute's operations. Here are the key entities:

### Users & Roles
- **User**: The base entity for all accounts. Contains `email`, `passwordHash`, `role`, `fullName`, and `isActive`.
- **Stagiaire (Student)**: Linked to a `User`, `Centre`, and `Filiere`. Contains `registrationNumber`, `birthDate`, `cin`, `enrollmentDate`, and `status`.
- **Formateur (Teacher)**: Linked to a `User` and multiple `Centre`s. Contains `speciality` and `hireDate`.
- **Secretary**: Linked to a `User` and a specific `Centre`.

### Academic Structure
- **Centre**: Physical or logical campus locations.
- **Filiere**: Educational tracks or majors.
- **Program**: Curriculum details.
- **Cours**: Specific classes taught by a Formateur.
- **ModulesFormateur**: Links Formateurs to specific modules.

### Operations
- **Controle**: Exams or assessments.
- **Note**: Grades assigned to a Stagiaire for a specific Controle.
- **Absence**: Records of student absences.
- **Payment**: Financial transactions linked to a Stagiaire.
- **EmploiDeTemp**: Schedules and timetables.
- **Document**: Administrative documents.

## 4. Key API Endpoints

All API endpoints are prefixed with `/api`. Below are the most critical endpoints for frontend integration.

### Authentication (`/api/auth`)
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/login` | Authenticate user and get tokens | Public |
| POST | `/refresh` | Get a new access token using a refresh token | Public |
| POST | `/logout` | Invalidate the refresh token | Public |

### Stagiaires (Students) (`/api/stagiaires`)
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/` | Get all students (supports `?centreId=` & `?filiereId=`) | ADMIN, SECRETAIRE |
| GET | `/{id}` | Get a specific student by ID | ADMIN, SECRETAIRE, FORMATEUR |
| POST | `/` | Create a new student | SECRETAIRE |
| PUT | `/{id}` | Update a student | SECRETAIRE |
| DELETE | `/{id}` | Delete a student | SECRETAIRE |

### Notes (Grades) (`/api/notes`)
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/stagiaire/{id}` | Get all grades for a specific student | ADMIN, SECRETAIRE, FORMATEUR, STAGIAIRE |
| GET | `/controle/{id}` | Get all grades for a specific exam | ADMIN, SECRETAIRE, FORMATEUR |
| POST | `/` | Record a new grade | ADMIN, SECRETAIRE, FORMATEUR |
| PUT | `/{id}` | Update a grade | ADMIN, SECRETAIRE, FORMATEUR |

### Payments (`/api/payments`)
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/stagiaire/{id}` | Get payment history for a student | ADMIN, SECRETAIRE, STAGIAIRE |
| POST | `/` | Record a new payment | ADMIN, SECRETAIRE |
| PATCH | `/{id}/status` | Update payment status (e.g., pending to paid) | ADMIN, SECRETAIRE |

### Admin Dashboard (`/api/admin/dashboard`)
| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/stats` | Get high-level system statistics | ADMIN |
| GET | `/centres` | Get summary data per centre | ADMIN |
| GET | `/at-risk` | Get list of students at risk (absences/grades) | ADMIN |
| GET | `/payments/overview` | Get payment overview (supports `?month=` & `?year=`) | ADMIN |

## 5. Frontend Integration Status & Recommendations

Based on the current state of the `frontend/` directory:

1. **Missing API Integration**: The frontend currently relies heavily on hardcoded mock data (e.g., `frontend/src/data/secretary/stagiaires.js`). There are no `axios` or `fetch` calls implemented yet.
2. **Authentication Mocked**: The `Login-page.jsx` uses a `setTimeout` to simulate login. This needs to be replaced with an actual API call to `/api/auth/login`, and the resulting JWT tokens must be stored securely (e.g., in `localStorage` or context).
3. **CORS Configuration**: The backend is configured to accept requests from the URL defined in `app.frontend.url` (defaults to `http://localhost:5173`). Ensure your Vite dev server runs on this port, or update the `.env` file accordingly.
4. **API Client Setup**: It is highly recommended to create a centralized Axios instance (e.g., `src/services/api.js`) that automatically attaches the `Authorization: Bearer <token>` header to all requests and handles token refresh logic using interceptors.

## 6. Antigravity & Cursor Workflow Tips

- **DTO Mapping**: When building forms or displaying data, refer to the DTOs in `backend/src/main/java/com/ProjetILEIC/ILIEC/dto/`. The backend expects flat structures (e.g., `centreId` instead of a nested `centre` object) for creation/updates.
- **Error Handling**: The backend uses a `GlobalExceptionHandler`. Expect standardized error responses (e.g., `DuplicateResourceException`, `ResourceNotFoundException`) and handle them gracefully in the UI.
- **Role Routing**: The frontend already has separate route files (`AdminRoutes.jsx`, `SecretaryRoutes.jsx`, etc.). Ensure that the role returned from the login API dictates which routes are accessible to the user.
