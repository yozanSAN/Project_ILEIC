import { FileText, Users, Calendar, LayoutDashboard } from "lucide-react";

export const secretaryItems = [
  { label: "Tableau de bord", path: "/dashboard", icon: LayoutDashboard },
  { label: "Stagiaire", path: "/students", icon: Users },
  { label: "Contrôles", path: "/controls", icon: FileText },
  { label: "Emploi du temps", path: "/schedule", icon: Calendar },
];