import Sidebar from "./Sidebar"
import Navbar from "./Navbar"
import { secretaryItems } from "../../data/secretary/secretary_sidebar_items"
import user from '../../data/secretary/user'

export default function MainLayout({ children }) {
  return (
    <div>
      <Sidebar items={secretaryItems} />
      <Navbar user={user} />
      <main className="flex-1 overflow-x-hidden overflow-y-auto p-6">
        {children}
      </main>
    </div>
  )
}
