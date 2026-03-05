import { Routes, Route, Navigate } from "react-router-dom";
import StagiaireDashboard from "./pages/secretary/Stagiaires/StagiaireDashboard";
import StagiaireLayout from "./components/layout/StagiaireLayout";
import Profil from "./pages/stagiaire/Profil";
import Notes from "./pages/stagiaire/Notes";
import Cours from "./pages/stagiaire/Cours";
import Schedule from "./pages/stagiaire/Schedule";
import Controle from "./pages/stagiaire/Controle";

function App() {
  return (
    <div>
      <Routes>
        {/* Redirect empty path to dashboard */}
        <Route path="/" element={<Navigate to="/dashboard" replace />} />

        {/* Secretary Dashboard routes */}
        <Route path="/dashboard" element={<StagiaireDashboard />} />

        {/* Stagiaire routes */}
        <Route path="/stagiaire" element={<StagiaireLayout />}>
          <Route index element={<Navigate to="profil" replace />} />
          <Route path="profil" element={<Profil />} />
          <Route path="notes" element={<Notes />} />
          <Route path="cours" element={<Cours />} />
          <Route path="emplois-du-temps" element={<Schedule />} />
          <Route path="controle" element={<Controle />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;