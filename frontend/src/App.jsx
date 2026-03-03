import { Routes, Route } from "react-router-dom";
import secretaryRoutes from "./routes/secretaryRoutes";
import formateurRoutes from "./routes/formateurRoutes";
import etudiantRoutes from "./routes/etudiantRoutes";

const allRoutes = [...secretaryRoutes, ...formateurRoutes, ...etudiantRoutes];

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