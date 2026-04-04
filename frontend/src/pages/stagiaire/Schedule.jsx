import React, { useRef, useState } from 'react';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import {
    Calendar,
    ChevronLeft,
    ChevronRight,
    Printer,
    Download,
    MapPin,
    User,
    Clock,
    Loader2
} from "lucide-react";
import MainLayout from "../../components/layout/MainLayout"

/**
 * A simpler and more professional redesign for the student schedule.
 * Focusing on clarity, white space, and a refined color palette.
 */
const Schedule = () => {
    const scheduleRef = useRef(null);
    const [isExporting, setIsExporting] = useState(false);

    const handleDownloadSchedule = async () => {
        if (!scheduleRef.current) return;

        setIsExporting(true);
        try {
            const canvas = await html2canvas(scheduleRef.current, {
                scale: 2,
                useCORS: true,
                backgroundColor: '#ffffff'
            });

            const imgData = canvas.toDataURL('image/png');
            const pdf = new jsPDF({
                orientation: 'l',
                unit: 'mm',
                format: 'a4'
            });

            const pdfWidth = pdf.internal.pageSize.getWidth();
            const pdfHeight = (canvas.height * pdfWidth) / canvas.width;

            pdf.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight);
            pdf.save('Emploi_du_temps_Semaine_42.pdf');
        } catch (error) {
            console.error("Export failed:", error);
            alert("Erreur lors de l'exportation du planning");
        } finally {
            setTimeout(() => setIsExporting(false), 500);
        }
    };

    const days = [
        { name: "Lundi", date: "23 Oct" },
        { name: "Mardi", date: "24 Oct", isToday: true },
        { name: "Mercredi", date: "25 Oct" },
        { name: "Jeudi", date: "26 Oct" },
        { name: "Vendredi", date: "27 Oct" },
        { name: "Samedi", date: "28 Oct" }
    ];

    const timeSlots = [
        "08:30 - 10:30",
        "10:30 - 12:30",
        "14:30 - 16:30",
        "16:30 - 18:30"
    ];

    const courses = [
        // Lundi
        { day: "Lundi", slot: "08:30 - 10:30", title: "Architecture des Ordinateurs", type: "CM", prof: "Pr. Bennani", room: "A102", color: "blue" },
        { day: "Lundi", slot: "14:30 - 16:30", title: "Algorithmique", type: "TD", prof: "Pr. Idrissi", room: "B204", color: "cyan" },
        // Mardi
        { day: "Mardi", slot: "08:30 - 10:30", title: "Développement Web", type: "TP", prof: "Pr. Tazi", room: "Labo 3", color: "purple" },
        { day: "Mardi", slot: "10:30 - 12:30", title: "Développement Web", type: "TP", prof: "Pr. Tazi", room: "Labo 3", color: "purple" },
        { day: "Mardi", slot: "14:30 - 16:30", title: "Base de Données", type: "CM", prof: "Pr. Moussaoui", room: "Amphi A", color: "blue" },
        // Mercredi
        { day: "Mercredi", slot: "10:30 - 12:30", title: "Anglais Technique", type: "TD", prof: "Mme. Smith", room: "C105", color: "cyan" },
        // Jeudi
        { day: "Jeudi", slot: "08:30 - 10:30", title: "Probabilités & Stat.", type: "CM", prof: "Pr. Alami", room: "Amphi B", color: "blue" },
        { day: "Jeudi", slot: "10:30 - 12:30", title: "Probabilités & Stat.", type: "TD", prof: "Pr. Alami", room: "B202", color: "cyan" },
        // Vendredi
        { day: "Vendredi", slot: "14:30 - 16:30", title: "Réseaux Informatiques", type: "TP", prof: "Pr. Benkirane", room: "Labo CISCO", color: "purple" },
        { day: "Vendredi", slot: "16:30 - 18:30", title: "Réseaux Informatiques", type: "TP", prof: "Pr. Benkirane", room: "Labo CISCO", color: "purple" },
    ];

    const typeStyles = {
        blue: "bg-blue-50 text-blue-700 border-blue-200",
        cyan: "bg-cyan-50 text-cyan-700 border-cyan-200",
        purple: "bg-purple-50 text-purple-700 border-purple-200",
        gray: "bg-slate-50 text-slate-400 border-slate-100"
    };

    const getCourse = (day, slot) => courses.find(c => c.day === day && c.slot === slot);

    return (
        <MainLayout>
        <div className="max-w-7xl mx-auto w-full space-y-8 animate-in fade-in duration-700 p-2 sm:p-0">

            {/* Header Section */}
            <div className="flex flex-col lg:flex-row lg:items-end justify-between gap-6">
                <div>
                    <nav className="text-sm text-slate-500 mb-4 flex items-center gap-2">
                        <span>Scolarité</span>
                        <ChevronRight size={14} />
                        <span className="text-slate-900 font-semibold">Emploi du temps</span>
                    </nav>
                    <h1 className="text-4xl font-extrabold text-slate-900 tracking-tight mb-2">Mon Planning</h1>
                    <p className="text-slate-500 text-lg flex items-center gap-2">
                        <Calendar size={20} className="text-slate-400" />
                        Semaine du 23 au 28 Octobre 2023
                    </p>
                </div>

                <div className="flex items-center gap-4 bg-white p-2 rounded-2xl shadow-sm border border-slate-100">
                    <div className="flex items-center gap-1">
                        <button className="p-2 hover:bg-slate-50 rounded-xl transition-colors text-slate-600">
                            <ChevronLeft size={20} />
                        </button>
                        <span className="px-4 font-bold text-slate-900">Semaine 42</span>
                        <button className="p-2 hover:bg-slate-50 rounded-xl transition-colors text-slate-600">
                            <ChevronRight size={20} />
                        </button>
                    </div>
                    <div className="h-8 w-[1px] bg-slate-100 mx-2"></div>
                    <div className="flex items-center gap-2">
                        <button className="p-2.5 bg-slate-50 text-slate-700 rounded-xl hover:bg-slate-100 transition-colors">
                            <Printer size={18} />
                        </button>
                        <button
                            onClick={handleDownloadSchedule}
                            disabled={isExporting}
                            className="flex items-center gap-2 px-5 py-2.5 bg-slate-900 text-white rounded-xl font-bold hover:bg-slate-800 transition-all shadow-lg shadow-slate-200 active:scale-95 disabled:opacity-70"
                        >
                            {isExporting ? <Loader2 size={18} className="animate-spin" /> : <Download size={18} />}
                            <span>{isExporting ? "Chargement..." : "Télécharger PDF"}</span>
                        </button>
                    </div>
                </div>
            </div>

            {/* Main Grid View */}
            <div ref={scheduleRef} className="bg-white rounded-[32px] border border-slate-200 shadow-xl overflow-hidden p-4">
                {/* Day Headers Row */}
                <div className="grid grid-cols-1 md:grid-cols-7 border-b border-slate-100">
                    <div className="hidden md:flex items-center justify-center bg-slate-50/50 p-6 border-r border-slate-100">
                        <Clock size={20} className="text-slate-400" />
                    </div>
                    {days.map((day) => (
                        <div
                            key={day.name}
                            className={`flex-1 p-6 text-center border-r border-slate-100 last:border-r-0 ${day.isToday ? 'bg-indigo-50/30' : ''}`}
                        >
                            <span className="block text-[11px] font-black uppercase tracking-[0.2em] text-slate-400 mb-1">
                                {day.name}
                            </span>
                            <span className={`text-xl font-black ${day.isToday ? 'text-indigo-600' : 'text-slate-900'}`}>
                                {day.date}
                            </span>
                        </div>
                    ))}
                </div>

                {/* Time Grid */}
                <div className="divide-y divide-slate-100">
                    {timeSlots.map((slot) => (
                        <div key={slot} className="grid grid-cols-1 md:grid-cols-7 min-h-[160px]">
                            {/* Time Label */}
                            <div className="hidden md:flex items-center justify-center bg-slate-50/30 p-4 border-r border-slate-100 group">
                                <span className="text-sm font-bold text-slate-400 group-hover:text-slate-600 transition-colors">
                                    {slot.split(' - ')[0]}
                                </span>
                            </div>

                            {/* Day Cells for this Slot */}
                            {days.map((day) => {
                                const course = getCourse(day.name, slot);
                                return (
                                    <div
                                        key={`${day.name}-${slot}`}
                                        className={`p-3 border-r border-slate-100 last:border-r-0 group relative ${day.isToday ? 'bg-indigo-50/10' : ''}`}
                                    >
                                        {course ? (
                                            <div className={`h-full w-full rounded-2xl border p-4 transition-all duration-300 hover:shadow-lg hover:-translate-y-1 ${typeStyles[course.color]}`}>
                                                <div className="flex items-center justify-between mb-3">
                                                    <span className={`text-[10px] font-extrabold uppercase items-center px-2 py-0.5 rounded-md bg-white/50 border border-current`}>
                                                        {course.type}
                                                    </span>
                                                    <span className="text-[10px] items-center text-current/60 font-medium">
                                                        {course.slot.split(' - ')[0]}
                                                    </span>
                                                </div>
                                                <h4 className="font-bold text-[15px] leading-tight mb-4 line-clamp-2">
                                                    {course.title}
                                                </h4>
                                                <div className="space-y-1.5 opacity-80">
                                                    <div className="flex items-center gap-1.5 text-[11px] font-semibold">
                                                        <MapPin size={12} strokeWidth={3} />
                                                        <span>Salle {course.room}</span>
                                                    </div>
                                                    <div className="flex items-center gap-1.5 text-[11px] font-semibold">
                                                        <User size={12} strokeWidth={3} />
                                                        <span>{course.prof}</span>
                                                    </div>
                                                </div>
                                            </div>
                                        ) : (
                                            <div className="h-full w-full flex items-center justify-center opacity-0 group-hover:opacity-100 transition-opacity">
                                                <span className="text-[10px] font-bold text-slate-300 uppercase tracking-widest">Libre</span>
                                            </div>
                                        )}
                                    </div>
                                );
                            })}
                        </div>
                    ))}
                </div>
            </div>

            {/* Legend Footer */}
            <div className="flex flex-wrap items-center justify-center gap-8 py-4">
                <div className="flex items-center gap-3">
                    <div className="w-4 h-4 rounded-lg bg-blue-100 border-2 border-blue-400"></div>
                    <span className="text-xs font-bold text-slate-600 uppercase tracking-wider">Cours Magistral</span>
                </div>
                <div className="flex items-center gap-3">
                    <div className="w-4 h-4 rounded-lg bg-cyan-100 border-2 border-cyan-400"></div>
                    <span className="text-xs font-bold text-slate-600 uppercase tracking-wider">Travaux Dirigés</span>
                </div>
                <div className="flex items-center gap-3">
                    <div className="w-4 h-4 rounded-lg bg-purple-100 border-2 border-purple-400"></div>
                    <span className="text-xs font-bold text-slate-600 uppercase tracking-wider">Travaux Pratiques</span>
                </div>
            </div>
        </div>
        </MainLayout>
    );
};

export default Schedule;
