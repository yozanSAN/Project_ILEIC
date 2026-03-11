import StagiaireDashboard from "../pages/secretary/Stagiaires/StagiaireDashboard";
import StagiaireLayout from "../components/layout/StagiaireLayout";
import Profil from "../pages/stagiaire/Profil";
import Notes from "../pages/stagiaire/Notes";
import Cours from "../pages/stagiaire/Cours";
import Schedule from "../pages/stagiaire/Schedule";
import Controle from "../pages/stagiaire/Controle";


const etudaintsRoutes = [
    { path: "/stagiaire", element: <StagiaireLayout /> },
     { path: "/stagiaire/profil", element: <profil /> },
     { path: "/stagiaire/notes", element: <Notes /> },
     { path: "/stagiaire/cours", element: <Cours /> },
     { path: "/stagiaire/controle", element: <Controle /> },
     { path: "/stagiaire/emplois-du-temps", element: <Schedule /> },
];

export default etudaintsRoutes;