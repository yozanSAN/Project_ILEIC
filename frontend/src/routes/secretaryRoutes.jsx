import StagiaireDashboard from "../pages/secretary/Stagiaires/StagiaireDashboard";
import AjouterStagiaire from "../pages/secretary/Stagiaires/AjouterStagiaire";
import Notes from '../pages/secretary/Notes';
import Controles from "../pages/secretary/Controles";
import Cours from "../pages/secretary/Cours.jsx";
import Paiements from "../pages/secretary/paiements.jsx";

const secretaryRoutes = [
    { path: "/secretaire/stagiaire", element: <StagiaireDashboard /> },
    { path: "/secretaire/stagiaire/ajouterStagiaire", element: <AjouterStagiaire /> },
    { path: "/secretaire/notes", element: <Notes /> },
    { path: "/secretaire/controles", element: <Controles /> },
    { path: "/secretaire/cours", element: <Cours /> },
    { path: "/secretaire/paiements", element: <Paiements /> }

    

];

export default secretaryRoutes;