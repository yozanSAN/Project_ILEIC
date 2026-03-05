import Sidebar from "./Sidebar"
import Navbar from "./Navbar"
import { secretaryItems } from "../../data/secretary/secretary_sidebar_items"
import user from '../../data/secretary/user'

export default function MainLayout({ children }) {
  return (
    <div className="flex h-screen w-full bg-background overflow-hidden">
      
      {/* 1. SIDEBAR: Stays on the left, fixed width */}
      <Sidebar items={secretaryItems} />

      {/* 2. RIGHT WRAPPER: Takes remaining space */}
      <div className="flex flex-col flex-1 min-w-0">
        
        {/* 3. NAVBAR: Stays at the top, perfectly aligned with the content below */}
        <Navbar user={user} />

        {/* 4. THE CENTRAL AREA: This is where the magic happens */}
        <main className="flex-1 overflow-y-auto p-4 md:p-8">
          
          {/* 5. CONTENT LIMITER: Keeps your tables/forms from stretching too wide 
              mx-auto centers it. max-w-7xl keeps it readable on big screens. */}
          <div className="w-full px-6">
            {children}
          </div>
          
        </main>
      </div>
    </div>
  )
}