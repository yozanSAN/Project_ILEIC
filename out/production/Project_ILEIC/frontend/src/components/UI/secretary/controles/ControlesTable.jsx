import { useState } from "react"
import { calculateStudentYear } from "../../../../utils/CalculerAnnee"

const statutStyles = {
  "Planifié": "bg-primary/10 text-primary border border-primary/20",
  "Terminé":  "bg-primary text-white",
  "Archivé":  "bg-borderLight text-muted",
}

export default function ControlesTable({ controles }) {
  const [hoveredRow, setHoveredRow] = useState(null)

  return (
    <div className="bg-surface rounded-xl border border-borderLight overflow-hidden shadow-card w-full">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">

          <thead>
            <tr className="bg-background/30 border-b border-borderLight">
              {["Code", "Intitulé", "Cours", "Année", "Type", "Date", "Coeff.", "Statut"].map(col => (
                <th
                  key={col}
                  className={`px-4 py-4 text-xs font-bold text-muted uppercase tracking-wider ${col === "Coeff." ? "text-center" : "text-left"}`}
                >
                  {col}
                </th>
              ))}
            </tr>
          </thead>

          <tbody>
            {controles.length === 0 ? (
              <tr>
                <td colSpan={8} className="px-4 py-10 text-center text-muted text-sm">
                  Aucun contrôle trouvé
                </td>
              </tr>
            ) : (
              controles.map((controle, index) => (
                <tr
                  key={controle.id}
                  onMouseEnter={() => setHoveredRow(index)}
                  onMouseLeave={() => setHoveredRow(null)}
                  className={`border-b border-borderLight last:border-0 transition-colors duration-150 ${hoveredRow === index ? "bg-background" : "bg-surface"}`}
                >
                  <td className="px-4 py-4 font-mono font-bold text-primary text-xs leading-5">
                    {controle.code}
                  </td>
                  <td className="px-4 py-4 font-bold text-text text-sm">
                    {controle.intitule}
                  </td>
                  <td className="px-4 py-4 text-muted text-sm">
                    {controle.cours}
                  </td>
                  <td className="px-4 py-4 text-muted text-sm">
                    {calculateStudentYear(controle.AnneeDincription)}
                  </td>
                  <td className="px-4 py-4 text-muted text-sm">
                    {controle.type}
                  </td>
                  <td className="px-4 py-4 text-muted text-sm">
                    {new Date(controle.date).toLocaleDateString("fr-FR")}
                  </td>
                  <td className="px-4 py-4 text-center font-bold text-text text-sm">
                    {controle.coefficient}
                  </td>
                  <td className="px-4 py-4">
                    <span className={`inline-flex items-center px-3 py-0.5 rounded-full text-xs font-bold ${statutStyles[controle.statut]}`}>
                      {controle.statut}
                    </span>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>

      <div className="px-6 py-3 border-t border-borderLight bg-background/10 flex items-center justify-between">
        <span className="text-xs text-muted">
          {controles.length} contrôle{controles.length !== 1 ? "s" : ""} au total
        </span>
      </div>
    </div>
  )
}