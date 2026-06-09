# 🚀 School Management System — Full Stack

A comprehensive full-stack web application designed to streamline school operations and management. Built with **React** on the frontend and **Spring Boot** on the backend, this system provides educators, administrators, and staff with an intuitive interface to manage students, courses, attendance, grades, and more.

---

## ✨ Features

- **👥 Student Management** — Register, update, and manage student profiles and information
- **📚 Course Management** — Create and organize courses with multiple sections and schedules
- **📋 Attendance Tracking** — Track and report on student attendance with automated insights
- **📊 Grade Management** — Input, calculate, and manage student grades and transcripts
- **🔐 User Authentication** — Secure role-based access control (Admin, Teacher, Student)
- **📈 Reports & Analytics** — Generate performance reports and analytics dashboards
- **🎨 Responsive UI** — Clean, modern interface optimized for desktop and tablet devices

---

## 📸 Screenshots

**Screenshots coming soon!**

---

## 📁 Project Structure

```
/
├── frontend/          # React + Vite + Tailwind CSS
│   ├── src/
│   │   ├── components/    # Reusable UI components
│   │   ├── pages/         # Page components
│   │   ├── services/      # API client services
│   │   └── App.jsx
│   ├── public/
│   └── package.json
│
└── backend/           # Spring Boot + Java + Maven
    ├── src/
    │   ├── main/java/
    │   │   └── com/school/ileic/
    │   │       ├── controllers/
    │   │       ├── services/
    │   │       ├── models/
    │   │       ├── repositories/
    │   │       └── Application.java
    │   └── test/
    ├── pom.xml
    └── mvnw
```

---

## 🛠️ Tech Stack

| Layer      | Technology                                   |
|------------|----------------------------------------------|
| Frontend   | React, Vite, Tailwind CSS, Lucide-React      |
| Backend    | Spring Boot, Spring Data JPA, Maven          |
| Database   | PostgreSQL                                   |
| APIs       | RESTful API with Spring Web                  |

---

## 💻 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Git** — Version control
- **Node.js** (v16+) and **npm** — For frontend development
- **JDK 17+** — For backend development
- **NeonDB (Cloud database)** (v12+) — Database
- **IntelliJ IDEA** or **VS Code** — Recommended IDEs

### General Setup

```bash
# Clone the repository
git clone https://github.com/yozanSAN/Project_ILEIC.git
cd Project_ILEIC

# Pull the latest code
git pull origin main
```

---

### 🗄️ Database Setup

