import { Settings, LogOut } from "lucide-react";
import { useLocation, useNavigate } from "react-router-dom";

export default function Sidebar({ items }) {
  const location = useLocation();
  const navigate = useNavigate();

  return (
    <div className="w-64 bg-sidebar h-screen text-white flex flex-col p-4 fixed left-0 top-0 z-50">
      
      {/* 1. Profile Section */}
      <div className="flex flex-col items-center py-8 border-b border-white/10 mb-6">
        <div className="w-20 h-20 rounded-full border-2 border-white overflow-hidden mb-3 shadow-lg">
          <img 
            src="/avatar.png" 
            className="w-full h-full object-cover" 
            alt="User Avatar" 
          />
        </div>
        <h3 className="font-bold text-lg">Mme. Naima</h3>
        <p className="text-[10px] opacity-70 uppercase tracking-[3px] font-medium">
          Administration
        </p>
      </div>

      {/* 2. Dynamic Navigation Links */}
      <nav className="flex-1 space-y-2">
        {items.map((item, index) => {
          const Icon = item.icon;
          // Check if the current URL contains the item path to keep it "Active"
          const isActive = location.pathname.includes(item.path);

          return (
            <button
              key={index}
              onClick={() => navigate(item.path)}
              className={`w-full flex items-center gap-4 px-5 py-3 rounded-l-full transition-all duration-200 group
                ${isActive 
                  ? 'bg-background text-primary font-bold shadow-md' 
                  : 'hover:bg-sidebarHover text-white/80 hover:text-white'
                }`}
            >
              <Icon size={22} strokeWidth={isActive ? 2.5 : 2} />
              <span className="text-sm tracking-wide">{item.label}</span>
              
              {/* Optional: The small "cut-out" effect indicator */}
              {isActive && (
                <div className="absolute right-0 w-2 h-8 bg-background rounded-l-full" />
              )}
            </button>
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