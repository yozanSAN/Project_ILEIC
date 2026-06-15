// StatsCards.jsx — place this component below the table in your Controles page

const StatsCards = ({ stagiaires }) => {
  const total = stagiaires.length;
  const recus = stagiaires.filter(s => s.Note >= 10).length;
  const echecs = stagiaires.filter(s => s.Note < 10).length;
  const moyenne = total > 0
    ? (stagiaires.reduce((sum, s) => sum + s.Note, 0) / total).toFixed(2)
    : "0.00";
  const meilleure = total > 0
    ? Math.max(...stagiaires.map(s => s.Note)).toFixed(2)
    : "0.00";

  const recusPct = total > 0 ? ((recus / total) * 100).toFixed(1) : 0;
  const echecsPct = total > 0 ? ((echecs / total) * 100).toFixed(1) : 0;

  const cards = [
    {
      label: "Total Reçus",
      value: recus,
      sub: `${recusPct}% des stagiaires`,
      valueColor: "text-green-600",
      icon: "✓",
      iconBg: "bg-green-100",
      iconColor: "text-green-600",
    },
    {
      label: "Total Échecs",
      value: echecs,
      sub: `${echecsPct}% des stagiaires`,
      valueColor: "text-red-600",
      icon: "✗",
      iconBg: "bg-red-100",
      iconColor: "text-red-600",
    },
    {
      label: "Moyenne Générale",
      value: moyenne,
      sub: "sur 20",
      valueColor: "text-gray-800",
      icon: "∑",
      iconBg: "bg-blue-100",
      iconColor: "text-blue-600",
    },
    {
      label: "Meilleure Note",
      value: meilleure,
      sub: "sur 20",
      valueColor: "text-yellow-600",
      icon: "★",
      iconBg: "bg-yellow-100",
      iconColor: "text-yellow-600",
    },
  ];

  return (
    <div className="grid grid-cols-2 lg:grid-cols-4 gap-4 mt-4">
      {cards.map((card) => (
        <div
          key={card.label}
          className="bg-white rounded-xl border border-gray-200 p-5 flex items-center gap-4 shadow-sm"
        >
          <div className={`w-10 h-10 rounded-full flex items-center justify-center text-lg font-bold ${card.iconBg} ${card.iconColor}`}>
            {card.icon}
          </div>
          <div>
            <p className="text-xs text-gray-500 font-medium">{card.label}</p>
            <p className={`text-2xl font-bold ${card.valueColor}`}>{card.value}</p>
            <p className="text-xs text-gray-400">{card.sub}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default StatsCards;