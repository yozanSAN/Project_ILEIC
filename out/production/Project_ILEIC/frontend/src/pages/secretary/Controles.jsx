import { useState } from "react"
import MainLayout from "../../components/layout/MainLayout"
import Filter from "../../components/UI/secretary/controles/Filter"
import ControlesTable from "../../components/UI/secretary/controles/ControlesTable"
import controles from "../../data/secretary/controles"
import { calculateStudentYear } from "../../utils/CalculerAnnee"

export default function Controles() {
  const [selectedFiliere, setSelectedFiliere] = useState("")
  const [selectedAnnee, setSelectedAnnee] = useState("")
  const [selectedCours, setSelectedCours] = useState("")
  const [selectedType, setSelectedType] = useState("")

  const handleReset = () => {
    setSelectedFiliere("")
    setSelectedAnnee("")
    setSelectedCours("")
    setSelectedType("")
  }

  const filteredControles = controles
    .filter(c => selectedFiliere ? c.cours === selectedFiliere : true)
    .filter(c => selectedAnnee ? calculateStudentYear(c.AnneeDincription) === selectedAnnee : true)
    .filter(c => selectedCours ? c.cours === selectedCours : true)
    .filter(c => selectedType ? c.type === selectedType : true)

  return (
    <MainLayout>
      <div className="flex flex-col gap-6 w-full mx-5 px-6">
        <div>
          <h1 className="text-2xl font-bold text-text">Gestion des Contrôles</h1>
          <p className="text-muted text-sm">Planification et suivi des évaluations académiques</p>
        </div>
        <Filter
          selectedFiliere={selectedFiliere} setSelectedFiliere={setSelectedFiliere}
          selectedAnnee={selectedAnnee} setSelectedAnnee={setSelectedAnnee}
          selectedCours={selectedCours} setSelectedCours={setSelectedCours}
          selectedType={selectedType} setSelectedType={setSelectedType}
          onReset={handleReset}
        />
        <ControlesTable controles={filteredControles} />
      </div>
    </MainLayout>
  )
}