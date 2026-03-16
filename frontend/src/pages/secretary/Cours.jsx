import { useState } from "react"
import MainLayout from "../../components/layout/MainLayout"
import Filter from "../../components/UI/secretary/cours/Filter"
import CoursTable from "../../components/UI/secretary/cours/CoursTable"
import cours from "../../data/secretary/cours"
import { calculateStudentYear } from "../../utils/CalculerAnnee"
import { calculateSemestre } from "../../utils/calculateSemestre"

export default function Cours() {
  const [search, setSearch] = useState("")
  const [selectedFiliere, setSelectedFiliere] = useState("")
  const [selectedSemestre, setSelectedSemestre] = useState(calculateSemestre())

  const handleReset = () => {
    setSearch("")
    setSelectedFiliere("")
    setSelectedSemestre(calculateSemestre())
  }

  const filteredCours = cours
    .filter(c =>
      c.module.toLowerCase().includes(search.toLowerCase()) ||
      c.code.toLowerCase().includes(search.toLowerCase()) ||
      c.enseignant.toLowerCase().includes(search.toLowerCase())
    )
    .filter(c => selectedFiliere ? c.filiere === selectedFiliere : true)

  return (
    <MainLayout>
      <div className="flex flex-col gap-6 w-full mx-5 px-6">
        <div>
          <h1 className="text-2xl font-bold text-text">Gestion des Cours</h1>
          <p className="text-muted text-sm">Catalogue académique et planification des modules</p>
        </div>
        <Filter
          search={search} setSearch={setSearch}
          selectedFiliere={selectedFiliere} setSelectedFiliere={setSelectedFiliere}
          selectedSemestre={selectedSemestre} setSelectedSemestre={setSelectedSemestre}
          onReset={handleReset}
        />
        <CoursTable cours={filteredCours} />
      </div>
    </MainLayout>
  )
}