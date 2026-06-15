import { useState } from "react"
import { calculateProgramme, calculateMontantMensuel } from "../../../../utils/calculateProgramme"

const TOTAL_MOIS = 10

const getStatut = (moisPaies) => {
  if (moisPaies.length === 0) return "En retard"
  if (moisPaies.length === TOTAL_MOIS) return "À jour"
  return "Partiel"
}

const statutStyles = {
  "À jour":    "bg-green-100 text-green-700",
  "Partiel":   "bg-yellow-100 text-yellow-700",
  "En retard": "bg-red-100 text-red-700",
}

const programmeStyles = {
  "Diplôme": "bg-blue-100 text-blue-700",
  "Licence": "bg-green-100 text-green-700",
  "Master":  "bg-purple-100 text-purple-700",
}

export default function PaiementsTable({ paiements }) {
  const [hoveredRow, setHoveredRow] = useState(null)

  return (
    <div className="bg-surface rounded-xl border border-borderLight overflow-hidden shadow-card w-full">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">

          <thead>
            <tr className="bg-background/30 border-b border-borderLight">
              {["Stagiaire", "Programme", "Mensualité", "Mois payés", "Mois restants", "Total payé", "Reste dû", "Statut", "Actions"].map((col, i) => (
                <th key={col} className={`px-4 py-4 text-xs font-bold text-muted uppercase tracking-wider ${i === 8 ? "text-right" : "text-left"}`}>
                  {col}
                </th>
              ))}
            </tr>
          </thead>

          <tbody>
            {paiements.length === 0 ? (
              <tr>
                <td colSpan={9} className="px-4 py-10 text-center text-muted text-sm">
                  Aucun stagiaire trouvé
                </td>
              </tr>
            ) : (
              paiements.map((s, index) => {
                const programme = calculateProgramme(s.AnneeDincription)
                const mensualite = calculateMontantMensuel(s.AnneeDincription)
                const paye = s.moisPaies.length
                const restant = TOTAL_MOIS - paye
                const totalPaye = paye * mensualite
                const resteDu = restant * mensualite
                const statut = getStatut(s.moisPaies)

                return (
                  <tr
                    key={s.id}
                    onMouseEnter={() => setHoveredRow(index)}
                    onMouseLeave={() => setHoveredRow(null)}
                    className={`border-b border-borderLight last:border-0 transition-colors duration-150 ${hoveredRow === index ? "bg-background" : "bg-surface"}`}
                  >
                    <td className="px-4 py-4">
                      <p className="font-medium text-text">{s.nom} {s.prenom}</p>
                      <p className="text-xs text-muted mt-0.5">{s.filiere}</p>
                    </td>
                    <td className="px-4 py-4">
                      <span className={`inline-flex items-center px-3 py-0.5 rounded-full text-xs font-bold ${programmeStyles[programme]}`}>
                        {programme}
                      </span>
                    </td>
                    <td className="px-4 py-4 font-medium text-text">
                      {mensualite.toLocaleString("fr-FR")} DH
                    </td>
                    <td className="px-4 py-4">
                      <span className={`font-bold text-sm ${paye === TOTAL_MOIS ? "text-green-600" : "text-primary"}`}>
                        {paye} / {TOTAL_MOIS}
                      </span>
                    </td>
                    <td className="px-4 py-4 text-muted">
                      {restant}
                    </td>
                    <td className="px-4 py-4 font-medium text-text">
                      {totalPaye.toLocaleString("fr-FR")} DH
                    </td>
                    <td className={`px-4 py-4 font-medium ${resteDu === 0 ? "text-green-600" : statut === "En retard" ? "text-red-600" : "text-yellow-600"}`}>
                      {resteDu.toLocaleString("fr-FR")} DH
                    </td>
                    <td className="px-4 py-4">
                      <span className={`inline-flex items-center px-3 py-0.5 rounded-full text-xs font-bold ${statutStyles[statut]}`}>
                        {statut}
                      </span>
                    </td>
                    <td className="px-4 py-4 text-right">
                      <button
                        onClick={() => console.log("open modal for", s.id)}
                        className="text-xs px-3 py-1.5 rounded-lg border border-borderLight text-muted hover:bg-background transition-colors mr-2"
                      >
                        Modifier
                      </button>
                      <button className="text-xs px-3 py-1.5 rounded-lg border border-borderLight text-muted hover:bg-background transition-colors">
                        PDF
                      </button>
                    </td>
                  </tr>
                )
              })
            )}
          </tbody>
        </table>
      </div>

      <div className="px-6 py-3 border-t border-borderLight bg-background/10 flex items-center justify-between">
        <span className="text-xs text-muted">
          {paiements.length} stagiaire{paiements.length !== 1 ? "s" : ""} au total
        </span>
      </div>
    </div>
  )
}