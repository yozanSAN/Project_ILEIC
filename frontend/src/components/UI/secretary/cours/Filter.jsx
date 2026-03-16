import { Search } from "lucide-react"
import cours from "../../../../data/secretary/cours"

export default function Filter({
  search, setSearch,
  selectedFiliere, setSelectedFiliere,
  selectedSemestre, setSelectedSemestre,
  onReset
}) {
  const filieres = [...new Set(cours.map(c => c.filiere))]

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
              placeholder="Rechercher par code, nom ou enseignant..."
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

        {/* Semestre */}
        <div className="flex-1">
          <label className={labelClass}>Semestre</label>
          <select value={selectedSemestre} onChange={e => setSelectedSemestre(Number(e.target.value))} className={selectClass}>
            <option value={1}>Semestre 1</option>
            <option value={2}>Semestre 2</option>
          </select>
        </div>

        {/* Reset */}
        <div className="flex-shrink-0">
          <button
            onClick={onReset}
            className="px-4 py-2 rounded-xl border border-borderLight text-muted text-sm hover:bg-background transition-colors"
          >
            Réinitialiser
          </button>
        </div>

      </div>
    </div>
  )
}