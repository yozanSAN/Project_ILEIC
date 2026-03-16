import React from 'react';
import { UserRoundPlus, Search } from "lucide-react";
import { useNavigate } from 'react-router-dom';

export default function Filtering({ search, setSearch,
    selectedFiliere, setSelectedFiliere,
    selectedAnnee, setSelectedAnnee, }) {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate('/secretaire/stagiaire/ajouterStagiaire');
    };
    return (
        <div className="flex justify-around items-center bg-surface rounded-xl  py-5 px-4 w-full ">
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
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
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
                    value={selectedFiliere}
                    onChange={e => setSelectedFiliere(e.target.value)}
                    className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-sm rounded-xl focus:ring-brand focus:border-brand placeholder:text-body"
                >
                    <option value="">Tout les Filiere</option>
                    <option value="Développement Digital">Développement Digital</option>
                    <option value="Réseaux Informatiques">Réseaux Informatiques</option>
                    <option value="Design Graphique">Design Graphique</option>
                    <option value="Marketing Digital">Marketing Digital</option>
                    <option value="Développement Web">Développement Web</option>
                    <option value="Comptabilité">Comptabilité</option>
                    <option value="Gestion des Entreprises">Gestion des Entreprises</option>
                    <option value="Intelligence Artificielle">Intelligence Artificielle</option>
                </select>
            </div>

            {/* Annee Select */}
            <div>
                <label htmlFor="annee" className="block mb-2 text-sm font-medium text-heading">
                    Année (Tous)
                </label>
                <select
                    value={selectedAnnee}
                    onChange={e => setSelectedAnnee(e.target.value)}
                    id="annee"
                    className="block w-full px-3 py-2  bg-neutral-secondary-medium border border-default-medium text-heading text-sm rounded-xl focus:ring-brand focus:border-brand shadow-xs placeholder:text-body"
                >
                    <option value="">Toutes les années</option>
                    <option value="1ère année">1ère année</option>
                    <option value="2ème année">2ème année</option>
                    <option value="3ème année">3ème année</option>
                    <option value="4ème année">4ème année</option>
                    <option value="5ème année">5ème année</option>
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