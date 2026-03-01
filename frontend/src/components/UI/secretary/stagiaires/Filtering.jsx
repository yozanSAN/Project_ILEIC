import React from 'react';
import { UserRoundPlus, Search } from "lucide-react";

export default function Filtering() {
    return (
        <div className="space-y-4 flex justify-evenly items-center bg-surface rounded-xl">
            {/* Search Input with Icon */}
            <div className="flex items-center border border-borderLight rounded-xl shadow-lg shadow-gray-100 px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                <Search className="w-5 h-5 text-text mr-2" />
                <input
                    type="text"
                    placeholder="Type Here..."
                    className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                />
            </div>

            {/* Filiere Select */}
            <div>
                <label htmlFor="Filiere" className="block mb-2.5 text-sm font-medium text-heading">
                    Filière (Tous)
                </label>
                <select
                    id="Filiere"
                    className="block w-full px-3 py-2.5 bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-xl focus:ring-brand focus:border-brand shadow-xs placeholder:text-body"
                >
                    <option value="dev">Development Digital</option>
                    <option value="ig">Gestion</option>
                    <option value="reseaux">Réseaux</option>
                </select>
            </div>

            {/* Annee Select */}
            <div>
                <label htmlFor="annee" className="block mb-2.5 text-sm font-medium text-heading">
                    Année (Tous)
                </label>
                <select
                    id="annee"
                    className="block w-full px-3 py-2.5 bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-xl focus:ring-brand focus:border-brand shadow-xs placeholder:text-body"
                >
                    <option value="1ere">1ère année</option>
                    <option value="2eme">2ème année</option>
                    <option value="3eme">3ème année</option>
                </select>
            </div>

            {/* Button with Icon */}
            <button className="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-xl hover:bg-blue-700 transition">
                <UserRoundPlus className="w-5 h-5" />
                Ajouter Stagiaire
            </button>
        </div>
    );
}