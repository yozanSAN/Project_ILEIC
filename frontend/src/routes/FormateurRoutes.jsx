
import AbsencesFormateur from './../pages/formateur/AbsencesFormateur';
import ControleFormateur from './../pages/formateur/ControleFormateur';

const formateurRoutes = [
    { path: "/dashboardff", element: <AbsencesFormateur /> },
    { path: "/AjouterStagiaireff", element: <ControleFormateur /> },
];

export default formateurRoutes;