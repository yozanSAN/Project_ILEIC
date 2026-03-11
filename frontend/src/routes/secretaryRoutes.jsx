import StagiaireDashboard from "../pages/secretary/Stagiaires/StagiaireDashboard";
import AjouterStagiaire from "../pages/secretary/Stagiaires/AjouterStagiaire";
import Notes from '../pages/secretary/Notes';

const secretaryRoutes = [
    { path: "/secretaire/stagiaire", element: <StagiaireDashboard /> },
    { path: "/secretaire/stagiaire/ajouterStagiaire", element: <AjouterStagiaire /> },
    { path: "/secretaire/notes", element: <Notes /> }

];

export default secretaryRoutes;