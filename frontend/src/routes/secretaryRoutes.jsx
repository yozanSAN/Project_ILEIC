import StagiaireDashboard from "../pages/secretary/Stagiaires/StagiaireDashboard";
import AjouterStagiaire from "../pages/secretary/Stagiaires/AjouterStagiaire";
import Notes from '../pages/secretary/Notes';
import Controles from "../pages/secretary/Controles";
const secretaryRoutes = [
    { path: "/secretaire/stagiaire", element: <StagiaireDashboard /> },
    { path: "/secretaire/stagiaire/ajouterStagiaire", element: <AjouterStagiaire /> },
    { path: "/secretaire/notes", element: <Notes /> },
    { path: "/secretaire/controles", element: <Controles /> }

];

export default secretaryRoutes;