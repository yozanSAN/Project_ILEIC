import { Routes, Route } from "react-router-dom";
import formateurRoutes from "./routes/FormateurRoutes";
import etudaintsRoutes from "./routes/EtudiantRoutes";
import secretaryRoutes from "./routes/secretaryRoutes";


const allRoutes = [...secretaryRoutes, ...formateurRoutes, ...etudaintsRoutes];

function renderRoutes(routes) {
  return routes.map((route) => {
    if (route.children) {
      return (
        <Route key={route.path} path={route.path} element={route.element}>
          {renderRoutes(route.children)}
        </Route>
      );
    }
    return <Route key={route.path} path={route.path} element={route.element} />;
  });
}

function App() {
  return (
    <div>
      <Routes>

        {renderRoutes(allRoutes)}

        {/* Default route
        <Route path="/" element={<Login />} /> */}

      </Routes>
    </div>
  );
}

export default App;