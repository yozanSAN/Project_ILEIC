import MainLayout from "../../components/layout/MainLayout"
import Filter from "../../components/UI/secretary/notes/Filter"
import NotesTable from "../../components/UI/secretary/notes/NotesTable"
export default function Notes() {
  return (
    <div>
      <MainLayout>
        <div className='flex flex-col gap-4 w-full mx-5 px-6 '>
          <div>
            <h1 className="text-2xl font-bold text-text">Gestion des Notes</h1>
            <p className="text-muted text-sm ">Suivi et administration des performance académique</p>
          </div>
          <Filter />
          <NotesTable />
        </div>
      </MainLayout>
    </div>
  )
}
