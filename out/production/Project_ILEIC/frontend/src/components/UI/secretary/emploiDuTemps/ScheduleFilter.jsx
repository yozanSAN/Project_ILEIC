import stagiaires from "../../../../data/secretary/stagiaires"
import { calculateStudentYear } from "../../../../utils/CalculerAnnee"

export default function ScheduleFilter({
  selectedAnnee, setSelectedAnnee,
  selectedFiliere, setSelectedFiliere,
  weekOffset, setWeekOffset
}) {
  const filieres = [...new Set(stagiaires.map(s => s.Filiere))]
  const annees = [...new Set(stagiaires.map(s => calculateStudentYear(s.AnneeDincription)))]

  const selectClass = "w-full px-3 py-2 text-sm rounded-xl bg-background border border-borderLight text-text focus:outline-none focus:ring-2 focus:ring-primary/20 transition-colors"
  const labelClass = "block mb-2 text-xs font-bold text-muted uppercase tracking-wider"

  const getWeekLabel = () => {
    const now = new Date()
    const day = now.getDay()
    const monday = new Date(now)
    monday.setDate(now.getDate() - (day === 0 ? 6 : day - 1) + weekOffset * 7)
    const saturday = new Date(monday)
    saturday.setDate(monday.getDate() + 5)
    const months = ["Jan","Fév","Mar","Avr","Mai","Juin","Juil","Août","Sep","Oct","Nov","Déc"]
    return `${monday.getDate()} ${months[monday.getMonth()]} — ${saturday.getDate()} ${months[saturday.getMonth()]} ${saturday.getFullYear()}`
  }

  const bothSelected = selectedAnnee && selectedFiliere

  return (
    <div className="bg-surface rounded-xl border border-borderLight p-5 shadow-card w-full flex items-end justify-between gap-4 flex-wrap">
      <div className="flex items-end gap-4">
        {/* Année */}
        <div>
          <label className={labelClass}>Année</label>
          <select value={selectedAnnee} onChange={e => setSelectedAnnee(e.target.value)} className={selectClass} style={{ minWidth: "180px" }}>
            <option value="">-- Sélectionner une année --</option>
            {annees.map(a => <option key={a} value={a}>{a}</option>)}
          </select>
        </div>

        {/* Filière */}
        <div>
          <label className={labelClass}>Filière</label>
          <select value={selectedFiliere} onChange={e => setSelectedFiliere(e.target.value)} className={selectClass} style={{ minWidth: "200px" }}>
            <option value="">-- Sélectionner une filière --</option>
            {filieres.map(f => <option key={f} value={f}>{f}</option>)}
          </select>
        </div>
      </div>

      {/* Week Navigation */}
      <div className={`flex items-center gap-3 transition-opacity ${bothSelected ? "opacity-100" : "opacity-30 pointer-events-none"}`}>
        <button
          onClick={() => setWeekOffset(0)}
          className="px-3 py-2 rounded-xl border border-primary text-primary text-sm font-medium hover:bg-primary/5 transition-colors"
        >
          Aujourd'hui
        </button>
        <button
          onClick={() => setWeekOffset(w => w - 1)}
          className="px-3 py-2 rounded-xl border border-borderLight text-muted text-sm hover:bg-background transition-colors"
        >
          ←
        </button>
        <span className="text-sm font-medium text-text min-w-[180px] text-center">
          {bothSelected ? getWeekLabel() : "—"}
        </span>
        <button
          onClick={() => setWeekOffset(w => w + 1)}
          className="px-3 py-2 rounded-xl border border-borderLight text-muted text-sm hover:bg-background transition-colors"
        >
          →
        </button>
      </div>
    </div>
  )
}