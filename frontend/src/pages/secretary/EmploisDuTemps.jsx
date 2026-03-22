import { useState } from "react"
import MainLayout from "../../components/layout/MainLayout"
import ScheduleFilter from "../../components/UI/secretary/emploiDuTemps/ScheduleFilter"
import ScheduleGrid from "../../components/UI/secretary/emploiDuTemps/ScheduleGrid"
import emploiDuTemps from "../../data/secretary/emploiDuTemps"

export default function EmploisDuTemps() {
  const [selectedAnnee, setSelectedAnnee] = useState("")
  const [selectedFiliere, setSelectedFiliere] = useState("")
  const [weekOffset, setWeekOffset] = useState(0)

  const bothSelected = selectedAnnee && selectedFiliere

  const filteredSlots = bothSelected
    ? emploiDuTemps.filter(s => s.annee === selectedAnnee && s.filiere === selectedFiliere)
    : []

  return (
    <MainLayout>
      <div className="flex flex-col gap-6 w-full mx-5 px-6">
        <div>
          <h1 className="text-2xl font-bold text-text">Emploi du Temps</h1>
          <p className="text-muted text-sm">Planification hebdomadaire des cours par filière et année</p>
        </div>

        <ScheduleFilter
          selectedAnnee={selectedAnnee} setSelectedAnnee={setSelectedAnnee}
          selectedFiliere={selectedFiliere} setSelectedFiliere={setSelectedFiliere}
          weekOffset={weekOffset} setWeekOffset={setWeekOffset}
        />

        {bothSelected ? (
          <ScheduleGrid slots={filteredSlots} weekOffset={weekOffset} />
        ) : (
          <div className="bg-surface rounded-xl border border-borderLight p-16 text-center shadow-card">
            <p className="text-muted text-sm">Veuillez sélectionner une année et une filière</p>
            <p className="text-muted text-xs mt-2 opacity-70">L'emploi du temps s'affichera ici</p>
          </div>
        )}
      </div>
    </MainLayout>
  )
}