import { useState } from "react"
import { Search } from "lucide-react"
import stagiaires from "../../../../data/secretary/stagiaires"
import { calculateStudentYear } from "../../../../utils/CalculerAnnee"

export default function StudentSelector({ selectedStagiaire, setSelectedStagiaire }) {
  const [search, setSearch] = useState("")
  const [showDropdown, setShowDropdown] = useState(false)

  const filtered = stagiaires.filter(s =>
    s.name.toLowerCase().includes(search.toLowerCase()) ||
    s.numInscription.toLowerCase().includes(search.toLowerCase())
  )

  const handleSelect = (s) => {
    setSelectedStagiaire(s)
    setSearch(s.name)
    setShowDropdown(false)
  }

  const handleReset = () => {
    setSelectedStagiaire(null)
    setSearch("")
    setShowDropdown(false)
  }

  return (
    <div className="bg-surface rounded-xl border border-borderLight p-6 shadow-card w-full">
      <div className="flex items-center justify-between mb-5">
        <div className="flex items-center gap-2">
          <h2 className="text-base font-bold text-primary">Sélection du Stagiaire</h2>
        </div>
        {selectedStagiaire && (
          <span className="text-xs font-mono text-primary font-bold">
            Dossier #{selectedStagiaire.numInscription}
          </span>
        )}
      </div>

      <div className="flex items-end gap-4">
        {/* Search */}
        <div className="w-72 relative">
          <label className="block mb-2 text-xs font-bold text-muted uppercase tracking-wider">
            Nom ou Identifiant ID
          </label>
          <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 bg-background focus-within:ring-2 focus-within:ring-primary/20 transition-colors">
            <input
              type="text"
              value={search}
              onChange={e => { setSearch(e.target.value); setShowDropdown(true) }}
              onFocus={() => setShowDropdown(true)}
              placeholder="Ex: Ayoub ou REG-2023-001"
              className="w-full bg-transparent text-sm text-text placeholder:text-muted focus:outline-none"
            />
            <Search className="w-4 h-4 text-muted ml-2 shrink-0" />
          </div>

          {/* Dropdown */}
          {showDropdown && search && (
            <div className="absolute top-full left-0 right-0 mt-1 bg-surface border border-borderLight rounded-xl shadow-card z-10 max-h-48 overflow-y-auto">
              {filtered.length === 0 ? (
                <p className="px-4 py-3 text-sm text-muted">Aucun stagiaire trouvé</p>
              ) : (
                filtered.map(s => (
                  <button
                    key={s.id}
                    onClick={() => handleSelect(s)}
                    className="w-full text-left px-4 py-3 hover:bg-background transition-colors border-b border-borderLight last:border-0"
                  >
                    <p className="text-sm font-medium text-text">{s.name}</p>
                    <p className="text-xs text-muted">{s.numInscription} — {s.Filiere}</p>
                  </button>
                ))
              )}
            </div>
          )}
        </div>

        {/* Student Info Cards */}
        {selectedStagiaire ? (
          <>
            <div className="flex-1 bg-background rounded-xl px-4 py-3 border border-borderLight">
              <p className="text-xs text-muted uppercase tracking-wider mb-1">Filière</p>
              <p className="text-sm font-medium text-text">{selectedStagiaire.Filiere}</p>
            </div>
            <div className="flex-1 bg-background rounded-xl px-4 py-3 border border-borderLight">
              <p className="text-xs text-muted uppercase tracking-wider mb-1">Année</p>
              <p className="text-sm font-medium text-text">{calculateStudentYear(selectedStagiaire.AnneeDincription)}</p>
            </div>
            <div className="flex-1 bg-background rounded-xl px-4 py-3 border border-borderLight">
              <p className="text-xs text-muted uppercase tracking-wider mb-1">N° Inscription</p>
              <p className="text-sm font-medium text-text font-mono">{selectedStagiaire.numInscription}</p>
            </div>
            <button
              onClick={handleReset}
              className="px-4 py-2 rounded-xl border border-borderLight text-muted text-sm hover:bg-background transition-colors"
            >
              Changer
            </button>
          </>
        ) : (
          <p className="text-sm text-muted italic">Rechercher un stagiaire pour afficher son dossier</p>
        )}
      </div>
    </div>
  )
}