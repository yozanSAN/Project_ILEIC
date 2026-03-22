const DAYS = ["Lun", "Mar", "Mer", "Jeu", "Ven", "Sam"]
const TIMES = ["08:30", "10:30", "13:00", "15:00", "17:00"]

const TYPE_STYLES = {
  "cours":   { bg: "bg-blue-50",   text: "text-blue-800",  border: "border-blue-200",  label: "Cours magistral" },
  "tp":      { bg: "bg-green-50",  text: "text-green-800", border: "border-green-200", label: "Travaux pratiques" },
  "atelier": { bg: "bg-purple-50", text: "text-purple-800",border: "border-purple-200",label: "Atelier" },
  "examen":  { bg: "bg-orange-50", text: "text-orange-800",border: "border-orange-200",label: "Examen / Contrôle" },
}

function getWeekDates(offset) {
  const now = new Date()
  const day = now.getDay()
  const monday = new Date(now)
  monday.setDate(now.getDate() - (day === 0 ? 6 : day - 1) + offset * 7)
  return Array.from({ length: 6 }, (_, i) => {
    const d = new Date(monday)
    d.setDate(monday.getDate() + i)
    return d
  })
}

function isToday(date) {
  return date.toDateString() === new Date().toDateString()
}

export default function ScheduleGrid({ slots, weekOffset }) {
  const dates = getWeekDates(weekOffset)

  return (
    <div className="bg-surface rounded-xl border border-borderLight shadow-card w-full overflow-hidden">
      <div className="overflow-x-auto">
        <div style={{ display: "grid", gridTemplateColumns: "70px repeat(6, 1fr)", minWidth: "700px" }}>

          {/* Corner */}
          <div className="bg-background border-b border-r border-borderLight" />

          {/* Day headers */}
          {dates.map((date, i) => (
            <div key={i} className={`border-b border-r border-borderLight last:border-r-0 py-3 text-center ${isToday(date) ? "bg-background" : "bg-background/50"}`}>
              <p className={`text-xs font-bold uppercase tracking-wider ${isToday(date) ? "text-primary" : "text-muted"}`}>
                {DAYS[i]}
              </p>
              {isToday(date) ? (
                <div className="w-8 h-8 rounded-full bg-primary text-white text-base font-medium flex items-center justify-center mx-auto mt-1">
                  {date.getDate()}
                </div>
              ) : (
                <p className="text-lg font-medium text-text mt-1">{date.getDate()}</p>
              )}
            </div>
          ))}

          {/* Time rows */}
          {TIMES.map((time, ti) => (
            <>
              {/* Time label */}
              <div
                key={`time-${ti}`}
                className={`border-r border-borderLight flex items-start justify-center pt-2 text-xs text-muted ${ti === TIMES.length - 1 ? "" : "border-b"}`}
                style={{ minHeight: "88px" }}
              >
                {time}
              </div>

              {/* Slots */}
              {Array.from({ length: 6 }, (_, d) => {
                const slot = slots.find(s => s.day === d && s.time === time)
                const styles = slot ? TYPE_STYLES[slot.type] : null

                return (
                  <div
                    key={`slot-${ti}-${d}`}
                    className={`p-1.5 ${d === 5 ? "" : "border-r"} border-borderLight ${ti === TIMES.length - 1 ? "" : "border-b"}`}
                    style={{ minHeight: "88px" }}
                  >
                    {slot && (
                      <div className={`rounded-lg p-2 h-full border ${styles.bg} ${styles.border}`}>
                        <p className={`text-xs font-bold mb-1 ${styles.text}`}>{slot.nom}</p>
                        <p className={`text-xs ${styles.text} opacity-80`}>{slot.formateur}</p>
                        <p className={`text-xs ${styles.text} opacity-80`}>{slot.salle}</p>
                      </div>
                    )}
                  </div>
                )
              })}
            </>
          ))}
        </div>
      </div>

      {/* Legend */}
      <div className="px-4 py-3 border-t border-borderLight bg-background/20 flex items-center gap-6 flex-wrap">
        {Object.entries(TYPE_STYLES).map(([key, style]) => (
          <div key={key} className="flex items-center gap-2">
            <div className={`w-3 h-3 rounded ${style.bg} border ${style.border}`} />
            <span className="text-xs text-muted">{style.label}</span>
          </div>
        ))}
      </div>
    </div>
  )
}