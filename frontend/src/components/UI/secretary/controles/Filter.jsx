// src/components/UI/secretary/controles/Filter.jsx
import { RotateCcw } from "lucide-react"
import controles from "../../../../data/secretary/controles"

export default function Filter({
    selectedFiliere, setSelectedFiliere,
    selectedAnnee, setSelectedAnnee,
    selectedCours, setSelectedCours,
    selectedType, setSelectedType,
    onReset
}) {
    // Derive unique options dynamically from the data
    const filieres = [...new Set(controles.map(c => c.cours))]
    const cours = [...new Set(controles.map(c => c.cours))]

    const selectClass = `
    w-full px-3 py-2 text-sm rounded-2xl
    bg-[rgba(239,230,222,0.3)] border border-[#e2d8cf]
    text-text focus:outline-none focus:ring-2 focus:ring-[#990003]/20
    focus:border-[#990003]/40 transition-colors
  `

    const labelClass = "block mb-2 text-xs font-bold text-muted uppercase tracking-wider"

    return (
        <div className="bg-surface rounded-2xl border border-[#e2d8cf] p-6 w-full shadow-sm">
            <div className="flex items-end gap-6 w-full">

                {/* Filière */}
                <div className="flex-1">
                    <label className={labelClass}>Filière</label>
                    <select
                        value={selectedFiliere}
                        onChange={e => setSelectedFiliere(e.target.value)}
                        className={selectClass}
                    >
                        <option value="">Toutes les filières</option>
                        {filieres.map(f => (
                            <option key={f} value={f}>{f}</option>
                        ))}
                    </select>
                </div>

                {/* Année */}
                <div className="flex-1">
                    <label className={labelClass}>Année</label>
                    <select
                        value={selectedAnnee}
                        onChange={e => setSelectedAnnee(e.target.value)}
                        className={selectClass}
                    >
                        <option value="">Toutes les années</option>
                        <option value="1ère année">1ère année</option>
                        <option value="2ème année">2ème année</option>
                        <option value="3ème année">3ème année</option>
                        <option value="4ème année">4ème année</option>
                        <option value="5ème année">5ème année</option>
                    </select>
                </div>

                {/* Cours */}
                <div className="flex-1">
                    <label className={labelClass}>Cours</label>
                    <select
                        value={selectedCours}
                        onChange={e => setSelectedCours(e.target.value)}
                        className={selectClass}
                    >
                        <option value="">Tous les cours</option>
                        {cours.map(c => (
                            <option key={c} value={c}>{c}</option>
                        ))}
                    </select>
                </div>

                {/* Type */}
                {/* Type */}
                <div className="flex-1">
                    <label className={labelClass}>Type</label>
                    <select
                        value={selectedType}
                        onChange={e => setSelectedType(e.target.value)}
                        className={selectClass}
                    >
                        <option value="">Tous les types</option>
                        <option value="Contrôle">Contrôle</option>
                        <option value="Final">Final</option>
                    </select>
                </div>

                {/* Reset Button */}
                <div className="flex-1">
                    <button
                        onClick={onReset}
                        className="w-full flex items-center justify-center gap-2 px-4 py-2 rounded-2xl border border-[#e2d8cf] text-muted text-sm hover:bg-[rgba(239,230,222,0.5)] transition-colors"
                    >
                        <RotateCcw className="w-4 h-4" />
                        Réinitialiser
                    </button>
                </div>

            </div>
        </div>
    )
}