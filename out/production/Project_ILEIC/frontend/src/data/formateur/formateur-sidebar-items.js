// src/data/formateur/formateur-sidebar-items.js
import { User, Activity, UserX, BookOpen, Calendar,ClipboardCheck } from "lucide-react";

export const formateurItems = [
  { label: "Personnel Detail",  path: "/formateur/personnel-detail",  icon: User },
  { label: "État",              path: "/formateur/etat",              icon: Activity },
  { label: "Absences",          path: "/formateur/absences",          icon: UserX },
  { label: "Cours",             path: "/formateur/cours",             icon: BookOpen },
<<<<<<< Updated upstream
  { label: "Contrôle",          path: "/formateur/controle",          icon: ClipboardCheck },   
=======
  { label: "Contrôle",          path: "/formateur/controle",          icon: ClipboardCheck },   // ← Added
>>>>>>> Stashed changes
  { label: "Emplois",           path: "/formateur/emplois",           icon: Calendar },
];