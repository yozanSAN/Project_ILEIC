import { Upload } from "lucide-react"

const statutStyles = {
  "Validé":     "bg-primary text-white",
  "En attente": "bg-yellow-100 text-yellow-700 border border-yellow-200",
  "Rejeté":     "bg-red-100 text-red-700 border border-red-200",
  "Non soumis": "bg-borderLight text-muted",
}

export default function DocumentsList({ dossier }) {
  const valide = dossier.pieces.filter(p => p.statut === "Validé").length
  const attente = dossier.pieces.filter(p => p.statut === "En attente").length
  const rejete = dossier.pieces.filter(p => p.statut === "Rejeté").length
  const nonSoumis = dossier.pieces.filter(p => p.statut === "Non soumis").length
  const allSubmitted = dossier.pieces.every(p => p.statut !== "Non soumis")

  return (
    <div className="bg-surface rounded-xl border border-borderLight shadow-card w-full overflow-hidden">

      {/* Header */}
      <div className="flex items-center justify-between px-6 py-4 border-b border-borderLight">
        <h2 className="text-base font-bold text-primary">Liste des Pièces Justificatives</h2>
        <button className="flex items-center gap-2 px-4 py-2 rounded-xl border border-primary text-primary text-sm font-medium hover:bg-primary/5 transition-colors">
          <Upload className="w-4 h-4" />
          Télécharger tout le dossier
        </button>
      </div>

      {/* Table */}
      <table className="w-full text-sm">
        <thead>
          <tr className="bg-background/30 border-b border-borderLight">
            <th className="px-6 py-3 text-left text-xs font-bold text-muted uppercase tracking-wider w-[35%]">Document</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-muted uppercase tracking-wider w-[20%]">Actions rapides</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-muted uppercase tracking-wider w-[15%]">Statut</th>
            <th className="px-6 py-3 text-left text-xs font-bold text-muted uppercase tracking-wider w-[30%]">Fichier</th>
          </tr>
        </thead>
        <tbody>
          {dossier.pieces.map((piece) => (
            <tr
              key={piece.id}
              className="border-b border-borderLight last:border-0 hover:bg-background transition-colors"
            >
              {/* Document name */}
              <td className="px-6 py-4">
                <p className="font-medium text-text">{piece.nom}</p>
                <p className="text-xs text-muted mt-0.5">{piece.description}</p>
              </td>

              {/* Actions */}
              <td className="px-6 py-4">
                <div className="flex items-center gap-2">
                  <label className="flex items-center gap-1.5 px-3 py-1.5 rounded-lg border border-borderLight text-xs text-muted hover:bg-background cursor-pointer transition-colors">
                    <Upload className="w-3.5 h-3.5" />
                    {piece.fichier ? "Remplacer" : "Uploader"}
                    <input type="file" className="hidden" accept=".pdf,.jpg,.png" />
                  </label>
                </div>
              </td>

              {/* Statut */}
              <td className="px-6 py-4">
                <span className={`inline-flex items-center px-3 py-1 rounded-full text-xs font-bold ${statutStyles[piece.statut]}`}>
                  {piece.statut}
                </span>
              </td>

              {/* Fichier */}
              <td className="px-6 py-4">
                {piece.fichier ? (
                  <span className="text-xs font-mono text-muted bg-background px-2 py-1 rounded-lg border border-borderLight">
                    {piece.fichier}
                  </span>
                ) : (
                  <span className="text-xs text-muted italic">Aucun fichier</span>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Footer */}
      <div className="px-6 py-4 border-t border-borderLight bg-background/10 flex items-center justify-between">
        <div className="flex items-center gap-4 text-xs text-muted">
          <span className="text-green-600 font-medium">{valide} Validé</span>
          <span className="text-yellow-600 font-medium">{attente} En attente</span>
          <span className="text-red-600 font-medium">{rejete} Rejeté</span>
          <span className="font-medium">{nonSoumis} Non soumis</span>
        </div>
        <button
          disabled={!allSubmitted}
          className={`px-5 py-2 rounded-xl text-sm font-medium transition-colors ${
            allSubmitted
              ? "bg-primary text-white hover:bg-primaryHover cursor-pointer"
              : "bg-borderLight text-muted cursor-not-allowed"
          }`}
        >
          Valider le dossier
        </button>
      </div>
    </div>
  )
}