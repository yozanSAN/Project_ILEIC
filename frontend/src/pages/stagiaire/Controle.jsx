import React, { useState } from 'react';
import {
    TrendingUp,
    CheckCircle2,
    Calendar,
    Clock,
    MapPin,
    ChevronRight,
    Eye,
    Search,
    ArrowUpRight,
    Filter
} from "lucide-react";

/**
 * Controle Page - Displays upcoming exams and past results.
 * Matches the professional aesthetic of the student dashboard.
 */
const Controle = () => {
    const [activeFilter, setActiveFilter] = useState("Tout");

    const upcomingExams = [
        {
            id: 1,
            title: "Mathématiques - Algèbre Linéaire",
            duration: "2H00",
            time: "09:00 - 11:00",
            date: "12 Déc 2023",
            location: "Salle B4, Campus Nord",
            type: "Programmé"
        },
        {
            id: 2,
            title: "Physique - Thermodynamique",
            duration: "2H00",
            time: "14:00 - 16:00",
            date: "14 Déc 2023",
            location: "Amphi A, Campus Sud",
            type: "Programmé"
        }
    ];

    const examResults = [
        {
            id: 3,
            title: "Français - Littérature & Communication",
            note: 16,
            maxNote: 20,
            status: "Validé",
            date: "15 Nov 2023",
            percentage: 80
        },
        {
            id: 4,
            title: "Algorithmique Avancée",
            note: 13,
            maxNote: 20,
            status: "Validé",
            date: "10 Nov 2023",
            percentage: 65
        },
        {
            id: 5,
            title: "Base de Données - SQL",
            note: 9.5,
            maxNote: 20,
            status: "Rattrapage",
            date: "05 Nov 2023",
            percentage: 47.5
        }
    ];

    const metrics = [
        {
            label: "Moyenne Générale",
            value: "14.5",
            suffix: "/20",
            subtext: "+0.5 ce semestre",
            icon: <TrendingUp className="text-emerald-500" />,
            bg: "bg-emerald-50"
        },
        {
            label: "Contrôles Passés",
            value: "5",
            suffix: "",
            subtext: "Tous validés",
            icon: <CheckCircle2 className="text-blue-500" />,
            bg: "bg-blue-50"
        }
    ];

    const filteredResults = activeFilter === "Tout"
        ? examResults
        : examResults.filter(r => r.status === activeFilter);

    return (
        <div className="max-w-6xl mx-auto w-full space-y-10 animate-in fade-in duration-700">

            {/* Search and Metrics Header */}
            <div className="flex flex-col lg:flex-row gap-6 items-start lg:items-center justify-between">
                <div className="relative w-full lg:w-96">
                    <Search className="absolute left-4 top-1/2 -translate-y-1/2 text-slate-400" size={18} />
                    <input
                        type="text"
                        placeholder="Rechercher un examen..."
                        className="w-full pl-12 pr-4 py-3 bg-white border border-slate-200 rounded-2xl text-sm focus:ring-2 focus:ring-blue-100 transition-all outline-none shadow-sm placeholder:text-slate-400"
                    />
                </div>

                <div className="flex gap-4 w-full lg:w-auto">
                    {metrics.map((metric, i) => (
                        <div key={i} className="flex-1 lg:w-64 bg-white p-5 rounded-[24px] border border-slate-100 shadow-sm flex items-center gap-4">
                            <div className={`w-12 h-12 rounded-2xl ${metric.bg} flex items-center justify-center shrink-0`}>
                                {metric.icon}
                            </div>
                            <div>
                                <p className="text-[11px] font-bold text-slate-400 uppercase tracking-wider mb-0.5">{metric.label}</p>
                                <div className="flex items-baseline gap-1">
                                    <span className="text-2xl font-black text-slate-900">{metric.value}</span>
                                    <span className="text-sm font-bold text-slate-400">{metric.suffix}</span>
                                </div>
                                <p className="text-[10px] font-bold text-emerald-500 mt-0.5">{metric.subtext}</p>
                            </div>
                        </div>
                    ))}
                </div>
            </div>

            {/* Upcoming Exams */}
            <section className="space-y-6">
                <div className="flex items-center justify-between px-2">
                    <h2 className="text-xl font-black text-slate-900 flex items-center gap-2">
                        Examens Programmés
                        <span className="w-6 h-6 rounded-full bg-blue-100 text-blue-600 text-xs flex items-center justify-center">2</span>
                    </h2>
                    <button className="text-sm font-bold text-blue-600 hover:text-blue-700 transition-colors flex items-center gap-1 group">
                        Voir le calendrier <ChevronRight size={16} className="group-hover:translate-x-0.5 transition-transform" />
                    </button>
                </div>

                <div className="grid grid-cols-1 gap-4">
                    {upcomingExams.map((exam) => (
                        <div key={exam.id} className="bg-white p-6 rounded-[24px] border border-slate-100 shadow-sm hover:shadow-md transition-all group flex flex-col md:flex-row md:items-center justify-between gap-6">
                            <div className="flex items-start gap-5">
                                <div className="w-14 h-14 rounded-2xl bg-slate-50 border border-slate-100 flex flex-col items-center justify-center shrink-0">
                                    <span className="text-[10px] font-black text-slate-400 uppercase leading-none mb-1">Durée</span>
                                    <span className="text-sm font-black text-slate-900 leading-none">{exam.duration}</span>
                                </div>
                                <div>
                                    <div className="flex items-center gap-2 mb-2">
                                        <span className="px-2 py-0.5 rounded-md bg-blue-50 text-blue-600 text-[10px] font-extrabold uppercase tracking-wider border border-blue-100">
                                            {exam.type}
                                        </span>
                                        <span className="text-xs font-bold text-slate-400">{exam.date}</span>
                                    </div>
                                    <h3 className="text-lg font-black text-slate-900 mb-2 leading-tight">{exam.title}</h3>
                                    <div className="flex flex-wrap items-center gap-4 text-slate-500 text-sm font-medium">
                                        <div className="flex items-center gap-1.5">
                                            <Clock size={14} className="text-slate-400" />
                                            {exam.time}
                                        </div>
                                        <div className="flex items-center gap-1.5">
                                            <MapPin size={14} className="text-slate-400" />
                                            {exam.location}
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button className="px-6 py-3 bg-slate-50 text-slate-900 font-bold rounded-xl hover:bg-slate-100 transition-all border border-slate-100 group-hover:border-slate-200">
                                Détails
                            </button>
                        </div>
                    ))}
                </div>
            </section>

            {/* Results History */}
            <section className="space-y-6">
                <div className="flex flex-col sm:flex-row sm:items-center justify-between px-2 gap-4">
                    <h2 className="text-xl font-black text-slate-900">Résultats des Examens</h2>
                    <div className="flex items-center bg-white p-1 rounded-xl border border-slate-100 shadow-sm">
                        {["Tout", "Validés", "Rattrapage"].map((filter) => (
                            <button
                                key={filter}
                                onClick={() => setActiveFilter(filter)}
                                className={`px-5 py-2 text-xs font-bold rounded-lg transition-all ${activeFilter === filter
                                        ? "bg-slate-900 text-white shadow-md shadow-slate-200"
                                        : "text-slate-500 hover:text-slate-900 hover:bg-slate-50"
                                    }`}
                            >
                                {filter}
                            </button>
                        ))}
                    </div>
                </div>

                <div className="grid grid-cols-1 gap-4">
                    {filteredResults.map((result) => (
                        <div key={result.id} className="bg-white p-6 rounded-[24px] border border-slate-100 shadow-sm hover:shadow-md transition-all flex flex-col md:flex-row items-stretch md:items-center justify-between gap-8 relative overflow-hidden">
                            <div className="flex-1 space-y-4">
                                <div className="flex items-center gap-3">
                                    <span className={`px-2 py-0.5 rounded-md text-[10px] font-extrabold uppercase tracking-wider border ${result.status === "Validé"
                                            ? "bg-emerald-50 text-emerald-600 border-emerald-100"
                                            : "bg-orange-50 text-orange-600 border-orange-100"
                                        }`}>
                                        {result.status}
                                    </span>
                                    <span className="text-xs font-bold text-slate-400">{result.date}</span>
                                </div>
                                <h3 className="text-lg font-black text-slate-900 leading-tight">{result.title}</h3>
                                <div className="space-y-2 max-w-md">
                                    <div className="flex justify-between text-xs font-bold text-slate-400">
                                        <span>Performance</span>
                                        <span className={result.status === "Validé" ? "text-emerald-600" : "text-orange-600"}>{result.note}/20</span>
                                    </div>
                                    <div className="h-2 w-full bg-slate-100 rounded-full overflow-hidden">
                                        <div
                                            className={`h-full rounded-full transition-all duration-1000 ${result.status === "Validé" ? "bg-emerald-500" : "bg-orange-500"
                                                }`}
                                            style={{ width: `${result.percentage}%` }}
                                        ></div>
                                    </div>
                                </div>
                            </div>

                            <div className="flex items-center justify-between md:justify-end gap-6 shrink-0">
                                <div className="text-right">
                                    <div className="text-2xl font-black text-slate-900 leading-none mb-1">{result.note}</div>
                                    <div className="text-sm font-bold text-slate-400">/ 20</div>
                                </div>
                                <button className="flex items-center gap-2 px-5 py-3 text-blue-600 font-bold text-sm bg-blue-50/50 hover:bg-blue-50 rounded-xl transition-all group">
                                    <Eye size={18} />
                                    Voir la copie
                                    <ArrowUpRight size={16} className="opacity-0 group-hover:opacity-100 -translate-y-1 transition-all" />
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            </section>

            {/* Footer Copyright */}
            <footer className="pt-10 pb-6 text-center">
                <p className="text-[11px] font-bold text-slate-400 uppercase tracking-widest">
                    © 2024 ILEIC School. Tous droits réservés.
                </p>
            </footer>
        </div>
    );
};

export default Controle;
