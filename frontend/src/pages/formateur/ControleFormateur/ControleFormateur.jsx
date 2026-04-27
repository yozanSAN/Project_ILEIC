import React, { useState } from 'react';
import { ArrowLeft, Search, Plus, Calendar, Clock } from "lucide-react";
import MainLayout from "../../../components/layout/MainLayout";

const sampleControls = [
  {
    id: 1,
    subject: "Mathematics",
    title: "Algebra II",
    type: "EXAM",
    date: "2023-10-12",
    status: "COMPLETED",
    action: "Input Grades",
    actionColor: "bg-red-600 hover:bg-red-700",
  },
  {
    id: 2,
    subject: "Physics",
    title: "Thermodynamics",
    type: "QUIZ",
    date: "2023-10-20",
    status: "UPCOMING",
    action: "Edit Details",
    actionColor: "border border-gray-300 hover:bg-gray-50",
  },
  {
    id: 3,
    subject: "Advanced Algorithms",
    title: "",
    type: "EXAM",
    date: "2023-09-28",
    status: "GRADED",
    action: "View Report",
    actionColor: "bg-rose-100 text-rose-700 hover:bg-rose-200",
  },
  // Add more as needed
];

export default function ControleFormateur() {
  const [search, setSearch] = useState("");
  const [filterStatus, setFilterStatus] = useState("all");

  const filteredControls = sampleControls.filter(control => {
    const matchesSearch = 
      control.subject.toLowerCase().includes(search.toLowerCase()) ||
      control.title.toLowerCase().includes(search.toLowerCase());
    
    const matchesStatus = filterStatus === "all" || control.status.toLowerCase() === filterStatus;
    
    return matchesSearch && matchesStatus;
  });

  return (
    <MainLayout>
      <div className="min-h-screen bg-background px-6 py-6">
        {/* HEADER */}
        <div className="flex items-center justify-between mb-8">
          <div className="flex items-center gap-3">
            <ArrowLeft className="cursor-pointer text-gray-700" size={28} />
            <h1 className="text-2xl font-semibold text-gray-800">Contrôle</h1>
          </div>
          <button className="flex items-center gap-2 bg-red-600 hover:bg-red-700 text-white px-5 py-2.5 rounded-xl font-medium shadow-sm transition-colors">
            <Plus size={20} />
            Add Control
          </button>
        </div>

        {/* STATS ROW */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-8">
          <div className="bg-white rounded-2xl p-6 shadow-sm border border-gray-100">
            <div className="text-sm text-gray-500">Examens d'aujourd'hui</div>
            <div className="text-5xl font-semibold text-gray-900 mt-2">3</div>
          </div>
          <div className="bg-white rounded-2xl p-6 shadow-sm border border-gray-100">
            <div className="text-sm text-gray-500">Notes en attente</div>
            <div className="text-5xl font-semibold text-red-600 mt-2">12</div>
          </div>
        </div>

        {/* SEARCH BAR */}
        <div className="relative mb-8">
          <div className="absolute left-5 top-1/2 -translate-y-1/2 text-gray-400">
            <Search size={20} />
          </div>
          <input
            type="text"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            placeholder="Search subjects..."
            className="w-full bg-white border border-blue-500 focus:border-blue-600 pl-12 pr-5 py-4 rounded-2xl text-sm outline-none shadow-sm"
          />
        </div>

        {/* FILTER TABS (optional) */}
        <div className="flex gap-2 mb-6">
          {["all", "completed", "upcoming", "graded"].map((status) => (
            <button
              key={status}
              onClick={() => setFilterStatus(status)}
              className={`px-5 py-2 text-sm font-medium rounded-full transition-colors ${
                filterStatus === status
                  ? "bg-gray-900 text-white"
                  : "bg-white text-gray-600 hover:bg-gray-100"
              }`}
            >
              {status === "all" ? "All" : status.charAt(0).toUpperCase() + status.slice(1)}
            </button>
          ))}
        </div>

        {/* CONTROLS LIST */}
        <div className="space-y-4">
          {filteredControls.map((control) => {
            const isUpcoming = control.status === "UPCOMING";
            const isCompleted = control.status === "COMPLETED";
            const isGraded = control.status === "GRADED";

            return (
              <div
                key={control.id}
                className="bg-white rounded-2xl p-6 shadow-sm border border-gray-100 hover:shadow transition-all"
              >
                <div className="flex items-start justify-between">
                  <div>
                    <div className="flex items-center gap-3">
                      <span className="text-lg font-semibold text-gray-900">
                        {control.subject}
                      </span>
                      {control.title && (
                        <span className="text-gray-500"> - {control.title}</span>
                      )}
                    </div>

                    <div className="flex items-center gap-4 mt-3 text-sm text-gray-500">
                      <div className="flex items-center gap-1.5">
                        <Calendar size={16} />
                        {new Date(control.date).toLocaleDateString('fr-FR', {
                          day: 'numeric',
                          month: 'short',
                          year: 'numeric'
                        })}
                      </div>
                      <div className="flex items-center gap-1.5">
                        <Clock size={16} />
                        {control.type}
                      </div>
                    </div>
                  </div>

                  {/* STATUS BADGE */}
                  <div className={`px-4 py-1 text-xs font-semibold rounded-full ${
                    isCompleted ? "bg-emerald-100 text-emerald-700" :
                    isUpcoming ? "bg-blue-100 text-blue-700" :
                    isGraded ? "bg-lime-100 text-lime-700" : "bg-gray-100 text-gray-600"
                  }`}>
                    {control.status}
                  </div>
                </div>

                {/* ACTION BUTTON */}
                <div className="mt-6 flex justify-end">
                  <button
                    className={`px-6 py-2.5 text-sm font-medium rounded-xl transition-all ${control.actionColor}`}
                  >
                    {control.action}
                  </button>
                </div>
              </div>
            );
          })}

          {filteredControls.length === 0 && (
            <div className="text-center py-16 text-gray-500">
              Aucun contrôle trouvé pour les filtres sélectionnés.
            </div>
          )}
        </div>
      </div>
    </MainLayout>
  );
}