import React, { useRef, useState } from 'react';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import {
    Download,
    TrendingUp,
    Award,
    CheckCircle2,
    Sigma,
    FlaskConical,
    Languages,
    BookOpen,
    Code,
    Loader2
} from "lucide-react";
import MainLayout from "../../components/layout/MainLayout"

const Notes = () => {
    const printRef = useRef(null);
    const [isExporting, setIsExporting] = useState(false);

    const handleExportPDF = async () => {
        if (!printRef.current) return;

        setIsExporting(true);
        try {
            // Small delay to ensure any rendering is complete
            await new Promise(resolve => setTimeout(resolve, 100));

            const canvas = await html2canvas(printRef.current, {
                scale: 2, // Higher quality
                useCORS: true,
                logging: false,
                backgroundColor: '#ffffff'
            });

            const imgData = canvas.toDataURL('image/png');
            const pdf = new jsPDF({
                orientation: 'p',
                unit: 'mm',
                format: 'a4'
            });

            const pdfWidth = pdf.internal.pageSize.getWidth();
            const pdfHeight = (canvas.height * pdfWidth) / canvas.width;

            pdf.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight);
            pdf.save('Releve_de_Notes_Amine_Benali.pdf');
        } catch (error) {
            console.error('Erreur lors de la génération du PDF:', error);
            alert('Une erreur est survenue lors de l\'exportation.');
        } finally {
            setIsExporting(false);
        }
    };

    const tableData = [
        {
            id: 1,
            matiere: "Mathématiques Avancées",
            module: "Analyse",
            icon: <Sigma size={20} className="text-blue-500" />,
            iconBg: "bg-blue-50",
            prof: "Pr. El Amrani",
            coef: 4,
            note: "16.50",
            resultText: "Validé",
            resultColor: "bg-emerald-100 text-emerald-700",
        },
        {
            id: 2,
            matiere: "Physique Quantique",
            module: "Physique",
            icon: <FlaskConical size={20} className="text-purple-500" />,
            iconBg: "bg-purple-50",
            prof: "Pr. Benali",
            coef: 3,
            note: "14.00",
            resultText: "Validé",
            resultColor: "bg-emerald-100 text-emerald-700",
        },
        {
            id: 3,
            matiere: "Anglais Technique",
            module: "Langues",
            icon: <Languages size={20} className="text-orange-500" />,
            iconBg: "bg-orange-50",
            prof: "Ms. Smith",
            coef: 2,
            note: "11.50",
            resultText: "Passable",
            resultColor: "bg-orange-100 text-orange-700",
        },
        {
            id: 4,
            matiere: "Philosophie",
            module: "Sciences Humaines",
            icon: <BookOpen size={20} className="text-red-500" />,
            iconBg: "bg-red-50",
            prof: "Pr. Tazi",
            coef: 2,
            note: "08.50",
            resultText: "Rattrapage",
            resultColor: "bg-red-100 text-red-700",
        },
        {
            id: 5,
            matiere: "Programmation Web",
            module: "Informatique",
            icon: <Code size={20} className="text-teal-500" />,
            iconBg: "bg-teal-50",
            prof: "Pr. Idrissi",
            coef: 3,
            note: "17.00",
            resultText: "Excellent",
            resultColor: "bg-emerald-100 text-emerald-700",
        }
    ];

    return (
        <MainLayout>
        <div className="max-w-5xl mx-auto w-full animate-in fade-in duration-500 slide-in-from-bottom-4">
            {/* Header section */}
            <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4 mb-8 pl-1">
                <div>
                    <h1 className="text-3xl font-bold text-slate-900 tracking-tight leading-tight mb-1">Relevé de Notes</h1>
                    <p className="text-slate-500 font-medium">Année Universitaire 2023-2024</p>
                </div>
                <button
                    onClick={handleExportPDF}
                    disabled={isExporting}
                    className="flex items-center gap-2 px-5 py-2.5 bg-white border border-slate-200 text-slate-700 rounded-xl font-medium hover:bg-slate-50 hover:text-slate-900 transition-colors shadow-sm self-start sm:self-auto disabled:opacity-70 disabled:cursor-not-allowed"
                >
                    {isExporting ? <Loader2 size={18} className="animate-spin" /> : <Download size={18} />}
                    <span>{isExporting ? "Création..." : "Exporter PDF"}</span>
                </button>
            </div>

            {/* Content to Export */}
            <div ref={printRef} className="bg-slate-50 p-2 sm:p-0">
                {/* Top Cards Grid */}
                <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 mb-8">

                    {/* Main Average Card */}
                    <div className="lg:col-span-2 bg-gradient-to-br from-[#1E293B] to-[#1E3A8A] rounded-[24px] p-8 text-white relative overflow-hidden shadow-lg shadow-blue-900/20">
                        {/* Subtle background decoration */}
                        <div className="absolute -top-24 -right-24 w-64 h-64 bg-white/5 rounded-full blur-3xl"></div>

                        <div className="relative z-10 flex flex-col md:flex-row md:items-center justify-between gap-6 h-full">
                            <div>
                                <p className="text-blue-200 text-[11px] font-bold tracking-[0.1em] uppercase mb-3">Moyenne Générale</p>
                                <div className="flex items-baseline gap-2 mb-4">
                                    <span className="text-6xl font-bold tracking-tight leading-none text-white">14.50</span>
                                    <span className="text-blue-200 text-2xl font-medium">/ 20</span>
                                </div>
                                <div className="inline-flex items-center gap-1.5 px-3 py-1.5 rounded-full bg-white/10 border border-white/10 text-emerald-400 text-sm font-medium mt-1">
                                    <TrendingUp size={16} />
                                    <span>+0.5 pts vs Semestre 1</span>
                                </div>
                            </div>

                            <div className="text-left md:text-right flex flex-col md:items-end justify-center pt-2">
                                <h3 className="text-3xl font-bold mb-1">Bien</h3>
                                <p className="text-blue-200 text-sm mb-5">Mention Honorifique</p>

                                <div className="w-full md:w-48">
                                    <div className="h-1.5 w-full bg-blue-950/50 rounded-full overflow-hidden mb-2">
                                        <div className="h-full bg-cyan-400 rounded-full w-[85%]"></div>
                                    </div>
                                    <p className="text-blue-200 text-[11px] uppercase tracking-wider text-left md:text-right">Top 15% de la promotion</p>
                                </div>
                            </div>
                        </div>
                    </div>

                    {/* Small Cards */}
                    <div className="flex flex-col gap-6">
                        {/* Credits Card */}
                        <div className="bg-white rounded-[24px] p-6 border border-slate-100 shadow-sm flex flex-col justify-between flex-1">
                            <div className="flex items-start justify-between mb-2">
                                <div className="w-12 h-12 rounded-2xl bg-indigo-50 border border-indigo-100 flex items-center justify-center text-indigo-500">
                                    <Award size={24} />
                                </div>
                                <span className="px-3 py-1 rounded-lg bg-slate-50 text-slate-500 text-xs font-semibold border border-slate-100">
                                    Annuel
                                </span>
                            </div>
                            <div>
                                <p className="text-slate-500 text-sm font-medium mb-1">Crédits Validés</p>
                                <div className="flex items-baseline gap-1">
                                    <span className="text-3xl font-bold text-slate-900">54</span>
                                    <span className="text-slate-400 font-medium">/ 60</span>
                                </div>
                            </div>
                        </div>

                        {/* Modules Card */}
                        <div className="bg-white rounded-[24px] p-6 border border-slate-100 shadow-sm flex flex-col justify-between flex-1">
                            <div className="flex items-start justify-between mb-2">
                                <div className="w-12 h-12 rounded-2xl bg-emerald-50 border border-emerald-100 flex items-center justify-center text-emerald-500">
                                    <CheckCircle2 size={24} />
                                </div>
                                <span className="px-3 py-1 rounded-lg bg-emerald-50 text-emerald-600 text-xs font-semibold border border-emerald-100/50">
                                    Succès
                                </span>
                            </div>
                            <div>
                                <p className="text-slate-500 text-sm font-medium mb-1">Modules Validés</p>
                                <div className="flex items-baseline gap-1">
                                    <span className="text-3xl font-bold text-slate-900">9</span>
                                    <span className="text-slate-400 font-medium">/ 10</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {/* Tabs */}
                <div className="flex items-center gap-8 border-b border-slate-200 mb-6 px-2">
                    <button className="text-slate-500 font-medium pb-4 hover:text-slate-800 transition-colors">
                        Semestre 1
                    </button>
                    <button className="text-blue-600 font-semibold pb-4 border-b-2 border-blue-600 pt-0.5">
                        Semestre 2
                    </button>
                    <button className="text-slate-500 font-medium pb-4 hover:text-slate-800 transition-colors">
                        Annuel
                    </button>
                </div>

                {/* Table */}
                <div className="bg-white rounded-[24px] border border-slate-100 shadow-sm overflow-hidden mb-8">
                    <div className="overflow-x-auto">
                        <table className="w-full text-left border-collapse">
                            <thead>
                                <tr className="border-b border-slate-100">
                                    <th className="py-5 px-6 text-[11px] font-bold text-slate-400 uppercase tracking-widest bg-slate-50/50">Matière</th>
                                    <th className="py-5 px-6 text-[11px] font-bold text-slate-400 uppercase tracking-widest bg-slate-50/50">Professeur</th>
                                    <th className="py-5 px-6 text-[11px] font-bold text-slate-400 uppercase tracking-widest bg-slate-50/50 text-center">Coefficient</th>
                                    <th className="py-5 px-6 text-[11px] font-bold text-slate-400 uppercase tracking-widest bg-slate-50/50 text-center">Note / 20</th>
                                    <th className="py-5 px-6 text-[11px] font-bold text-slate-400 uppercase tracking-widest bg-slate-50/50 text-right">Résultat</th>
                                </tr>
                            </thead>
                            <tbody className="divide-y divide-slate-50">
                                {tableData.map((row) => (
                                    <tr key={row.id} className="hover:bg-slate-50/50 transition-colors group">
                                        <td className="py-4 px-6 min-w-[250px]">
                                            <div className="flex items-center gap-4">
                                                <div className={`w-10 h-10 rounded-xl ${row.iconBg} flex items-center justify-center shrink-0`}>
                                                    {row.icon}
                                                </div>
                                                <div>
                                                    <p className="font-semibold text-slate-900">{row.matiere}</p>
                                                    <p className="text-[13px] text-slate-500 mt-0.5 font-medium">Module: {row.module}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td className="py-4 px-6 text-slate-500 text-[14px] font-medium min-w-[150px]">{row.prof}</td>
                                        <td className="py-4 px-6 text-slate-600 text-sm font-semibold text-center">{row.coef}</td>
                                        <td className="py-4 px-6 text-center">
                                            <span className="font-bold text-slate-800 text-[15px]">{row.note}</span>
                                        </td>
                                        <td className="py-4 px-6 text-right w-[140px]">
                                            <span className={`inline-flex min-w-[90px] items-center justify-center px-3 py-1.5 rounded-full text-[11px] font-bold uppercase tracking-wide ${row.resultColor}`}>
                                                {row.resultText}
                                            </span>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
                {/* End Content to Export */}
            </div>
        </div>
        </MainLayout>
    );
};

export default Notes;
