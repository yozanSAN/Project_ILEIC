// src/components/UI/secretary/controles/ControlesTable.jsx
import { useState } from "react"
import { calculateStudentYear } from "../../../../utils/CalculerAnnee"

const statutStyles = {
  "Planifié": "bg-[rgba(153,0,3,0.1)] text-[#990003] border border-[rgba(153,0,3,0.2)]",
  "Terminé":  "bg-[#990003] text-white",
  "Archivé":  "bg-[#e2e8f0] text-[#475569]",
}

export default function ControlesTable({ controles }) {
  const [hoveredRow, setHoveredRow] = useState(null)

  return (
    <div className="bg-surface rounded-xl border border-[#e2d8cf] overflow-hidden shadow-sm w-full">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">

          {/* Header */}
          <thead>
            <tr className="bg-[rgba(239,230,222,0.3)] border-b border-[#e2d8cf]">
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

          {/* Body */}
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
                  className={`
                    border-b border-[rgba(226,216,207,0.5)] last:border-0
                    transition-colors duration-150
                    ${hoveredRow === index ? "bg-background" : "bg-surface"}
                  `}
                >
                  {/* Code */}
                  <td className="px-4 py-4">
                    <span className="font-mono font-bold text-[#990003] text-xs leading-5 whitespace-pre-line">
                      {controle.code.replace("-", "-\n")}
                    </span>
                  </td>

                  {/* Intitulé */}
                  <td className="px-4 py-4 font-bold text-text text-sm">
                    {controle.intitule}
                  </td>

                  {/* Cours */}
                  <td className="px-4 py-4 text-muted text-sm">
                    {controle.cours}
                  </td>

                  {/* Année */}
                  <td className="px-4 py-4 text-muted text-sm">
                    {calculateStudentYear(controle.AnneeDincription)}
                  </td>

                  {/* Type */}
                  <td className="px-4 py-4 text-muted text-sm">
                    {controle.type}
                  </td>

                  {/* Date */}
                  <td className="px-4 py-4 text-muted text-sm">
                    {new Date(controle.date).toLocaleDateString("fr-FR")}
                  </td>

                  {/* Coefficient */}
                  <td className="px-4 py-4 text-center font-bold text-text text-sm">
                    {controle.coefficient}
                  </td>

                  {/* Statut */}
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

      {/* Footer */}
      <div className="px-6 py-3 border-t border-[#e2d8cf] bg-[rgba(239,230,222,0.1)] flex items-center justify-between">
        <span className="text-xs text-muted">
          {controles.length} contrôle{controles.length !== 1 ? "s" : ""} au total
        </span>
      </div>
    </div>
  )
}