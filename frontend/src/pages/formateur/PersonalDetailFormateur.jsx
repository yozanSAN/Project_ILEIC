import React from 'react';
import { Pencil } from "lucide-react";

export default function PersonalDetailFormateur() {
  return (
    <div className="flex-1 bg-gray-200 p-6 min-h-screen">
      {/* Header */}
      <div className="flex items-center gap-2 text-gray-700 mb-6">
        <span className="text-xl cursor-pointer">←</span>
        <h2 className="font-semibold">Personnel Detail</h2>
      </div>

      {/* Profile Card */}
      <div className="bg-gray-300 rounded-xl max-w-3xl mx-auto shadow-lg">
        {/* Make only the content scrollable */}
        <div className="max-h-[75vh] overflow-y-auto p-8">
          {/* Avatar */}
          <div className="flex justify-center mb-6">
            <div className="relative">
              <img
                src="https://i.pravatar.cc/120"
                alt="profile"
                className="w-24 h-24 rounded-full border-4 border-white object-cover"
              />
              <span className="absolute bottom-1 right-1 w-6 h-6 bg-green-500 rounded-full border-4 border-white"></span>
            </div>
          </div>

          {/* Name */}
          <div className="text-center mb-8">
            <h3 className="font-bold text-xl">Rachid Eliguor</h3>
            <span className="text-sm text-red-600 bg-red-100 px-3 py-1 rounded-full font-medium">
              FORMATEUR SENIOR
            </span>
          </div>

          {/* Form fields */}
          <div className="space-y-6">
            <Input label="Nom complet" value="Rachid Eliguor" />
            <Input label="Email institutionnel" value="religuor@institut.com" />
            <Input label="Téléphone" value="+212 661 123456" />
            <Input label="Spécialisation" value="Mathematics / Computer Science" />
            <Input label="Date d’embauche" value="01 Septembre 1995" />
            {/* You can add more fields here later – they will scroll */}
          </div>

          {/* Button – always visible at bottom of scroll area */}
          <div className="mt-10 flex justify-center pb-4">
            <button className="flex items-center gap-2 bg-red-600 hover:bg-red-700 text-white px-10 py-3 rounded-lg shadow-md font-medium transition">
              <Pencil size={18} />
              Modifier le Profil
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

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
        className="w-full px-4 py-3 rounded-lg bg-white border border-gray-300 text-gray-800 outline-none focus:border-red-500 transition disabled:opacity-70 disabled:cursor-not-allowed"
      />
    </div>
  );
}