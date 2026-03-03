import React, { useState } from "react";
import { ArrowLeft, CheckCircle2, ChevronLeft, ChevronRight } from "lucide-react";

export default function EtatFormateur() {
  // Form state
  const [formData, setFormData] = useState({
    dateSeance: "",
    horaire: "",
    duree: "",
    annee: "2ème Année",
    centre: "",
    filiere: "",
    groupe: "",
    uf: "",
    numSequence: "",
    numObjectif: "",
    deroulement: "",
    observations: "",
    finSeance: "",
  });

  const [showSuccess, setShowSuccess] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleVider = () => {
    setFormData({
      dateSeance: "",
      horaire: "",
      duree: "",
      annee: "2ème Année",
      centre: "",
      filiere: "",
      groupe: "",
      uf: "",
      numSequence: "",
      numObjectif: "",
      deroulement: "",
      observations: "",
      finSeance: "",
    });
  };

  const handleValider = () => {
    console.log("Validation des données :", formData);
    setShowSuccess(true);
    setTimeout(() => setShowSuccess(false), 3000);
  };

  return (
    <div className="h-screen flex flex-col bg-[#ECECEC] overflow-hidden">
      {/* HEADER - fixed height */}
      <div className="shrink-0 bg-white border-b px-6 py-4 flex items-center gap-3">
        <ArrowLeft className="cursor-pointer text-gray-700" size={24} />
        <h1 className="font-semibold text-gray-700">Etat</h1>
      </div>

      {/* MAIN CONTENT AREA - takes remaining height, allows internal scroll */}
      <div className="flex-1 overflow-hidden px-6 py-6">
        <div className="h-full max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-3 gap-8 overflow-hidden">
          {/* LEFT COLUMN - scrolls with parent if needed */}
          <div className="lg:col-span-2 space-y-6 overflow-y-auto pr-2 scrollbar-thin scrollbar-thumb-gray-400 scrollbar-track-gray-100">
            {/* INFORMATIONS */}
            <div className="bg-white rounded-xl p-6 shadow-sm">
              <div className="flex items-center gap-3 mb-6">
                <h2 className="text-sm font-semibold text-gray-700 uppercase">
                  Informations de la séance
                </h2>
                <span className="text-xs bg-blue-100 text-blue-600 px-3 py-1 rounded-full">
                  Session #442
                </span>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 text-sm">
                <Input
                  label="Date Séance"
                  type="date"
                  name="dateSeance"
                  value={formData.dateSeance}
                  onChange={handleChange}
                />
                <Input
                  label="Horaire"
                  type="time"
                  name="horaire"
                  value={formData.horaire}
                  onChange={handleChange}
                />
                <Input
                  label="Durée (h)"
                  placeholder="ex: 4"
                  name="duree"
                  value={formData.duree}
                  onChange={handleChange}
                />
                <Select
                  label="Année"
                  name="annee"
                  value={formData.annee}
                  onChange={handleChange}
                  options={["1ère Année", "2ème Année", "3ème Année"]}
                />
                <Select
                  label="Centre"
                  col
                  name="centre"
                  value={formData.centre}
                  onChange={handleChange}
                  options={[
                    "ILEIC Agadir",
                    "ILEIC Dakhla",
                    "ILEIC Inzegane",
                    "ILEIC Ait Melloul",
                  ]}
                />
                <Select
                  label="Filière"
                  col
                  name="filiere"
                  value={formData.filiere}
                  onChange={handleChange}
                  options={[
                    "Développement Informatique",
                    "Réseaux Informatiques",
                    "Gestion Informatisée",
                    "Finance et Comptabilité",
                    "Assistant en gestion administrative et comptable",
                  ]}
                />
                <Input
                  label="Groupe"
                  placeholder="DEV201"
                  name="groupe"
                  value={formData.groupe}
                  onChange={handleChange}
                />
                <Input
                  label="U.F"
                  placeholder="Installation & Optimisation"
                  name="uf"
                  value={formData.uf}
                  onChange={handleChange}
                />
                <Input
                  label="Num Séquence"
                  name="numSequence"
                  value={formData.numSequence}
                  onChange={handleChange}
                />
                <Input
                  label="Num Objectif"
                  name="numObjectif"
                  value={formData.numObjectif}
                  onChange={handleChange}
                />
              </div>
            </div>

            {/* TABS */}
            <div className="bg-white rounded-xl shadow-sm">
              <div className="flex gap-8 px-6 pt-4 border-b text-sm">
                <button className="pb-3 border-b-2 border-blue-600 text-blue-600 font-medium">
                  Déroulement
                </button>
                <button className="pb-3 text-gray-400">Volumes UF</button>
                <button className="pb-3 text-gray-400">Stagiaires (24)</button>
              </div>
              <div className="p-6 space-y-5">
                <Textarea
                  label="Déroulement Séance"
                  name="deroulement"
                  value={formData.deroulement}
                  onChange={handleChange}
                />
                <Textarea
                  label="Observations"
                  name="observations"
                  value={formData.observations}
                  onChange={handleChange}
                />
                <Textarea
                  label="Fin Séance / Séance Prochaine"
                  name="finSeance"
                  value={formData.finSeance}
                  onChange={handleChange}
                />
              </div>
            </div>
          </div>

          {/* RIGHT COLUMN - independent scroll */}
          <div className="space-y-6 overflow-y-auto pr-2 scrollbar-thin scrollbar-thumb-gray-400 scrollbar-track-gray-100">
            {/* VOLUMES UF */}
            <div className="bg-white rounded-xl p-6 shadow-sm">
              <h3 className="font-semibold text-sm mb-4 text-gray-700">
                VOLUMES HORAIRES U.F
              </h3>
              <table className="w-full text-sm">
                <thead>
                  <tr className="border-b text-gray-500">
                    <th className="py-2 text-left">SEQ</th>
                    <th>THÉORIE</th>
                    <th>PRATIQUE</th>
                    <th>RÉALISÉES</th>
                  </tr>
                </thead>
                <tbody>
                  <tr className="border-b">
                    <td className="py-3">10</td>
                    <td>4h</td>
                    <td>8h</td>
                    <td className="text-green-600 font-medium">12h</td>
                  </tr>
                  <tr>
                    <td className="py-3">11</td>
                    <td>2h</td>
                    <td>4h</td>
                    <td className="text-blue-600 font-medium">6h</td>
                  </tr>
                </tbody>
              </table>
            </div>

            {/* STAGIAIRES */}
            <div className="bg-white rounded-xl p-6 shadow-sm">
              <div className="flex justify-between mb-4">
                <h3 className="font-semibold text-sm">Liste des stagiaires</h3>
                <div className="flex gap-2 text-xs">
                  <button className="p-1 rounded-full hover:bg-gray-100">
                    <ChevronLeft size={18} />
                  </button>
                  <button className="bg-blue-600 text-white px-3 py-1 rounded-full">
                    Tous
                  </button>
                  <button className="bg-gray-200 px-3 py-1 rounded-full">
                    Aucun
                  </button>
                  <button className="p-1 rounded-full hover:bg-gray-100">
                    <ChevronRight size={18} />
                  </button>
                </div>
              </div>
              <div className="max-h-72 overflow-y-auto space-y-3">
                {["ALAOUUI Mohamed", "BENANI Sofia", "BENANI Sofia", "BENANI Sofia"].map(
                  (name, i) => (
                    <div
                      key={i}
                      className="flex justify-between items-center border-b pb-2"
                    >
                      <div className="flex items-center gap-3">
                        <div className="w-9 h-9 rounded-full bg-blue-100 flex items-center justify-center text-blue-600 font-semibold">
                          {name[0]}
                        </div>
                        <div>
                          <p className="font-medium text-sm">{name}</p>
                          <p className="text-xs text-gray-400">
                            Code: 204439{i}
                          </p>
                        </div>
                      </div>
                      <input type="checkbox" className="w-5 h-5 accent-blue-600" />
                    </div>
                  )
                )}
              </div>
              <button className="text-blue-600 text-sm mt-4">
                Gérer toutes les absences →
              </button>
            </div>

            {/* OBJECTIFS */}
            <div className="bg-blue-50 border border-blue-100 rounded-xl p-5">
              <div className="flex items-center gap-2 mb-3">
                <CheckCircle2 className="text-blue-600" size={18} />
                <h3 className="font-medium text-blue-700">
                  Objectifs Pédagogiques
                </h3>
              </div>
              <ul className="list-disc pl-5 text-sm text-gray-700 space-y-1">
                <li>Comprendre l’architecture micro-services</li>
                <li>Authentification JWT</li>
                <li>Persistance MongoDB</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      {/* FIXED BOTTOM BAR */}
      <div className="shrink-0 bg-white border-t px-6 py-4 flex flex-wrap gap-4 justify-between items-center shadow-lg">
        <button
          onClick={handleValider}
          className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg flex items-center gap-2 transition"
        >
          <CheckCircle2 size={18} />
          Valider
        </button>

        <div className="flex gap-3 flex-wrap">
          <button
            onClick={handleVider}
            className="px-5 py-3 bg-gray-200 hover:bg-gray-300 rounded-lg transition"
          >
            Vider
          </button>
          <button className="px-5 py-3 bg-gray-100 hover:bg-gray-200 rounded-lg transition">
            Mes États
          </button>
          <button className="px-5 py-3 bg-red-500 hover:bg-red-600 text-white rounded-lg transition">
            Fermer
          </button>
        </div>
      </div>

      {/* Success feedback */}
      {showSuccess && (
        <div className="fixed bottom-20 left-1/2 -translate-x-1/2 bg-green-600 text-white px-6 py-3 rounded-lg shadow-lg z-50">
          Séance validée avec succès !
        </div>
      )}
    </div>
  );
}

/* ===== REUSABLE COMPONENTS ===== */
function Input({ label, type = "text", placeholder, name, value, onChange }) {
  return (
    <div>
      <label className="text-xs text-gray-500">{label}</label>
      <input
        type={type}
        name={name}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        className="w-full mt-1 p-3 rounded-lg bg-gray-50 border outline-none focus:border-blue-500"
      />
    </div>
  );
}

function Select({ label, options, col, name, value, onChange }) {
  return (
    <div className={col ? "sm:col-span-2" : ""}>
      <label className="text-xs text-gray-500">{label}</label>
      <select
        name={name}
        value={value}
        onChange={onChange}
        className="w-full mt-1 p-3 rounded-lg bg-gray-50 border outline-none focus:border-blue-500"
      >
        {options.map((opt, i) => (
          <option key={i}>{opt}</option>
        ))}
      </select>
    </div>
  );
}

function Textarea({ label, name, value, onChange }) {
  return (
    <div>
      <label className="text-sm font-medium">{label}</label>
      <textarea
        name={name}
        value={value}
        onChange={onChange}
        className="w-full mt-2 h-28 p-3 border rounded-lg resize-none outline-none focus:border-blue-500"
        placeholder="..."
      />
    </div>
  );
}