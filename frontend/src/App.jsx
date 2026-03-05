import { Routes, Route } from "react-router-dom";

import secretaryRoutes from "./routes/SecretaryRoutes";
// import etudiantRoutes from "./routes/etudiantRoutes";
import formateurRoutes from "./routes/formateurRoutes";


const allRoutes = [...secretaryRoutes, ...formateurRoutes];

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