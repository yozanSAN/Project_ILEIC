import React, { useState } from 'react'
import stagiaires from '../../../../data/secretary/stagiaires'
import { calculateStudentYear } from '../../../../utils/CalculerAnnee'

export default function Table() {
  const [hoveredRow, setHoveredRow] = useState(null)

  return (
    <div className=" bg-background w-full">
      
      {/* Table Card */}
      <div
        className="bg-surface rounded-xl overflow-hidden"
        style={{ boxShadow: '0 4px 10px rgba(0,0,0,0.05)' }}
      >
        <div />

        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead>
              <tr className="border-b border-borderLight">
                <th className="text-left px-6 py-4 text-xs font-semibold text-muted uppercase tracking-widest">
                  Nom &amp; Prénom
                </th>
                <th className="text-left px-6 py-4 text-xs font-semibold text-muted uppercase tracking-widest">
                  Filière
                </th>
                <th className="text-left px-6 py-4 text-xs font-semibold text-muted uppercase tracking-widest">
                  Année
                </th>
                <th className="text-left px-6 py-4 text-xs font-semibold text-muted uppercase tracking-widest">
                  CNE
                </th>
              </tr>
            </thead>
            <tbody>
              {stagiaires.map((stagiair, index) => (
                <tr
                  key={stagiair.CNE || index}
                  onMouseEnter={() => setHoveredRow(index)}
                  onMouseLeave={() => setHoveredRow(null)}
                  className={`
                    border-b border-borderLight last:border-0
                    transition-colors duration-150
                    ${hoveredRow === index ? 'bg-background' : 'bg-surface'}
                  `}
                >
                  {/* Name cell */}
                  <td className="px-6 py-4 font-medium text-text">
                    {stagiair.name}
                  </td>

                  {/* Filière */}
                  <td className="px-6 py-4">
                    <span
                      className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium"
                      style={{
                        backgroundColor: 'rgba(154,0,2,0.08)',
                        color: '#9A0002',
                      }}
                    >
                      {stagiair.Filiere}
                    </span>
                  </td>

                  {/* Année */}
                  <td className="px-6 py-4 text-text font-medium">
                    {calculateStudentYear(stagiair.AnneeDincription)}
                  </td>

                  {/* CNE */}
                  <td className="px-6 py-4 text-muted font-mono text-xs tracking-wide">
                    {stagiair.CNE}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Footer */}
        <div className="px-6 py-3 border-t border-borderLight bg-background flex items-center justify-between">
          <span className="text-xs text-muted">
            {stagiaires.length} stagiaire{stagiaires.length !== 1 ? 's' : ''} au total
          </span>
          <div className="flex items-center gap-1">
            <div className="w-2 h-2 rounded-full bg-primary opacity-60" />
            <div className="w-2 h-2 rounded-full bg-primary opacity-40" />
            <div className="w-2 h-2 rounded-full bg-primary opacity-20" />
          </div>
        </div>
      </div>
    </div>
  )
}