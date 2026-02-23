import { 
    User,
    Activity,
    UserX,
    BookOpen,
    Calendar
  } from "lucide-react";
  
  export const formateurItems = [
    {label: "Personnel Detail",path: "/formateur/personnel-detail",icon: User,},
    {label: "État",path: "/formateur/etat",icon: Activity,},
    {label: "Absences",path: "/formateur/absences",icon: UserX,},
    {label: "Cours",path: "/formateur/cours",icon: BookOpen,},
    {label: "Emplois",path: "/formateur/emplois",icon: Calendar,},
  ];