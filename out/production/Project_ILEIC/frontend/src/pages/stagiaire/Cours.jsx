import React, { useState } from 'react';
import jsPDF from 'jspdf';
import {
    Search,
    ChevronDown,
    Download,
    Code,
    Scale,
    Calculator,
    TrendingUp,
    Languages,
    Database,
    ChevronLeft,
    ChevronRight,
    Check
} from "lucide-react";
import MainLayout from "../../components/layout/MainLayout"

const Cours = () => {
    const [searchQuery, setSearchQuery] = useState("");
    const [activeCenter, setActiveCenter] = useState("");
    const [isCenterDropdownOpen, setIsCenterDropdownOpen] = useState(false);

    const centers = ["Tous", "Elbatoire", "Dakhla", "Ait Melloul"];

    const filters = [
        { id: 'filiere', label: 'Filière' },
        { id: 'annees', label: 'Années' },
        { id: 'semester', label: 'Semestre' },
        { id: 'module', label: 'Liste de Module' },
    ];

    const coursesData = [
        {
            id: 1,
            title: "Développement Digital",
            description: "Apprentissage des frameworks modernes de développement web et mobile (React, Node.js).",
            icon: <Code size={24} className="text-blue-500" />,
            iconBg: "bg-blue-50",
            prof: "Prof. El Amrani",
            updated: "1j",
            profAvatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Amrani&backgroundColor=528A6D",
            badge: "IT",
            center: "Elbatoire"
        },
        {
            id: 2,
            title: "Droit des Affaires",
            description: "Introduction au droit commercial, aux contrats et aux statuts juridiques des entreprises.",
            icon: <Scale size={24} className="text-purple-500" />,
            iconBg: "bg-purple-50",
            prof: "Mme. Benjelloun",
            updated: "5h",
            profAvatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Benjelloun&backgroundColor=1e3a8a",
            badge: "Droit",
            center: "Dakhla"
        },
        {
            id: 3,
            title: "Comptabilité Générale",
            description: "Principes fondamentaux de la comptabilité, bilan, compte de résultat et flux de trésorerie.",
            icon: <Calculator size={24} className="text-emerald-500" />,
            iconBg: "bg-emerald-50",
            prof: "M. Tazi",
            updated: "1j",
            profAvatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Tazi&backgroundColor=528A6D",
            badge: "Finance",
            center: "Ait Melloul"
        },
        {
            id: 4,
            title: "Marketing Stratégique",
            description: "Analyse de marché, segmentation, ciblage et positionnement des offres commerciales.",
            icon: <TrendingUp size={24} className="text-orange-500" />,
            iconBg: "bg-orange-50",
            prof: "Mme. Idrissi",
            updated: "3j",
            profAvatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Idrissi&backgroundColor=64748b",
            badge: "Marketing",
            center: "Elbatoire"
        },
        {
            id: 5,
            title: "Anglais des Affaires",
            description: "Business English vocabulary, email writing, and professional communication skills.",
            icon: <Languages size={24} className="text-pink-500" />,
            iconBg: "bg-pink-50",
            prof: "Mr. Smith",
            updated: "1h",
            profAvatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Smith&backgroundColor=cbd5e1",
            badge: "Langues",
            center: "Dakhla"
        },
        {
            id: 6,
            title: "Bases de Données",
            description: "Conception, modélisation (Merise/UML) et requêtage SQL (MySQL, PostgreSQL).",
            icon: <Database size={24} className="text-teal-500" />,
            iconBg: "bg-teal-50",
            prof: "Prof. Alaoui",
            updated: "4j",
            profAvatar: "https://api.dicebear.com/7.x/avataaars/svg?seed=Alaoui&backgroundColor=1e293b",
            badge: "Informatique",
            center: "Ait Melloul"
        }
    ];

    // Logic to handle course download (Generates a sample PDF syllabus)
    const handleDownload = (course) => {
        try {
            const doc = new jsPDF();

            // Add Title
            doc.setFontSize(22);
            doc.setTextColor(30, 41, 59); // slate-800
            doc.text(course.title, 20, 30);

            // Add Line
            doc.setDrawColor(226, 232, 240); // slate-200
            doc.line(20, 35, 190, 35);

            // Add Details
            doc.setFontSize(12);
            doc.setTextColor(100, 116, 139); // slate-500
            doc.text(`Professeur: ${course.prof}`, 20, 50);
            doc.text(`Centre: ${course.center}`, 20, 60);
            doc.text(`Catégorie: ${course.badge || 'Général'}`, 20, 70);

            // Add Description
            doc.setFontSize(14);
            doc.setTextColor(30, 41, 59);
            doc.text("Description du cours :", 20, 90);

            doc.setFontSize(12);
            doc.setTextColor(71, 85, 105);
            const splitDesc = doc.splitTextToSize(course.description, 170);
            doc.text(splitDesc, 20, 100);

            // Save the PDF
            doc.save(`Syllabus_${course.title.replace(/[^a-zA-Z0-9]/g, '_')}.pdf`);
        } catch (error) {
            console.error("Failed to generate PDF:", error);
            alert("Une erreur s'est produite lors de la génération du PDF.");
        }
    };

    // Filter logic
    const filteredCourses = coursesData.filter(course => {
        // 1. Search Query Filter
        const matchesSearch =
            course.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
            course.description.toLowerCase().includes(searchQuery.toLowerCase());

        // 2. Center Filter
        const matchesCenter =
            !activeCenter || activeCenter === "Tous" || course.center === activeCenter;

        return matchesSearch && matchesCenter;
    });

    return (
        <MainLayout>
        <div className="max-w-6xl mx-auto w-full animate-in fade-in duration-500 slide-in-from-bottom-4 flex flex-col min-h-[calc(100vh-120px)]">
            {/* Header section with active search */}
            <div className="flex flex-col md:flex-row md:items-center justify-between gap-4 mb-8 pl-1">
                <div>
                    <h1 className="text-3xl font-bold text-slate-900 tracking-tight leading-tight mb-1">Matériel de Cours</h1>
                    <p className="text-slate-500 font-medium">Semestre 1 - Année 2024/2025</p>
                </div>

                <div className="relative w-full md:w-[350px]">
                    <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none">
                        <Search size={18} className="text-slate-400" />
                    </div>
                    <input
                        type="text"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        placeholder="Rechercher un module ou un mot-clé..."
                        className="w-full pl-11 pr-4 py-3 bg-[#F1F5F9] border-none rounded-full text-sm font-medium focus:ring-2 focus:ring-blue-200 transition-all placeholder:text-slate-400 text-slate-700 shadow-inner"
                    />
                </div>
            </div>

            {/* Filters Dropdowns */}
            <div className="flex flex-wrap items-center justify-start md:justify-end gap-3 mb-10 w-full px-2">

                {/* Active Center Filter */}
                <div className="relative w-full sm:w-auto min-w-[150px]">
                    <button
                        onClick={() => setIsCenterDropdownOpen(!isCenterDropdownOpen)}
                        className={`w-full flex items-center justify-between px-4 py-2 bg-white border ${isCenterDropdownOpen ? 'border-blue-300 rounded-t-xl' : 'border-slate-200 rounded-xl'} text-[13px] font-semibold text-slate-700 shadow-sm transition-colors hover:border-blue-200`}
                    >
                        <span className={activeCenter && activeCenter !== "Tous" ? "text-blue-600" : ""}>
                            {activeCenter && activeCenter !== "Tous" ? activeCenter : "Tous les Centres"}
                        </span>
                        <ChevronDown size={14} className={`text-slate-400 transition-transform ${isCenterDropdownOpen ? 'rotate-180' : ''}`} />
                    </button>

                    {isCenterDropdownOpen && (
                        <div className="absolute top-full left-0 w-full bg-white border-x border-b border-blue-300 rounded-b-xl shadow-lg z-20 overflow-hidden divide-y divide-slate-50">
                            {centers.map(center => (
                                <button
                                    key={center}
                                    onClick={() => {
                                        setActiveCenter(center);
                                        setIsCenterDropdownOpen(false);
                                    }}
                                    className="w-full text-left px-4 py-2.5 text-[13px] font-medium text-slate-600 hover:bg-blue-50 hover:text-blue-700 transition-colors flex items-center justify-between"
                                >
                                    {center}
                                    {activeCenter === center && <Check size={14} className="text-blue-600" />}
                                </button>
                            ))}
                        </div>
                    )}
                </div>

                {/* Other Disabled Filters (Placeholders) */}
                {filters.map((filter) => (
                    <div key={filter.id} className="relative w-full sm:w-auto min-w-[140px] opacity-70 cursor-not-allowed">
                        <button disabled className="w-full flex items-center justify-between px-4 py-2 bg-white/80 border border-slate-200 rounded-xl text-[13px] font-semibold text-slate-500 shadow-sm">
                            <span>{filter.label}</span>
                            <ChevronDown size={14} className="text-slate-300" />
                        </button>
                    </div>
                ))}
            </div>

            {/* Course Cards Grid */}
            {filteredCourses.length > 0 ? (
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6 mb-12">
                    {filteredCourses.map((course) => (
                        <div key={course.id} className="bg-white rounded-[24px] border border-slate-100 shadow-sm hover:shadow-md transition-shadow p-6 flex flex-col min-h-[290px] relative">

                            {course.badge && (
                                <span className="absolute top-6 right-6 px-3 py-1 bg-slate-100 text-slate-600 text-[11px] font-bold rounded-full">
                                    {course.badge}
                                </span>
                            )}

                            <div className={`w-12 h-12 rounded-2xl ${course.iconBg} flex items-center justify-center mb-5 shrink-0`}>
                                {course.icon}
                            </div>

                            <div className="flex-1 mb-4">
                                <h3 className="text-[17px] font-bold text-slate-900 mb-2 leading-tight line-clamp-2">{course.title}</h3>
                                <p className="text-[13px] text-slate-500 font-medium leading-relaxed line-clamp-3">{course.description}</p>
                                <p className="text-[11px] text-slate-400 mt-2 flex items-center gap-1 font-semibold">
                                    <span className="w-1.5 h-1.5 rounded-full bg-slate-300"></span>
                                    Centre {course.center}
                                </p>
                            </div>

                            <div className="mt-auto border-t border-slate-50 pt-4">
                                <div className="flex items-center gap-3 mb-4">
                                    <img
                                        src={course.profAvatar}
                                        alt={course.prof}
                                        className="w-9 h-9 rounded-full border border-slate-100 object-cover bg-slate-50"
                                    />
                                    <div>
                                        <p className="text-[13px] font-bold text-slate-800 leading-none mb-1.5">{course.prof}</p>
                                        <p className="text-[10px] text-slate-400 uppercase tracking-wider font-semibold">
                                            Mis à jour: {course.updated}
                                        </p>
                                    </div>
                                </div>

                                <button
                                    onClick={() => handleDownload(course)}
                                    className="w-full flex flex-row items-center justify-center py-2.5 bg-gradient-to-r from-[#06b6d4] to-[#0ea5e9] hover:from-[#0891b2] hover:to-[#0284c7] text-white rounded-xl font-bold transition-all shadow-sm hover:shadow-cyan-500/20 group gap-2"
                                >
                                    <Download size={18} className="group-hover:-translate-y-0.5 transition-transform" />
                                    <span className="text-[14px]">Télécharger PDF</span>
                                </button>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <div className="flex-1 flex flex-col items-center justify-center text-center py-12 px-4">
                    <div className="w-20 h-20 bg-slate-100 rounded-full flex items-center justify-center mb-4">
                        <Search size={32} className="text-slate-400" />
                    </div>
                    <h3 className="text-lg font-bold text-slate-900 mb-2">Aucun module trouvé</h3>
                    <p className="text-slate-500 max-w-md">
                        Nous n'avons trouvé aucun cours correspondant à votre recherche. Essayez de modifier vos filtres ou vos termes de recherche.
                    </p>
                    <button
                        onClick={() => { setSearchQuery(""); setActiveCenter("Tous"); }}
                        className="mt-6 px-6 py-2 bg-slate-900 text-white rounded-full font-medium text-sm hover:bg-slate-800 transition-colors"
                    >
                        Réinitialiser la recherche
                    </button>
                </div>
            )}

            {/* Pagination - Only display if there are courses */}
            {filteredCourses.length > 0 && (
                <div className="flex items-center justify-center gap-2 mt-auto pb-4 pt-8">
                    <button className="w-8 h-8 flex items-center justify-center rounded-full text-slate-400 hover:text-slate-600 hover:bg-slate-100 transition-colors">
                        <ChevronLeft size={16} strokeWidth={2.5} />
                    </button>
                    <button className="w-8 h-8 flex items-center justify-center rounded-full bg-slate-900 text-white text-sm font-bold shadow-sm">
                        1
                    </button>
                    <button className="w-8 h-8 flex items-center justify-center rounded-full text-slate-600 text-sm font-bold hover:bg-slate-100 transition-colors">
                        2
                    </button>
                    <button className="w-8 h-8 flex items-center justify-center rounded-full text-slate-400 hover:text-slate-600 hover:bg-slate-100 transition-colors">
                        <ChevronRight size={16} strokeWidth={2.5} />
                    </button>
                </div>
            )}
        </div>
        </MainLayout>
    );
};

export default Cours;
