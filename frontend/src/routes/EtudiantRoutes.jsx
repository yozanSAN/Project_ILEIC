import MainLayout from "../components/layout/MainLayout";
import Profil from "../pages/stagiaire/Profil";
import Notes from "../pages/stagiaire/Notes";
import Cours from "../pages/stagiaire/Cours";
import Schedule from "../pages/stagiaire/Schedule";
import Controle from "../pages/stagiaire/Controle";


const etudaintsRoutes = [
    {
        path: "/stagiaire",
        element: <MainLayout role="stagiaire" />,
        children: [
            { path: "profil", element: <Profil /> },
            { path: "notes", element: <Notes /> },
            { path: "cours", element: <Cours /> },
            { path: "controle", element: <Controle /> },
            { path: "emplois-du-temps", element: <Schedule /> },
        ]
    }
];

export default etudaintsRoutes;