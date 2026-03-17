import { calculateMontantMensuel } from "../../../../utils/calculateProgramme"

const TOTAL_MOIS = 10

export default function StatsCards({ paiements }) {
  const totalPaye = paiements.reduce((sum, s) => sum + s.moisPaies.length * calculateMontantMensuel(s.AnneeDincription), 0)
  const totalDu = paiements.reduce((sum, s) => sum + (TOTAL_MOIS - s.moisPaies.length) * calculateMontantMensuel(s.AnneeDincription), 0)
  const total = totalPaye + totalDu
  const taux = total > 0 ? Math.round(totalPaye / total * 100) : 0
  const retard = paiements.filter(s => s.moisPaies.length === 0).length
  const aJour = paiements.filter(s => s.moisPaies.length === TOTAL_MOIS).length

  const cards = [
    {
      label: "Total encaissé",
      value: totalPaye.toLocaleString("fr-FR") + " DH",
      sub: aJour + " stagiaires à jour",
      valueColor: "text-primary"
    },
    {
      label: "Reste à percevoir",
      value: totalDu.toLocaleString("fr-FR") + " DH",
      sub: retard + " stagiaire" + (retard !== 1 ? "s" : "") + " en retard",
      valueColor: "text-red-600"
    },
    {
      label: "Taux de recouvrement",
      value: taux + "%",
      sub: "sur l'ensemble des stagiaires",
      valueColor: "text-text"
    },
    {
      label: "Total stagiaires",
      value: paiements.length,
      sub: "toutes filières",
      valueColor: "text-text"
    },
  ]

  return (
    <div className="grid grid-cols-4 gap-3 w-full">
      {cards.map(card => (
        <div key={card.label} className="bg-surface rounded-xl border border-borderLight p-5 shadow-card">
          <p className="text-xs text-muted mb-1">{card.label}</p>
          <p className={`text-2xl font-bold ${card.valueColor}`}>{card.value}</p>
          <p className="text-xs text-muted mt-1">{card.sub}</p>
        </div>
      ))}
    </div>
  )
}