import { Search } from "lucide-react"
import paiements from "../../../../data/secretary/paiements"

export default function Filter({
  search, setSearch,
  selectedFiliere, setSelectedFiliere,
  selectedProgramme, setSelectedProgramme,
  selectedStatut, setSelectedStatut,
  onReset
}) {
  const filieres = [...new Set(paiements.map(p => p.filiere))]

  const selectClass = "w-full px-3 py-2 text-sm rounded-xl bg-background border border-borderLight text-text focus:outline-none focus:ring-2 focus:ring-primary/20 transition-colors"
  const labelClass = "block mb-2 text-xs font-bold text-muted uppercase tracking-wider"

  return (
    <div className="bg-surface rounded-xl border border-borderLight p-6 w-full shadow-card">
      <div className="flex items-end gap-4 w-full">

        {/* Search */}
        <div className="flex-[2]">
          <label className={labelClass}>Recherche</label>
          <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 bg-background focus-within:ring-2 focus-within:ring-primary/20 transition-colors">
            <Search className="w-4 h-4 text-muted mr-2 shrink-0" />
            <input
              type="text"
              value={search}
              onChange={e => setSearch(e.target.value)}
              placeholder="Nom ou prénom..."
              className="w-full bg-transparent text-sm text-text placeholder:text-muted focus:outline-none"
            />
          </div>
        </div>

        {/* Filière */}
        <div className="flex-1">
          <label className={labelClass}>Filière</label>
          <select value={selectedFiliere} onChange={e => setSelectedFiliere(e.target.value)} className={selectClass}>
            <option value="">Toutes les filières</option>
            {filieres.map(f => <option key={f} value={f}>{f}</option>)}
          </select>
        </div>

        {/* Programme */}
        <div className="flex-1">
          <label className={labelClass}>Programme</label>
          <select value={selectedProgramme} onChange={e => setSelectedProgramme(e.target.value)} className={selectClass}>
            <option value="">Tous</option>
            <option value="Diplôme">Diplôme</option>
            <option value="Licence">Licence</option>
            <option value="Master">Master</option>
          </select>
        </div>

        {/* Statut */}
        <div className="flex-1">
          <label className={labelClass}>Statut</label>
          <select value={selectedStatut} onChange={e => setSelectedStatut(e.target.value)} className={selectClass}>
            <option value="">Tous</option>
            <option value="À jour">À jour</option>
            <option value="Partiel">Partiel</option>
            <option value="En retard">En retard</option>
          </select>
        </div>

        {/* Reset */}
        <div className="flex-shrink-0">
          <button onClick={onReset} className="px-4 py-2 rounded-xl border border-borderLight text-muted text-sm hover:bg-background transition-colors">
            Réinitialiser
          </button>
        </div>

      </div>
    </div>
  )
}