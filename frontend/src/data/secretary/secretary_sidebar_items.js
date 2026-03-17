import { 
  Users, 
  FileText, 
  BookOpen, 
  ClipboardList, 
  FileCheck, 
  CreditCard 
} from "lucide-react";

export const secretaryItems = [
  { label: "Stagiaires", path: "/secretaire/stagiaire", icon: Users }, 
  { label: "Contrôles", path: "/secretaire/controles", icon: FileText },
  { label: "Cours", path:  "/secretaire/cours", icon: BookOpen },
  { label: "Inscription Documents", path: "/secretaire/documents", icon: ClipboardList },
  { label: "Notes", path: "/secretaire/notes", icon: FileCheck },
  { label: "Paiements", path: "/secretaire/paiements", icon: CreditCard },
];