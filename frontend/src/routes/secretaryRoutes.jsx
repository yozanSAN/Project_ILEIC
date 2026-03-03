import StagiaireDashboard from "../pages/secretary/Stagiaires/StagiaireDashboard";
import AjouterStagiaire from "../pages/secretary/Stagiaires/AjouterStagiaire";

const secretaryRoutes = [
    { path: "/dashboard", element: <StagiaireDashboard /> },
    { path: "/AjouterStagiaire", element: <AjouterStagiaire /> },
];

export default secretaryRoutes;