1. **-> Create account in NeonDB to get access to the database ** (Create account if not already)
   - [https://neon.com/]

2. **You will resive a contibution invite :**

3. **Database schema** will be auto-created on first backend startup (via Spring Data JPA/Hibernate)

---

### 🔧 Environment Configuration

Create a `.env` file in the root directory for easy environment variable management:

#### Frontend `.env` (`frontend/.env`)

```env
# API Configuration
VITE_API_BASE_URL=http://localhost:8080/api

# App Environment
VITE_APP_ENV=development
VITE_APP_NAME=School Management System
```

#### Backend `application.properties` (`backend/src/main/resources/application.properties`)

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Database Configuration
[Database private info will be shared privitly]
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Application Configuration
app.name=School Management System
app.version=1.0.0
```

---

### 🎨 Frontend Setup (React + Vite)

```bash
# Navigate to the frontend directory
cd frontend

# Install dependencies
npm install

# Start the development server
npm run dev
```

> 🌐 **Frontend runs at:** `http://localhost:5173`

**Available npm scripts:**

```bash
npm run dev      # Start development server
npm run build    # Build for production
npm run preview  # Preview production build
npm run lint     # Run ESLint
```

---

### 🔌 Backend Setup (Spring Boot)

#### Option 1: Using IntelliJ IDEA (Recommended)

1. Open the `/backend` folder in **IntelliJ IDEA**
2. Ensure **JDK 17** (or higher) is installed and configured
3. Load Maven dependencies:
   - Right-click `pom.xml` → **Add as Maven Project**
4. Wait for Maven to download dependencies
5. Click the **Run** button or press `Shift + F10`

#### Option 2: Using Command Line

```bash
# Navigate to backend directory
cd backend

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

> 🌐 **Backend runs at:** `http://localhost:8080/api`

**Troubleshooting:**
- If `mvnw` is not executable: `chmod +x mvnw`
- Clear Maven cache: `./mvnw clean`

---

## 📡 API Documentation

The backend provides a RESTful API for all school management operations.

### Base URL
```
http://localhost:8080/api
```

### Main Endpoints

#### **Students**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/students` | List all students |
| GET | `/students/{id}` | Get student by ID |
| POST | `/students` | Create a new student |
| PUT | `/students/{id}` | Update student |
| DELETE | `/students/{id}` | Delete student |

#### **Courses**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/courses` | List all courses |
| GET | `/courses/{id}` | Get course by ID |
| POST | `/courses` | Create a new course |
| PUT | `/courses/{id}` | Update course |
| DELETE | `/courses/{id}` | Delete course |

#### **Attendance**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/attendance` | List attendance records |
| POST | `/attendance` | Mark attendance |
| GET | `/attendance/report/{studentId}` | Get student attendance report |

#### **Grades**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/grades` | List all grades |
| POST | `/grades` | Enter/update grades |
| GET | `/grades/transcript/{studentId}` | Get student transcript |

### Example API Calls

```bash
# Get all students
curl -X GET http://localhost:8080/api/students

# Create a new student
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@school.com",
    "enrollmentDate": "2024-01-15"
  }'

# Get a specific student
curl -X GET http://localhost:8080/api/students/1
```

> 📖 **Full API documentation:** See `backend/API_DOCS.md` for complete endpoint details (coming soon)

---

## 🚀 Building for Production

### Frontend Production Build

```bash
cd frontend

# Build optimized production bundle
npm run build

# Preview the production build locally
npm run preview
```

Output files are in `frontend/dist/` — ready to deploy to any static hosting service.

### Backend Production Build

```bash
cd backend

# Build JAR file
./mvnw clean package

# JAR file created at: backend/target/school-management-system.jar

# Run the JAR
java -jar target/school-management-system.jar
```

### Deployment Options

- **Frontend:** Vercel, Netlify, GitHub Pages, AWS S3 + CloudFront
- **Backend:** AWS EC2, Heroku, DigitalOcean, Railway, Azure App Service
- **Database:** AWS RDS, Azure Database for PostgreSQL, Railway, Heroku Postgres

---

## 🔧 Troubleshooting & FAQ

### Frontend Issues

**Port 5173 already in use:**
```bash
# Kill process using port 5173
lsof -ti:5173 | xargs kill -9
# Then restart: npm run dev
```

**Dependencies not installing:**
```bash
# Clear npm cache
npm cache clean --force
rm -rf node_modules package-lock.json
npm install
```

### Backend Issues

**Database connection failed:**
- Verify PostgreSQL is running: `psql -U postgres`
- Check database credentials in `application.properties`
- Ensure database exists: `CREATE DATABASE ileic_school;`

**JDK version mismatch:**
```bash
# Check your Java version
java -version

# Install JDK 17 if needed (macOS)
brew install openjdk@17
```

**Maven build fails:**
```bash
# Clear Maven cache
./mvnw clean install
```

### General Issues

**Port 8080 already in use:**
```bash
# Kill process using port 8080
lsof -ti:8080 | xargs kill -9
```

**"No changes detected" or git merge conflicts:**
```bash
git pull origin main
git status
```

---

## 🤝 Contributing

We welcome contributions from team members! Please follow these guidelines:

### Branch Naming Convention

```
feature/feature-name       # New features
bugfix/bug-description     # Bug fixes
refactor/refactor-task     # Code refactoring
docs/documentation-task    # Documentation updates
```

### Commit Message Format

```
feat: add student enrollment feature
bugfix: fix grade calculation bug
refactor: simplify authentication logic
docs: update API documentation
```

### Pull Request Process

1. **Create a feature branch** from `main`:
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes** and commit with clear messages:
   ```bash
   git add .
   git commit -m "feat: describe your changes"
   ```

3. **Ensure code quality:**
   - Frontend: Run `npm run lint`
   - Backend: Ensure builds locally with `./mvnw clean install`

4. **Push your branch:**
   ```bash
   git push origin feature/your-feature-name
   ```

5. **Create a Pull Request** with a clear description of:
   - What you changed
   - Why you changed it
   - How to test the changes

6. **Code Review:** Wait for approval from at least one team member

7. **Merge:** Once approved, merge your PR to `main`

### General Guidelines

- ✅ Always run `git pull origin main` before starting work
- ✅ Write descriptive commit messages (no vague messages like "fixed stuff")
- ✅ Test your changes locally before pushing
- ✅ Keep PRs focused and reasonably sized (not 500+ lines)
- ✅ Add comments to complex logic
- ✅ Ensure the backend **builds locally** before pushing

---

## 📋 Project Status & Roadmap

**Current Status:** 🚧 **In Development**

### v1.0 (Current - Beta)
- ✅ Core student management
- ✅ Course management
- ✅ Attendance tracking
- ✅ Grade entry and management
- ✅ User authentication & authorization

### v1.1 (Planned)
- 📅 Advanced reporting and analytics
- 📅 Email notifications (grades, attendance alerts)
- 📅 Mobile-responsive dashboard improvements
- 📅 Bulk import/export functionality

### Future (v2.0+)
- 📅 Mobile app (React Native)
- 📅 Parent portal
- 📅 Payment management
- 📅 Real-time notifications via WebSockets
- 📅 Advanced analytics with charts and graphs

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

You are free to:
- ✅ Use this software for any purpose
- ✅ Modify and distribute it
- ✅ Use it commercially

With the condition:
- 📌 Include the original license notice

---

## 📞 Support & Contact

- **Issues & Bugs:** [Create an issue](https://github.com/yozanSAN/Project_ILEIC/issues)
- **Discussions:** [GitHub Discussions](https://github.com/yozanSAN/Project_ILEIC/discussions)
- **Email:** ayoubpro183@gmail.com

---

## 👥 Team

- **Project Lead:** yozanSAN
- **Contributors:** [See contributors page](https://github.com/yozanSAN/Project_ILEIC/graphs/contributors)

---

## 🙏 Acknowledgments

- [React Documentation](https://react.dev)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Tailwind CSS](https://tailwindcss.com)
- [Vite Documentation](https://vitejs.dev)

---

**Last Updated:** May 2026  
**Maintained By:** yozanSAN
