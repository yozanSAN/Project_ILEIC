import StagiaireDashboard from "../pages/secretary/Stagiaires/StagiaireDashboard";
import AjouterStagiaire from "../pages/secretary/Stagiaires/AjouterStagiaire";

const secretaryRoutes = [
    { path: "/secretaire/stagiaire", element: <StagiaireDashboard /> },
     { path: "/secretaire/stagiaire/ajouterStagiaire", element: <AjouterStagiaire /> },
];

export default secretaryRoutes;