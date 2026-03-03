import React from 'react'

export default function InformaionsStagiaires() {
    return (
        <div className='bg-surface rounded-xl shadow-card p-4'>
            <div className="grid grid-cols-2 gap-4">
                <div className="..."><div className='flex flex-col'>
                    <label htmlFor="Filiere" className="block mb-2.5 text-sm font-medium ">
                        Recherche
                    </label>
                    <div className="flex items-center border border-borderLight rounded-xl px-3 py-2 focus-within:border-slate-500 focus-within:ring-4 focus-within:ring-slate-100">
                        <input
                            type="text"
                            placeholder="recherche par nom"
                            className="w-full bg-transparent placeholder:text-text text-sm focus:outline-none"
                        />
                    </div>
                </div>
                </div>
                <div className="...">02</div>
                <div className="...">03</div>
                <div className="col-span-2 ...">04</div>
            </div>
        </div>
    )
}
