import React from "react";
import { Pencil } from "lucide-react";
import { formateurData } from "./formateurData";
import MainLayout from '../../../components/layout/MainLayout'

export default function PersonalDetailFormateur() {
  const {
    nomComplet,
    email,
    telephone,
    specialisation,
    dateEmbauche,
    role,
    avatar,
    status,
  } = formateurData;

  return (
    <MainLayout>
    <div className="flex-1 bg-gray-200 p-6 min-h-screen">
      {/* Header */}
      <div className="flex items-center gap-2 text-gray-700 mb-6">
        <span className="text-xl cursor-pointer">←</span>
        <h2 className="font-semibold">Personnel Detail</h2>
      </div>

      {/* Profile Card */}
      <div className="bg-gray-300 rounded-xl max-w-3xl mx-auto shadow-lg">
        <div className="max-h-[75vh] overflow-y-auto p-8">

          {/* Avatar */}
          <div className="flex justify-center mb-6">
            <div className="relative">
              <img
                src={avatar}
                alt="profile"
                className="w-24 h-24 rounded-full border-4 border-white object-cover"
              />
              <span
                className={`absolute bottom-1 right-1 w-6 h-6 rounded-full border-4 border-white ${
                  status === "active" ? "bg-green-500" : "bg-gray-400"
                }`}
              />
            </div>
          </div>

          {/* Name */}
          <div className="text-center mb-8">
            <h3 className="font-bold text-xl">{nomComplet}</h3>
            <span className="text-sm text-red-600 bg-red-100 px-3 py-1 rounded-full font-medium">
              {role}
            </span>
          </div>

          {/* Form fields */}
          <div className="space-y-6">
            <Input label="Nom complet" value={nomComplet} />
            <Input label="Email institutionnel" value={email} />
            <Input label="Téléphone" value={telephone} />
            <Input label="Spécialisation" value={specialisation} />
            <Input label="Date d’embauche" value={dateEmbauche} />
          </div>

          {/* Button */}
          <div className="mt-10 flex justify-center pb-4">
            <button className="flex items-center gap-2 bg-red-600 hover:bg-red-700 text-white px-10 py-3 rounded-lg shadow-md font-medium transition">
              <Pencil size={18} />
              Modifier le Profil
            </button>
          </div>
        </div>
      </div>
    </div>
    </MainLayout>
  );
}

/* Reusable Input */
function Input({ label, value }) {
  return (
    <div>
      <label className="block text-xs text-gray-600 uppercase tracking-wide mb-1.5">
        {label}
      </label>
      <input
        type="text"
        value={value}
        disabled
        className="w-full px-4 py-3 rounded-lg bg-white border border-gray-300 text-gray-800 outline-none disabled:opacity-70"
      />
    </div>
   
  );
}