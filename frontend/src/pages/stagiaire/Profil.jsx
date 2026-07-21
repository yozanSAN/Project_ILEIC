import { useState, useEffect } from "react";
import { GraduationCap, MapPin, Monitor, Calendar, Mail, Phone, IdCard, Cake, Home } from "lucide-react";
import MainLayout from "../../components/layout/MainLayout";
import { getAllStagiaires } from "../../services/stagiaireService";
import { useAuthStore } from "../../store/authStore";

const Profil = () => {
    const email = useAuthStore((state) => state.email);
    const [profile, setProfile] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const stagiaires = await getAllStagiaires();
                const userProfile = stagiaires.find(s => s.email === email);
                setProfile(userProfile);
            } catch (error) {
                console.error("Failed to load profile", error);
            } finally {
                setLoading(false);
            }
        };
        fetchProfile();
    }, [email]);

    if (loading) {
        return (
            <MainLayout>
                <div className="flex justify-center p-10">Chargement du profil...</div>
            </MainLayout>
        );
    }

    if (!profile) {
        return (
            <MainLayout>
                <div className="flex justify-center p-10 text-red-500">Profil introuvable pour cet email.</div>
            </MainLayout>
        );
    }

    return (
        <MainLayout>
        <div className="max-w-5xl mx-auto w-full animate-in fade-in duration-500 slide-in-from-bottom-4">
            <div className="mb-8 pl-1">
                <h1 className="text-3xl font-bold text-slate-900 mb-2 tracking-tight">Profil de l'étudiant</h1>
                <p className="text-slate-500">Gérez vos informations personnelles et votre parcours académique.</p>
            </div>

            {/* Header Card */}
            <div className="bg-white rounded-[24px] shadow-sm shadow-slate-200/50 border border-slate-100 p-8 mb-8">
                <div className="flex flex-col md:flex-row items-center md:items-start gap-6">
                    <div className="relative shrink-0">
                        <div className="w-32 h-32 rounded-full bg-teal-50 border-[6px] border-teal-50 flex items-end justify-center overflow-hidden">
                            <img
                                src={`https://api.dicebear.com/7.x/avataaars/svg?seed=${profile.fullName}&backgroundColor=transparent&accessories=none&clothes=blazerAndShirt&eyebrows=defaultNatural&eyes=default&facialHair=beardLight&mouth=default&skinColor=edb98a&top=shortHairShortWaved`}
                                alt={`Avatar ${profile.fullName}`}
                                className="w-full h-full object-cover scale-110 translate-y-2"
                            />
                        </div>
                        <span className="absolute bottom-2 right-2 w-5 h-5 bg-green-500 border-[3px] border-white rounded-full"></span>
                    </div>

                    <div className="flex-1 pt-2 flex flex-col items-center md:items-start text-center md:text-left">
                        <div className="flex flex-wrap items-center justify-center md:justify-start gap-3 mb-2">
                            <h2 className="text-[26px] font-bold text-slate-900 tracking-tight leading-none">{profile.fullName}</h2>
                            <span className="px-3 py-1 rounded-full bg-green-100 text-green-700 text-[11px] font-bold tracking-wider uppercase shadow-sm">
                                {profile.status || "Actif"}
                            </span>
                        </div>
                        <p className="text-slate-500 text-[17px] mb-5 font-medium">Étudiant - {profile.filiereName}</p>

                        <div className="flex flex-wrap items-center gap-3 text-sm font-medium text-slate-600">
                            <div className="flex flex-1 md:flex-none items-center justify-center gap-2 bg-slate-50 px-4 py-2 rounded-xl border border-slate-100 shadow-sm">
                                <GraduationCap size={18} className="text-slate-400" />
                                Promo {profile.enrollmentDate ? profile.enrollmentDate.split('-')[0] : "N/A"}
                            </div>
                            <div className="flex flex-1 md:flex-none items-center justify-center gap-2 bg-slate-50 px-4 py-2 rounded-xl border border-slate-100 shadow-sm">
                                <MapPin size={18} className="text-slate-400" />
                                {profile.centreName || "Casablanca, MA"}
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            {/* Info Grid */}
            <div className="bg-white rounded-[24px] shadow-sm shadow-slate-200/50 border border-slate-100 p-8 sm:p-10">
                <div className="grid grid-cols-1 md:grid-cols-2 gap-x-12 gap-y-8">
                    <InfoItem label="FILIÈRE" value={profile.filiereName || "Non assignée"} icon={<Monitor size={22} className="text-slate-400" />} />
                    <InfoItem label="DATE D'INSCRIPTION" value={profile.enrollmentDate || "N/A"} icon={<Calendar size={22} className="text-slate-400" />} />
                    <InfoItem label="EMAIL" value={profile.email} icon={<Mail size={22} className="text-slate-400" />} />
                    <InfoItem label="TÉLÉPHONE" value={profile.phone || "N/A"} icon={<Phone size={22} className="text-slate-400" />} />
                    <InfoItem label="CNE / CODE MASSAR" value={profile.registrationNumber || profile.cin || "N/A"} icon={<IdCard size={22} className="text-slate-400" />} />
                    <InfoItem label="DATE DE NAISSANCE" value={profile.birthDate || "N/A"} icon={<Cake size={22} className="text-slate-400" />} />
                    <InfoItem label="ADRESSE" value={profile.address || "N/A"} icon={<Home size={22} className="text-slate-400" />} fullWidth />
                </div>
            </div>
        </div>
        </MainLayout>
    );
};

const InfoItem = ({ label, value, icon, fullWidth }) => {
    return (
        <div className={`${fullWidth ? 'md:col-span-2' : ''} group`}>
            <p className="text-[11px] font-bold text-slate-400 tracking-[0.08em] mb-2 uppercase ml-1 group-hover:text-slate-500 transition-colors">{label}</p>
            <div className="flex items-center gap-4 bg-[#F8FAFC] group-hover:bg-slate-100/80 transition-colors rounded-2xl px-5 py-3.5 border border-slate-100/80">
                <div className="bg-white p-2 rounded-xl shadow-sm border border-slate-100/50">
                    {icon}
                </div>
                <p className="text-slate-800 font-medium text-[15px]">{value}</p>
            </div>
        </div>
    );
};

export default Profil;
