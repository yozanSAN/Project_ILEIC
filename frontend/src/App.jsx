import { Routes, Route } from "react-router-dom";

import formateurRoutes from "./routes/formateurRoutes"; 
import etudaintsRoutes from "./routes/EtudiantRoutes";
import secretaryRoutes from "./routes/secretaryRoutes";


const allRoutes = [...secretaryRoutes, ...formateurRoutes ,...etudaintsRoutes];

function App() {
  return (
    <div>
      <Routes>
        {allRoutes.map(({ path, element }) => (
          <Route key={path} path={path} element={element} />
        ))}
      </Routes>
    </div>
  );
}

export default App;