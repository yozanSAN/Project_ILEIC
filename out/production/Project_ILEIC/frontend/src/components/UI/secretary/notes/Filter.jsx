import { Search } from "lucide-react"

export default function Filter({
  search, setSearch,
  selectedFiliere, setSelectedFiliere,
  selectedAnnee, setSelectedAnnee,
  selectedSemestre, setSelectedSemestre
}) {
  return (
    <div className="flex justify-around items-center bg-surface rounded-xl py-5 px-4 w-full">
      {/* Search */}
      <div className='flex flex-col'>
        <label className="block mb-2.5 text-sm font-medium">Recherche</label>
        <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
          <Search className="w-5 h-5 text-text mr-2" />
          <input
            type="text"
            value={search}
            onChange={e => setSearch(e.target.value)}
            placeholder="recherche par nom"
            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
          />
        </div>
      </div>

      {/* Filiere */}
      <div>
        <label className="block mb-2 text-sm font-medium text-heading">Filière (Tous)</label>
        <select
          value={selectedFiliere}
          onChange={e => setSelectedFiliere(e.target.value)}
          className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-sm rounded-xl focus:ring-brand focus:border-brand"
        >
          <option value="">Tout les Filiere</option>
          <option value="Développement Digital">Développement Digital</option>
          <option value="Réseaux Informatiques">Réseaux Informatiques</option>
          <option value="Design Graphique">Design Graphique</option>
          <option value="Marketing Digital">Marketing Digital</option>
          <option value="Développement Web">Développement Web</option>
          <option value="Comptabilité">Comptabilité</option>
          <option value="Gestion des Entreprises">Gestion des Entreprises</option>
          <option value="Intelligence Artificielle">Intelligence Artificielle</option>
        </select>
      </div>

      {/* Annee */}
      <div>
        <label className="block mb-2 text-sm font-medium text-heading">Année (Tous)</label>
        <select
          value={selectedAnnee}
          onChange={e => setSelectedAnnee(e.target.value)}
          className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-xl focus:ring-brand focus:border-brand"
        >
          <option value="">Toutes les années</option>
          <option value="1ère année">1ère année</option>
          <option value="2ème année">2ème année</option>
          <option value="3ème année">3ème année</option>
          <option value="4ème année">4ème année</option>
          <option value="5ème année">5ème année</option>
        </select>
      </div>

      {/* Semestre */}
      <div>
        <label className="block mb-2 text-sm font-medium text-heading">Semestre</label>
        <select
          value={selectedSemestre}
          onChange={e => setSelectedSemestre(Number(e.target.value))}
          className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-xl focus:ring-brand focus:border-brand"
        >
          <option value={1}>1</option>
          <option value={2}>2</option>
        </select>
      </div>
    </div>
  )
}