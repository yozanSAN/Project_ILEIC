import Sidebar from "./Sidebar";
import Navbar from "./Navbar";
import { useLocation } from "react-router-dom";

// items
import { secretaryItems } from "../../data/secretary/secretary_sidebar_items";
import { formateurItems } from "../../data/formateur/formateur-sidebar-items";
import { stagiaireItems } from "../../data/stagiaire/stagiaire-sidebar-items";

// users
import secretaryUser from "../../data/secretary/user";
import formateurUser from "../../data/formateur/formateur";
import stagiaireUser from "../../data/stagiaire/user";

export default function MainLayout({ children }) {
  const location = useLocation();
  const isFormateur = location.pathname.startsWith("/formateur");
  const isSecretary = location.pathname.startsWith("/secretaire");
  const isStagiaire = location.pathname.startsWith("/stagiaire")
  let sidebarItems;
  let currentUser;


  if (isFormateur) {
    sidebarItems = formateurItems;
    currentUser = formateurUser;
  } else if (isSecretary) {
    sidebarItems = secretaryItems;
    currentUser = secretaryUser;
  } else if (isStagiaire){
    sidebarItems = stagiaireItems
    currentUser = stagiaireUser
  }

  return (
    <div className="flex h-screen w-full bg-background overflow-hidden">

      {/* SIDEBAR */}
      <Sidebar items={sidebarItems} />

      <div className="flex flex-col flex-1 min-w-0">
        <Navbar user={currentUser} items={sidebarItems} />

        <main className="flex-1 overflow-y-auto p-4 md:p-8">
          <div className="w-full px-6">
            {children}
          </div>
        </main>
      </div>
    </div>
  );
}