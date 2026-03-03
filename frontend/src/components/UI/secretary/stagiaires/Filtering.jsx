import React from 'react';
import { UserRoundPlus, Search } from "lucide-react";
import { useNavigate } from 'react-router-dom';

export default function Filtering() {
    const navigate = useNavigate();
    const handleClick = () => {
    navigate('/AjouterStagiaire');
  };
    return (
        <div className="flex justify-evenly items-center bg-surface rounded-xl gap-5 py-5 px-4 w-full ">
            {/* Search Input with Icon */}
            <div className='flex flex-col'>
                <label htmlFor="Filiere" className="block mb-2.5 text-sm font-medium ">
                    Recherche
                </label>
                <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">

                    <Search className="w-5 h-5 text-text mr-2" />
                    <input
                        type="text"
                        placeholder="recherche par nom"
                        className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                    />
                </div>
            </div>


            {/* Filiere Select */}
            <div>
                <label htmlFor="Filiere" className="block mb-2 text-sm font-medium text-heading">
                    Filière (Tous)
                </label>
                <select
                    id="Filiere"
                    className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-sm rounded-xl focus:ring-brand focus:border-brand placeholder:text-body"
                >
                    <option value="dev">Development Digital</option>
                    <option value="ig">Gestion</option>
                    <option value="reseaux">Réseaux</option>
                </select>
            </div>

            {/* Annee Select */}
            <div>
                <label htmlFor="annee" className="block mb-2 text-sm font-medium text-heading">
                    Année (Tous)
                </label>
                <select
                    id="annee"
                    className="block w-full px-3 py-2  bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-xl focus:ring-brand focus:border-brand shadow-xs placeholder:text-body"
                >
                    <option value="1ere">1ère année</option>
                    <option value="2eme">2ème année</option>
                    <option value="3eme">3ème année</option>
                </select>
            </div>

            {/* Button with Icon */}
            <button onClick={handleClick} className="flex items-center gap-2 px-4 py-2 mt-5 bg-primary text-white rounded-xl hover:bg-primaryHover transition">

                <UserRoundPlus className="w-5 h-5" />
                Ajouter Stagiaire
            </button>
        </div>
    );
}