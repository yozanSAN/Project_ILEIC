# 🚀 School Management Project - Frontend

## Getting Started

1. **Pull the latest code:** `git pull origin main`
2. **switch to branch main:** `git checkout main`
3. **Install dependencies:** `npm install`
4. **Run the app:** `npm run dev`

## Tech Stack
- **Framework:** React + Vite
- **Styling:** Tailwind CSS (Pure)
- **Icons:** Lucide-React

## Layout Instructions
All dashboard pages should be wrapped in the `MainLayout` component.
## 🏗️ Project Architecture: MainLayout

To keep the UI consistent, all pages (Dashboard, Students, Payments, etc.) must use the `MainLayout`. This component automatically includes the **Sidebar** and **Navbar**.

### How to use it:
When you create a new page, simply wrap your content inside the `<MainLayout>` component. The content you place inside will be rendered in the main scrollable area.

**Example (`src/pages/Students.jsx`):**
```jsx
import MainLayout from '../components/layout/MainLayout';

const sstagiars = () => {
  return (
    <MainLayout>
      {/* Anything you put here is passed as "children" 
         and will appear in the middle of the screen.
      */}
      <h1 className="text-2xl font-bold">Gestion des Stagiaires</h1>
      <p>Bienvenue sur la page de gestion des étudiants.</p>
      
      {/* dir le composantes le 3ndk wst main layout , matalan : stagiaireTable  wla emplois des temps*/}
      <stagiaireTable />
    </MainLayout>
  );
};

export default StudentsPage;