import { Routes, Route } from "react-router-dom";
import ProfileFormateurDashboard from "./pages/formateur/ProfileFormateur/PersonalDetailFormateur";
// import Login from "./pages/auth/Login-page";

function App() {
  return (
    <div>
      <Routes>
        {/* Default route
        <Route path="/" element={<Login />} /> */}

        {/* Dashboard routes */}
        <Route path="/formateur" element={<ProfileFormateurDashboard />} />
      </Routes>
    </div>
  );
}

export default App;