// src/pages/formateur/CoursFormateur/CoursFormateur.jsx
import React, { useState } from "react";
import { ArrowLeft, Upload, Share2, Plus, FileText } from "lucide-react";
import {
  centers,
  filieres,
  anneesScolaires,
  semestres,
  modelesCours,
  sampleCourses,
} from "./cours-data";

export default function CoursFormateur() {
  const [showAddCourse, setShowAddCourse] = useState(false);
  const [selectedFile, setSelectedFile] = useState(null);

  return (
    <div className="min-h-screen bg-background p-6 md:p-8">
      {/* HEADER */}
      <div className="flex items-center justify-between mb-8">
        <div className="flex items-center gap-3">
          <ArrowLeft className="cursor-pointer text-gray-700" size={24} />
          <h1 className="font-semibold text-xl md:text-2xl text-gray-800">
            Mes Cours
          </h1>
        </div>

        <button
          onClick={() => setShowAddCourse(!showAddCourse)}
          className="bg-red-500 hover:bg-red-600 text-white px-5 py-2.5 rounded-xl flex items-center gap-2 shadow transition"
        >
          <Plus size={18} />
          Nouveau
        </button>
      </div>

      {/* FILTERS */}
      <div className="flex flex-wrap gap-3 mb-8">
        <button className="bg-white px-5 py-2.5 rounded-lg shadow text-sm font-medium border border-gray-200">
          Tous
        </button>

        <button className="bg-gray-100 px-5 py-2.5 rounded-lg text-sm hover:bg-gray-200 transition">
          En cours
        </button>

        <button className="bg-gray-100 px-5 py-2.5 rounded-lg text-sm hover:bg-gray-200 transition">
          Terminés
        </button>
      </div>

      {/* ADD COURSE FORM */}
      {showAddCourse && (
        <div className="bg-white rounded-2xl p-6 md:p-8 shadow-lg mb-10 border border-gray-100">
          <h2 className="text-center font-semibold text-gray-800 text-lg mb-6">
            Ajouter un nouveau cours
          </h2>

          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-6 gap-4">

            {/* Centre */}
            <select className="bg-gray-50 px-4 py-2 rounded-lg text-sm border border-gray-200 focus:border-red-400 h-10">
              <option value="">Centre</option>
              {centers.map((c) => (
                <option key={c}>{c}</option>
              ))}
            </select>

            {/* Filiere */}
            <select className="bg-gray-50 px-4 py-2 rounded-lg text-sm border border-gray-200 focus:border-red-400 h-10">
              <option value="">Filière</option>
              {filieres.map((f) => (
                <option key={f}>{f}</option>
              ))}
            </select>

            {/* Annee */}
            <select className="bg-gray-50 px-4 py-2 rounded-lg text-sm border border-gray-200 focus:border-red-400 h-10">
              <option value="">Année</option>
              {anneesScolaires.map((a) => (
                <option key={a}>{a}</option>
              ))}
            </select>

            {/* Semestre */}
            <select className="bg-gray-50 px-4 py-2 rounded-lg text-sm border border-gray-200 focus:border-red-400 h-10">
              <option value="">Semestre</option>
              {semestres.map((s) => (
                <option key={s}>{s}</option>
              ))}
            </select>

            {/* Modele */}
            <select className="bg-gray-50 px-4 py-2 rounded-lg text-sm border border-gray-200 focus:border-red-400 h-10">
              <option value="">Modèle</option>
              {modelesCours.map((m) => (
                <option key={m}>{m}</option>
              ))}
            </select>

            {/* Upload */}
            <div className="flex flex-col items-center justify-center border-2 border-dashed border-gray-300 rounded-xl p-4 bg-gray-50 hover:bg-gray-100 transition relative">

              <input
                type="file"
                className="absolute inset-0 opacity-0 cursor-pointer"
                onChange={(e) => setSelectedFile(e.target.files[0])}
              />

              <Upload className="text-gray-400 mb-2" size={28} />

              <p className="text-sm font-medium text-gray-700">
                Importer fichier
              </p>

              <p className="text-xs text-gray-500 mt-1">
                PDF, PPT, ZIP…
              </p>

              {selectedFile && (
                <p className="text-xs text-green-600 mt-1">
                  {selectedFile.name}
                </p>
              )}

              <button className="mt-3 bg-green-500 hover:bg-green-600 text-white text-xs px-3 py-1 rounded-lg">
                Activer
              </button>

            </div>
          </div>

          {/* Link */}
          <div className="flex flex-col sm:flex-row items-center gap-4 mt-8">
            <input
              type="text"
              placeholder="Ajouter un lien (Google Drive, YouTube, Moodle…)"
              className="flex-1 border border-gray-300 rounded-xl px-4 py-3 text-sm focus:border-red-400 w-full"
            />

            <button className="bg-red-500 hover:bg-red-600 text-white px-6 py-3 rounded-xl flex items-center gap-2 shadow">
              <Share2 size={16} />
              Partager
            </button>
          </div>
        </div>
      )}

      {/* COURSE LIST */}
      <h2 className="font-semibold text-gray-800 mb-5 text-lg">
        Liste des cours ({sampleCourses.length})
      </h2>

      <div className="space-y-4">
        {sampleCourses.map((course) => (
          <CourseCard key={course.id} {...course} />
        ))}
      </div>
    </div>
  );
}

/* COURSE CARD */

function CourseCard({ title, center, div, semestre, annee, filiere }) {
  const [status, setStatus] = useState("Active");

  return (
    <div className="bg-white rounded-xl p-5 flex flex-col sm:flex-row justify-between items-start sm:items-center shadow hover:shadow-md transition border border-gray-100">

      <div className="flex items-start gap-4">
        <div className="bg-red-50 p-3 rounded-lg">
          <FileText className="text-red-500" size={24} />
        </div>

        <div>
          <h3 className="font-semibold text-gray-800">{title}</h3>

          <p className="text-sm text-gray-600 mt-1">
            {filiere} • {center} • {div} • {semestre} • {annee}
          </p>
        </div>
      </div>

      {/* STATUS SELECT */}
      <select
        value={status}
        onChange={(e) => setStatus(e.target.value)}
        className={`mt-3 sm:mt-0 px-3 py-1 rounded-lg text-sm border
        ${
          status === "Active"
            ? "bg-green-100 text-green-700 border-green-300"
            : "bg-gray-100 text-gray-600 border-gray-300"
        }`}
      >
        <option value="Active">Active</option>
        <option value="Non Active">Non Active</option>
      </select>

    </div>
  );
}
