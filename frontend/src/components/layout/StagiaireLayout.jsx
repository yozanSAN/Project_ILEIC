import { Outlet, NavLink, useLocation, useNavigate } from "react-router-dom";
import { User, FileText, BookOpen, Calendar, BarChart2, Bell, ChevronDown, LogOut } from "lucide-react";

const StagiaireLayout = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/");
  };

  // Simple function to get a title based on path
  const getPageTitle = (pathname) => {
    if (pathname.includes("profil")) return "Profil de l'étudiant";
    if (pathname.includes("notes")) return "Mes Notes";
    if (pathname.includes("cours")) return "Mes Cours";
    if (pathname.includes("emplois-du-temps")) return "Emplois du temps";
    if (pathname.includes("controle")) return "Contrôle";
    return "Tableau de Bord";
  };

  return (
    <div className="flex h-screen bg-slate-50 font-sans">
      {/* Sidebar */}
      <aside className="w-64 bg-[#C82A2E] text-white flex flex-col shrink-0">
        <div className="p-6 flex items-center gap-3 border-b border-red-800/30">
          <div className="w-10 h-10 rounded-full bg-[#528A6D] flex items-center justify-center text-[10px] font-bold shadow-inner">
            ILEIC
          </div>
          <div>
            <h1 className="font-bold text-lg leading-tight tracking-wide">ILEIC</h1>
            <p className="text-[10px] text-white/80 font-medium tracking-wider">ESPACE ÉTUDIANT</p>
          </div>
        </div>

        <nav className="flex-1 px-4 py-6 space-y-1.5 overflow-y-auto">
          <NavItem to="/stagiaire/profil" icon={<User size={18} />} label="Informations" />
          <NavItem to="/stagiaire/notes" icon={<FileText size={18} />} label="Notes" />
          <NavItem to="/stagiaire/cours" icon={<BookOpen size={18} />} label="Cours" />
          <NavItem to="/stagiaire/emplois-du-temps" icon={<Calendar size={18} />} label="Emplois du temps" />
          <NavItem to="/stagiaire/controle" icon={<BarChart2 size={18} />} label="Contrôle" />
        </nav>

        {/* Logout Button */}
        <div className="p-4 border-t border-red-800/30">
          <button
            onClick={handleLogout}
            className="w-full flex items-center gap-3 px-4 py-3 text-white/70 hover:bg-white/5 hover:text-white rounded-xl transition-all duration-200 group"
          >
            <LogOut size={18} />
            <span className="text-[15px]">Déconnexion</span>
          </button>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 flex flex-col min-w-0 overflow-hidden">
        {/* Header */}
        <header className="bg-white/80 backdrop-blur-md border-b border-gray-200 h-16 flex items-center justify-between px-8 bg-opacity-90 sticky top-0 z-10 shrink-0">
          <div className="text-sm text-slate-500 font-medium flex items-center">
            <span className="cursor-pointer hover:text-slate-800 transition-colors">Accueil</span>
            <span className="mx-2 text-slate-300">/</span>
            <span className="font-semibold text-slate-800">{getPageTitle(location.pathname)}</span>
          </div>

          <div className="flex items-center gap-6">
            <button className="text-slate-500 hover:text-slate-800 text-sm font-medium transition-colors hidden sm:flex items-center gap-1.5">
              Untitled <ChevronDown size={14} className="text-slate-400" />
            </button>
            <button className="text-slate-400 hover:text-slate-800 relative transition-colors">
              <Bell size={20} className="stroke-[1.5]" />
              <span className="absolute top-0 right-0 w-2 h-2 bg-red-500 rounded-full border border-white"></span>
            </button>
            <div className="flex items-center gap-3 cursor-pointer group">
              <div className="text-right hidden sm:block">
                <p className="text-sm font-semibold text-slate-900 leading-tight group-hover:text-red-700 transition-colors">Amine Benali</p>
                <p className="text-xs text-slate-500">Étudiant</p>
              </div>
              <img
                src="https://api.dicebear.com/7.x/avataaars/svg?seed=Amine&backgroundColor=b6e3f4"
                alt="Profile"
                className="w-9 h-9 rounded-full bg-slate-100 ring-2 ring-transparent group-hover:ring-red-100 transition-all"
              />
            </div>
          </div>
        </header>

        {/* Page Content */}
        <div className="flex-1 overflow-auto p-4 sm:p-8">
          <Outlet />
        </div>
      </main>
    </div>
  );
};

const NavItem = ({ to, icon, label }) => {
  return (
    <NavLink
      to={to}
      className={({ isActive }) => `
        flex items-center gap-3 px-4 py-3 rounded-xl transition-all duration-200
        ${isActive
          ? 'bg-white/10 text-white font-medium shadow-sm backdrop-blur-sm'
          : 'text-white/70 hover:bg-white/5 hover:text-white hover:translate-x-1'}
      `}
    >
      {icon}
      <span className="text-[15px]">{label}</span>
    </NavLink>
  );
};

export default StagiaireLayout;
