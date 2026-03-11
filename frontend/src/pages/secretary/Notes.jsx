import { useState } from "react"
import MainLayout from "../../components/layout/MainLayout"
import Filter from "../../components/UI/secretary/notes/Filter"
import NotesTable from "../../components/UI/secretary/notes/NotesTable"
import StatsCards from "../../components/UI/secretary/notes/StatsCards"
import stagiaires from "../../data/secretary/stagiaires.js"
import { calculateStudentYear } from "../../utils/CalculerAnnee.js"
import { calculateSemestre } from "../../utils/calculateSemestre.js"

export default function Notes() {
  const [search, setSearch] = useState("")
  const [selectedFiliere, setSelectedFiliere] = useState("")
  const [selectedAnnee, setSelectedAnnee] = useState("")
  const [selectedSemestre, setSelectedSemestre] = useState(calculateSemestre())

  const filteredStagiaires = stagiaires
    .filter(s => s.name.toLowerCase().includes(search.toLowerCase()))
    .filter(s => selectedFiliere ? s.Filiere === selectedFiliere : true)
    .filter(s => selectedAnnee ? calculateStudentYear(s.AnneeDincription) === selectedAnnee : true)
    .filter(s => selectedSemestre ? s.semestre === selectedSemestre : true)

  return (
    <div>
      <MainLayout>
        <div className='flex flex-col gap-4 w-full mx-5 px-6'>
          <div>
            <h1 className="text-2xl font-bold text-text">Gestion des Notes</h1>
            <p className="text-muted text-sm">Suivi et administration des performance académique</p>
          </div>
          <Filter
            search={search} setSearch={setSearch}
            selectedFiliere={selectedFiliere} setSelectedFiliere={setSelectedFiliere}
            selectedAnnee={selectedAnnee} setSelectedAnnee={setSelectedAnnee}
            selectedSemestre={selectedSemestre} setSelectedSemestre={setSelectedSemestre}
          />
          <NotesTable stagiaires={filteredStagiaires} />
          <StatsCards stagiaires={stagiaires} />
        </div>
      </MainLayout>
    </div>
  )
}