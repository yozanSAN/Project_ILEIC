# 🚀 School Management System — Full Stack

A full-stack web application for managing school operations, built with React on the frontend and Spring Boot on the backend.

---

## 📁 Project Structure

```
/
├── frontend/     # React + Vite + Tailwind CSS
└── backend/      # Spring Boot + Java + Maven
```

---

## 💻 Getting Started

### General Setup

```bash
# Clone the repository
git clone <repo-url>

# Pull the latest code
git pull origin main
```

---

### Frontend Setup (React)

```bash
# Navigate to the frontend directory
cd frontend

# Install dependencies
npm install

# Start the development server
npm run dev
```

> 🌐 Runs at: `http://localhost:5173`

---

### Backend Setup (Spring Boot)

1. Open the `/backend` folder in **IntelliJ IDEA**
2. Make sure **JDK 17** (or your specific version) is installed
3. Load Maven dependencies: right-click `pom.xml` → **Add as Maven Project**
4. Run the application:
   - Use the **Run** button in IntelliJ, **or**
   - Run the following command in the terminal:

```bash
./mvnw spring-boot:run
```

> 🌐 Runs at: `http://localhost:8080`

---

## 🛠️ Tech Stack

| Layer      | Technology                                    |
|------------|-----------------------------------------------|
| Frontend   | React, Vite, Tailwind CSS, Lucide-React       |
| Backend    | Spring Boot, Spring Data JPA, Maven           |
| Database   | *(Add your database here, e.g. MySQL/MongoDB)*|

---

## 🤝 Team Workflow

- **Always** run `git pull` before starting work to avoid merge conflicts
- Write clear, descriptive commit messages (e.g., `feat: added student controller`)
- Make sure the backend **builds locally** before pushing any changes
