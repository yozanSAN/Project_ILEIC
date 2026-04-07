import PersonalDetailFormateur from "../pages/formateur/ProfileFormateur/PersonalDetailFormateur";
import EtatFormateur from "../pages/formateur/EtatFormateur/EtatFormateur";
import AbsencesFormateur from "../pages/formateur/AbsenceFormateur/AbsencesFormateur";
import CoursFormateur from "../pages/formateur/CoursFormateur/CoursFormateur";
import EmploisFormateur from "../pages/formateur/EmploisFormateur/EmploisFormateur";

const formateurRoutes = [
  {path: "/formateur",element: <PersonalDetailFormateur />,},
  {path: "/formateur/personnel-detail",element: <PersonalDetailFormateur />,},
  {path: "/formateur/etat",element: <EtatFormateur />,},
  {path: "/formateur/absences",element: <AbsencesFormateur />,},
  {path: "/formateur/cours",element: <CoursFormateur />,},
  {path: "/formateur/emplois",element: <EmploisFormateur />,},
];

export default formateurRoutes;