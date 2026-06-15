import React from 'react'

export default function InformationsStagiaires() {
    return (
        <div className='bg-surface rounded-xl shadow-card px-5 py-4 '>
            <div className='my-5'>
                <h1 className="text-2xl font-bold text-text">Informations personnelles </h1>
                <hr />
            </div>

            <div className="grid grid-cols-2 gap-5">


                <div className='flex flex-col'>
                    <label htmlFor="nom" className="block mb-2.5 text-sm font-medium ">
                        Nom
                    </label>
                    <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                        <input
                            type="text"
                            placeholder="EX:ELAMRANI"
                            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                        />
                    </div>
                </div>

                <div className='flex flex-col'>
                    <label htmlFor="prenom" className="block mb-2.5 text-sm font-medium ">
                        Prenom
                    </label>
                    <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                        <input
                            type="text"
                            placeholder="EX:YASSINE"
                            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                        />
                    </div>
                </div>

                <div className='flex flex-col'>
                    <label htmlFor="datedenaissance" className="block mb-2.5 text-sm font-medium ">
                        Date de Naissance
                    </label>
                    <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                        <input
                            type="date"
                            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                        />
                    </div>
                </div>

                <div className='flex flex-col'>
                    <label htmlFor="sexe" className="block mb-2.5 text-sm font-medium ">
                        Sexe
                    </label>
                    <select
                        id="sexe"
                        className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-sm rounded-xl focus:ring-brand focus:border-brand placeholder:text-body"
                    >
                        <option value="h">homme</option>
                        <option value="f">femme</option>
                    </select>
                </div>

                <div className='flex flex-col'>
                    <label htmlFor="cin" className="block mb-2.5 text-sm font-medium ">
                        CIN
                    </label>
                    <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                        <input
                            type="text"
                            placeholder='EX:AB12345'
                            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                        />
                    </div>
                </div>

                <div className='flex flex-col'>
                    <label htmlFor="email" className="block mb-2.5 text-sm font-medium ">
                        Email
                    </label>
                    <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                        <input
                            type="email"
                            placeholder='EX:yassine@example.com'
                            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                        />
                    </div>
                </div>


            </div>

            {/* INFORMATION ACADEMIQUE */}
            <div className='my-5'>
                <h1 className="text-2xl font-bold text-text">Informations Academique </h1>
                <hr />
            </div >

            <div className="grid grid-cols-2 gap-5">

                <div className='flex flex-col'>
                    <label htmlFor="filier" className="block mb-2.5 text-sm font-medium ">
                        Filiere
                    </label>
                    <select
                        id="filiere"
                        className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-sm rounded-xl focus:ring-brand focus:border-brand placeholder:text-body"
                    >
                        <option value="dev">Development Digital</option>
                        <option value="ig">Gestion</option>
                        <option value="reseaux">Réseaux</option>
                    </select>
                </div>

                <div className='flex flex-col'>
                    <label htmlFor="datedeInscripton" className="block mb-2.5 text-sm font-medium ">
                        Date d'Inscription
                    </label>
                    <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                        <input
                            type="date"
                            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                        />
                    </div>
                </div>

                <div className='flex flex-col'>
                    <label htmlFor="Ndetude" className="block mb-2.5 text-sm font-medium ">
                        Niveau d'Etude
                    </label>
                    <select
                        id="Ndetude"
                        className="block w-full px-3 py-2 bg-neutral-secondary-medium border border-default-medium text-sm rounded-xl focus:ring-brand focus:border-brand placeholder:text-body"
                    >
                        <option value="Baccalaureat">Baccalauréat </option>
                        <option value="niveaubac">Niveau Bac</option>

                    </select>
                </div>

            </div>
        </div>
    )
}
