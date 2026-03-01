import Sidebar from "./Sidebar"
import Navbar from "./Navbar"
import { secretaryItems } from "../../data/secretary/secretary_sidebar_items"
import user from '../../data/secretary/user'

export default function MainLayout() {
  return (
    <div>
      <Sidebar items={secretaryItems}/>
      <Navbar user={user}/>
    </div>
  )
}
