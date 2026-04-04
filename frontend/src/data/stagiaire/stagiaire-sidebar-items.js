import { User, FileText, BookOpen, Calendar, BarChart2 } from "lucide-react";

export const stagiaireItems = [
    { label: "Informations", path: "/stagiaire/profil", icon: User },
    { label: "Notes", path: "/stagiaire/notes", icon: FileText },
    { label: "Cours", path: "/stagiaire/cours", icon: BookOpen },
    { label: "Emplois du temps", path: "/stagiaire/emplois-du-temps", icon: Calendar },
    { label: "Contrôle", path: "/stagiaire/controle", icon: BarChart2 },
];
