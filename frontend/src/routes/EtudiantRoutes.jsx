import Profil from "../pages/stagiaire/Profil";
import Notes from "../pages/stagiaire/Notes";
import Cours from "../pages/stagiaire/Cours";
import Schedule from "../pages/stagiaire/Schedule";
import Controle from "../pages/stagiaire/Controle";


const etudaintsRoutes = [
    { path: "/stagiaire/profil", element: <Profil /> },
    { path: "/stagiaire/notes", element: <Notes /> },
    { path: "/stagiaire/cours", element: <Cours /> },
    { path: "/stagiaire/controle", element: <Controle /> },
    { path: "/stagiaire/emplois-du-temps", element: <Schedule /> },
        
    
];

export default etudaintsRoutes;