import { Settings, LogOut } from "lucide-react";
import { useLocation, useNavigate, NavLink } from "react-router-dom"; 

export default function Sidebar({ items }) {
  const location = useLocation();
  const navigate = useNavigate();

  return (
    <div className="w-64 bg-sidebar min-h-screen text-white flex flex-col p-4  ">
      
      {/* 1. Profile Section */}
      <div className="flex flex-col items-center py-8 border-b border-white/10 mb-6">
        <div className="w-20 h-20 rounded-full border-2 border-white overflow-hidden mb-3 shadow-lg">
          <img 
            src="/logo-ileic.svg" 
            className="w-full h-full object-cover" 
            alt="school avatar" 
          />
        </div>
        <h3 className="font-bold text-lg">ILEIC</h3>
      </div>

      {/* 2. Navigation Links – using NavLink for automatic isActive */}
      <nav className="flex-1 space-y-2">
        {items.map((item) => {
          const Icon = item.icon;

          return (
            <NavLink
              key={item.path}
              to={item.path}                    // ← full path from data
              className={({ isActive }) =>
                `w-full flex items-center gap-4 px-5 py-3 rounded-l-full transition-all duration-200 group relative
                ${isActive 
                  ? 'bg-background text-primary font-bold shadow-md' 
                  : 'hover:bg-sidebarHover text-white/80 hover:text-white'
                }`
              }
              end={item.path === "/formateur"}    // optional: only exact match for dashboard
            >
              {({ isActive }) => (
                <>
                  <Icon size={22} strokeWidth={isActive ? 2.5 : 2} />
                  <span className="text-sm">{item.label}</span>
                  
                  {isActive && (
                    <div className="absolute right-0 w-2 h-8 bg-background rounded-l-full" />
                  )}
                </>
              )}
            </NavLink>
          );
        })}
      </nav>

      {/* 3. Bottom Actions */}
      <div className="pt-4 border-t border-white/10 space-y-1">
        <button className="w-full flex items-center gap-4 px-5 py-2.5 text-sm opacity-70 hover:opacity-100 hover:bg-sidebarHover rounded-lg transition-all">
          <Settings size={18} />
          <span>Paramètres</span>
        </button>
        <button className="w-full flex items-center gap-4 px-5 py-2.5 text-sm text-red-200 opacity-70 hover:opacity-100 hover:bg-red-900/30 rounded-lg transition-all">
          <LogOut size={18} />
          <span>Déconnexion</span>
        </button>
      </div>
    </div>
  );
}