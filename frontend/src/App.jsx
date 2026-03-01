import { Routes, Route } from "react-router-dom";
import StagiaireDashboard from "./pages/secretary/Stagiaires/StagiaireDashboard";
// import Login from "./pages/auth/Login-page";

function App() {
  return (
    <div>
      <Routes>
        {/* Default route
        <Route path="/" element={<Login />} /> */}

        {/* Dashboard routes */}
        <Route path="/dashboard" element={<StagiaireDashboard />} />
      </Routes>
    </div>
  );
}

export default App;