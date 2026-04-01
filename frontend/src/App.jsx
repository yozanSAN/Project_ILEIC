import { Routes, Route } from "react-router-dom";
import formateurRoutes from "./routes/FormateurRoutes"; 
import etudaintsRoutes from "./routes/EtudiantRoutes";
import secretaryRoutes from "./routes/SecretaryRoutes";


const allRoutes = [...secretaryRoutes, ...formateurRoutes ,...etudaintsRoutes];

function App() {
  return (
    <div>
      <Routes>

        {allRoutes.map(({ path, element }) => (
          <Route key={path} path={path} element={element} />
        ))}

        {/* Default route
        <Route path="/" element={<Login />} /> */}

        {/* Dashboard routes */}
        {/* <Route path="/dashboard" element={<StagiaireDashboard />} /> */}

           

      </Routes>
    </div>
  );
}

export default App;