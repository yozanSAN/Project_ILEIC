import Sidebar from "./Sidebar";
import Navbar from "./Navbar";
import { Outlet } from "react-router-dom";

import { secretaryItems } from "../../data/secretary/secretary_sidebar_items";
import secretary from "../../data/secretary/user";

import { formateurItems } from "../../data/formateur/formateur-sidebar-items";
import formateur from "../../data/formateur/formateur";

export default function MainLayout({ role }) {

  let sidebarItems = [];
  let user = null;

  switch (role) {
    case "formateur":
      sidebarItems = formateurItems;
      user = formateur;
      break;

    case "secretary":
      sidebarItems = secretaryItems;
      user = secretary;
      break;

    default:
      sidebarItems = [];
      user = null;
  }

  return (
    <div className="flex h-screen w-full bg-background overflow-hidden">
      
      {/* SIDEBAR */}
      <Sidebar items={sidebarItems} />

      {/* RIGHT SIDE */}
      <div className="flex flex-col flex-1 min-w-0">

        {/* NAVBAR */}
        <Navbar user={user} />

        {/* BODY CONTENT */}
        <main className="flex-1 overflow-y-auto p-4 md:p-8">

          {/* CONTENT LIMITER */}
          <div className="w-full px-6">

            <div className="max-w-7xl mx-auto w-full mr-10">

              {/* ROUTE CONTENT */}
              <Outlet />

            </div>

          </div>

        </main>

      </div>
    </div>
  );
}