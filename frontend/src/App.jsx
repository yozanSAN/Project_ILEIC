import { Routes, Route } from "react-router-dom";
<<<<<<< HEAD
import StagiaireDashboard from "./pages/secretary/Stagiaires/StagiaireDashboard";
=======
import ProfileFormateurDashboard from "./pages/formateur/ProfileFormateur/PersonalDetailFormateur";
>>>>>>> formateur
// import Login from "./pages/auth/Login-page";

function App() {
  return (
    <div>
      <Routes>
        {/* Default route
        <Route path="/" element={<Login />} /> */}

        {/* Dashboard routes */}
<<<<<<< HEAD
        <Route path="/dashboard" element={<StagiaireDashboard />} />
=======
        <Route path="/formateur" element={<ProfileFormateurDashboard />} />
>>>>>>> formateur
      </Routes>
    </div>
  );
}

export default App;