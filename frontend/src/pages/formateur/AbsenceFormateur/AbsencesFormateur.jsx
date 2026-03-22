import React, { useState,useRef } from 'react';
import { sampleAbsences, centers, filieres, getToday } from './absences-data';
import {
  ArrowLeft,
  Search,
  Plus,
  Clock,
  Pencil,
  FileText,
} from "lucide-react";


export default function AbsencesFormateur() {
  const [selectedCenter, setSelectedCenter] = useState("");
  const [selectedFiliere, setSelectedFiliere] = useState("");
  const [search, setSearch] = useState("");
  const [dateFilter, setDateFilter] = useState(getToday());
  const dateInputRef = useRef(null);
  const [showDatePicker, setShowDatePicker] = useState(false);

  // Filter logic
  const filteredAbsences = sampleAbsences.filter(abs => {
    const matchCenter  = !selectedCenter  || abs.center === selectedCenter;
    const matchFiliere = !selectedFiliere || abs.filiere === selectedFiliere;
    const matchSearch  = !search || abs.name.toLowerCase().includes(search.toLowerCase());
    const matchDate    = abs.date === dateFilter;
    return matchCenter && matchFiliere && matchSearch && matchDate;
  });

  const absentCount = filteredAbsences.filter(a => a.status === "non").length;

  return (
    <div className="min-h-screen bg-background px-6 py-6">
      {/* HEADER - fixed height */}
      <div className="shrink-0 bg-bachground border-b px-6 py-4 flex items-center gap-3">
        <ArrowLeft className="cursor-pointer text-gray-700" size={24} />
        <h1 className="font-semibold text-gray-700">Absence</h1>
      </div>

      {/* FILTER BAR */}
      <div className="flex flex-wrap items-center gap-4 mb-8">
        <select
          value={selectedCenter}
          onChange={e => setSelectedCenter(e.target.value)}
          className="bg-white px-4 py-3 rounded-xl shadow text-sm outline-none"
        >
          <option value="">Tous les centres</option>
          {centers.map(c => <option key={c} value={c}>{c}</option>)}
        </select>

        <select
          value={selectedFiliere}
          onChange={e => setSelectedFiliere(e.target.value)}
          className="bg-white px-4 py-3 rounded-xl shadow text-sm outline-none"
        >
          <option value="">Toutes les filières</option>
          {filieres.map(f => <option key={f} value={f}>{f}</option>)}
        </select>

        {/* DATE FILTER */}
        <div className="relative">
          
          {/* DATE BUTTON */}
          <button
            onClick={() => setShowDatePicker(!showDatePicker)}
            className="flex items-center gap-2 bg-white px-4 py-3 rounded-xl shadow text-sm"
          >
            📅 {new Date(dateFilter).toLocaleDateString('fr-FR', {
              weekday: 'long',
              day: 'numeric',
              month: 'long'
            })}
          </button>

          {/* DATE PICKER UNDER BUTTON */}
          {showDatePicker && (
            <input
              type="date"
              value={dateFilter}
              onChange={(e) => {
                setDateFilter(e.target.value);
                setShowDatePicker(false);
              }}
              className="absolute left-0 mt-2 bg-white shadow rounded-lg p-2 text-sm"
            />
          )}

        </div>

        <div className="flex items-center bg-white px-4 py-3 rounded-xl shadow flex-1 min-w-[250px]">
          <Search size={18} className="text-gray-400 mr-2" />
          <input
            type="text"
            value={search}
            onChange={e => setSearch(e.target.value)}
            placeholder="Rechercher un étudiant..."
            className="outline-none w-full text-sm"
          />
        </div>

        <button className="bg-red-500 text-white p-3 rounded-xl shadow">
          <Plus />
        </button>
      </div>

      <div className="flex justify-end mb-4">
        <span className="bg-red-100 text-red-600 px-4 py-1 rounded-full text-xs font-semibold">
          {absentCount} ABSENT{absentCount !== 1 ? 'S' : ''}
        </span>
      </div>

      <div className="space-y-6">
        {filteredAbsences.map(abs => (
          <AbsenceCard
            key={abs.id}
            name={abs.name}
            filiere={abs.filiere}
            group={abs.groupe}
            time={abs.time}
            status={abs.status}
          />
        ))}

        {filteredAbsences.length === 0 && (
          <p className="text-center text-gray-500 py-10">
            Aucune absence trouvée pour les filtres sélectionnés.
          </p>
        )}
      </div>
    </div>
  );
}