import { Routes, Route } from "react-router-dom";
import formateurRoutes from "./routes/FormateurRoutes";
import etudaintsRoutes from "./routes/EtudiantRoutes";
import secretaryRoutes from "./routes/SecretaryRoutes";
import adminRoutes from "./routes/AdminRoutes";
import Login from "./pages/auth/Login-page"; 
import ProtectedRoute from "./routes/ProtectedRoute";
const allRoutes = [...adminRoutes, ...secretaryRoutes, ...formateurRoutes, ...etudaintsRoutes];

function renderRoutes(routes) {
  return routes.map((route) => {
    let allowedRoles = [];

    if (route.path.startsWith("/admin")) {
      allowedRoles = ["ADMIN"];
    } else if (route.path.startsWith("/secretaire")) {
      allowedRoles = ["SECRETAIRE"];
    } else if (route.path.startsWith("/formateur")) {
      allowedRoles = ["FORMATEUR"];
    } else if (route.path.startsWith("/stagiaire")) {
      allowedRoles = ["STAGIAIRE"];
    }

    return (
      <Route
        key={route.path}
        path={route.path}
        element={
          <ProtectedRoute allowedRoles={allowedRoles}>
            {route.element}
          </ProtectedRoute>
        }
      />
    );
  });
}

function App() {
  return (
    <div>
      <Routes>

        {renderRoutes(allRoutes)}

       
        <Route path="/" element={<Login />} /> 

        

           

      </Routes>
    </div>
  );
}

export default App;