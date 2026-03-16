import { useState } from "react"
import { calculateStudentYear } from "../../../../utils/CalculerAnnee"

const etatStyles = {
  "Actif":      "border border-borderLight text-muted",
  "En attente": "border border-borderLight text-muted",
  "Archivé":    "text-muted",
}

export default function CoursTable({ cours }) {
  const [hoveredRow, setHoveredRow] = useState(null)

  return (
    <div className="bg-surface rounded-xl border border-borderLight overflow-hidden shadow-card w-full">
      <div className="overflow-x-auto">
        <table className="w-full text-sm">

          <thead>
            <tr className="bg-background/30 border-b border-borderLight">
              {["Code", "Module", "Filière", "Niveau", "Enseignant", "Charge", "État"].map(col => (
                <th key={col} className="px-4 py-4 text-left text-xs font-bold text-muted uppercase tracking-wider">
                  {col}
                </th>
              ))}
            </tr>
          </thead>

          <tbody>
            {cours.length === 0 ? (
              <tr>
                <td colSpan={7} className="px-4 py-10 text-center text-muted text-sm">
                  Aucun cours trouvé
                </td>
              </tr>
            ) : (
              cours.map((c, index) => (
                <tr
                  key={c.id}
                  onMouseEnter={() => setHoveredRow(index)}
                  onMouseLeave={() => setHoveredRow(null)}
                  className={`border-b border-borderLight last:border-0 transition-colors duration-150 ${hoveredRow === index ? "bg-background" : "bg-surface"}`}
                >
                  <td className="px-4 py-4 font-bold text-primary text-sm">
                    {c.code}
                  </td>
                  <td className="px-4 py-4">
                    <p className="font-bold text-text text-sm">{c.module}</p>
                    <p className="text-xs text-muted mt-0.5">
                      ÉDITÉ : {new Date(c.editeLe).toLocaleDateString("fr-FR")}
                    </p>
                  </td>
                  <td className="px-4 py-4 text-muted text-sm">{c.filiere}</td>
                  <td className="px-4 py-4 text-muted text-sm">{calculateStudentYear(c.AnneeDincription)}</td>
                  <td className="px-4 py-4 text-muted text-sm">{c.enseignant}</td>
                  <td className="px-4 py-4 text-muted text-sm">{c.charge}h</td>
                  <td className="px-4 py-4">
                    <span className={`inline-flex items-center px-3 py-0.5 rounded-full text-xs font-bold uppercase tracking-wider ${etatStyles[c.etat]}`}>
                      {c.etat}
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
          {cours.length} cours au total
        </span>
      </div>
    </div>
  )
}