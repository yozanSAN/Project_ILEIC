import {LayoutDashboard,Users,UserCheck,GraduationCap,} from "lucide-react";
  
  export const adminItems = [
    {label: "Dashboard",path: "/admin",icon: LayoutDashboard,},
    {label: "Formateurs",path: "/formateur/personnel-detail",icon: UserCheck,},
    {label: "Stagiaires",path: "/stagiaire/profil",icon: GraduationCap,},
    {label: "Secrétaires",path: "/secretaire/stagiaire",icon: Users,},
  ];