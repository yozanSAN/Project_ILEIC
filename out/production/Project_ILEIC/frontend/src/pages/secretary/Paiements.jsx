import { useState } from "react"
import MainLayout from "../../components/layout/MainLayout"
import StatsCards from "../../components/UI/secretary/payments/StatsCards"
import Filter from "../../components/UI/secretary/payments/Filter"
import PaiementsTable from "../../components/UI/secretary/payments/PaiementsTable"
import paiements from "../../data/secretary/paiements"
import { calculateProgramme } from "../../utils/calculateProgramme"

export default function Paiements() {
  const [search, setSearch] = useState("")
  const [selectedFiliere, setSelectedFiliere] = useState("")
  const [selectedProgramme, setSelectedProgramme] = useState("")
  const [selectedStatut, setSelectedStatut] = useState("")

  const getStatut = (moisPaies) => {
    if (moisPaies.length === 0) return "En retard"
    if (moisPaies.length === 10) return "À jour"
    return "Partiel"
  }

  const handleReset = () => {
    setSearch("")
    setSelectedFiliere("")
    setSelectedProgramme("")
    setSelectedStatut("")
  }

  const filteredPaiements = paiements
    .filter(s => (s.nom + " " + s.prenom).toLowerCase().includes(search.toLowerCase()))
    .filter(s => selectedFiliere ? s.filiere === selectedFiliere : true)
    .filter(s => selectedProgramme ? calculateProgramme(s.AnneeDincription) === selectedProgramme : true)
    .filter(s => selectedStatut ? getStatut(s.moisPaies) === selectedStatut : true)

  return (
    <MainLayout>
      <div className="flex flex-col gap-6 w-full mx-5 px-6">
        <div>
          <h1 className="text-2xl font-bold text-text">Gestion des Paiements</h1>
          <p className="text-muted text-sm">Suivez les mensualités des stagiaires avec précision</p>
        </div>
        <StatsCards paiements={paiements} />
        <Filter
          search={search} setSearch={setSearch}
          selectedFiliere={selectedFiliere} setSelectedFiliere={setSelectedFiliere}
          selectedProgramme={selectedProgramme} setSelectedProgramme={setSelectedProgramme}
          selectedStatut={selectedStatut} setSelectedStatut={setSelectedStatut}
          onReset={handleReset}
        />
        <PaiementsTable paiements={filteredPaiements} />
      </div>
    </MainLayout>
  )
}