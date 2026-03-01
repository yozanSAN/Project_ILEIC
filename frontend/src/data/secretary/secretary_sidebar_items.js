import { 
  Users, 
  FileText, 
  BookOpen, 
  ClipboardList, 
  FileCheck, 
  CreditCard 
} from "lucide-react";

export const secretaryItems = [
  { label: "Stagiaires", path: "/secretary/stagiaires", icon: Users }, 
  { label: "Contrôles", path: "/secretary/controles", icon: FileText },
  { label: "Cours", path: "/secretary/cours", icon: BookOpen },
  { label: "Inscription Documents", path: "/secretary/documents", icon: ClipboardList },
  { label: "Notes", path: "/secretary/notes", icon: FileCheck },
  { label: "Paiements", path: "/secretary/payments", icon: CreditCard },
];