import React from 'react'
import MainLayout from '../../../components/layout/MainLayout'
import Table from '../../../components/UI/secretary/stagiaires/Table'
import Filtering from '../../../components/UI/secretary/stagiaires/Filtering'

export default function StagiaireDashboard() {
  return (
    <div>
      <MainLayout>
        <div className='flex flex-col gap-4 w-full px-6 py-6'>
          <div>
            <h1 className="text-2xl font-bold text-text">Gestion des Stagiaires</h1>
            <p className="text-muted text-sm mt-1">Bienvenue sur la page de gestion des étudiants.</p>
          </div>
          <Filtering />
          <Table />
        </div>
      </MainLayout>
    </div>
  )
}