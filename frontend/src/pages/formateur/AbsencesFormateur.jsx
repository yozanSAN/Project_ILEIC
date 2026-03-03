import React from 'react'
import {
  ArrowLeft,
  Search,
  Plus,
  Clock,
  Pencil,
  FileText,
} from "lucide-react";
export default function AbsencesFormateur() {
  return (
    <div className="min-h-screen bg-[#E6E6E6] px-6 py-6">

    {/* HEADER */}
    <div className="flex items-center gap-3 mb-6">
      <ArrowLeft className="cursor-pointer" />
      <h1 className="text-lg font-semibold">Absences</h1>
    </div>

    {/* FILTER BAR */}
    <div className="flex flex-wrap items-center gap-4 mb-8">
      <Select label="Center" />
      <Select label="Filiere" />

      <button className="flex items-center gap-2 bg-white px-4 py-3 rounded-xl shadow text-sm">
        📅 Aujourd'hui, 24 Mai
      </button>

      <div className="flex items-center bg-white px-4 py-3 rounded-xl shadow flex-1 min-w-[250px]">
        <Search size={18} className="text-gray-400 mr-2" />
        <input
          type="text"
          placeholder="Rechercher un étudiant..."
          className="outline-none w-full text-sm"
        />
      </div>

      <button className="bg-red-500 text-white p-3 rounded-xl shadow">
        <Plus />
      </button>
    </div>

    {/* BADGE */}
    <div className="flex justify-end mb-4">
      <span className="bg-red-100 text-red-600 px-4 py-1 rounded-full text-xs font-semibold">
        12 ABSENTS
      </span>
    </div>

    {/* LIST */}
    <div className="space-y-6">

      <AbsenceCard
        name="Jean-Pierre Dupont"
        filiere="Bachelor Design"
        group="Graphique - G2"
        time="08:30 - 12:30"
        status="non"
      />

      <AbsenceCard
        name="Jean-Pierre Dupont"
        filiere="Bachelor Design"
        group="Graphique - G2"
        time="08:30 - 12:30"
        status="non"
      />

      <AbsenceCard
        name="Sophie Martin"
        filiere="Web Development"
        group="G1"
        time="13:30 - 17:30"
        status="ok"
      />
    </div>
  </div>
);
}

/* ================= COMPONENTS ================= */

function Select({ label }) {
return (
  <select className="bg-white px-4 py-3 rounded-xl shadow text-sm outline-none">
    <option>{label}</option>
  </select>
);
}

function AbsenceCard({ name, filiere, group, time, status }) {
const isJustified = status === "ok";

return (
  <div className="bg-white rounded-2xl shadow flex overflow-hidden">

    {/* LEFT COLOR BAR */}
    <div
      className={`w-2 ${
        isJustified ? "bg-green-500" : "bg-red-500"
      }`}
    />

    {/* CONTENT */}
    <div className="flex-1 p-6">

      <div className="flex justify-between items-start">
        <div className="flex gap-4">
          <div className="w-12 h-12 rounded-full bg-gray-200" />

          <div>
            <h3 className="font-semibold">{name}</h3>
            <p className="text-sm text-gray-500">
              {filiere}
            </p>
            <p className="text-sm text-gray-500">
              {group}
            </p>
          </div>
        </div>

        <span
          className={`text-xs font-semibold px-3 py-1 rounded-lg ${
            isJustified
              ? "bg-green-100 text-green-600"
              : "bg-red-100 text-red-600"
          }`}
        >
          {isJustified ? "JUSTIFIÉ" : "NON JUSTIFIÉ"}
        </span>
      </div>

      <div className="border-t mt-4 pt-4 flex justify-between items-center text-sm text-gray-500">
        <div className="flex items-center gap-2">
          <Clock size={16} />
          {time}
        </div>

        <div className="flex gap-4">
          <Pencil className="cursor-pointer" />
          <FileText className="cursor-pointer" />
        </div>
      </div>
    </div>
  </div>
  )
}
