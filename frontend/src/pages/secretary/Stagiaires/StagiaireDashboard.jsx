import React from 'react'
import MainLayout from '../../../components/layout/MainLayout'
import Table from '../../../components/UI/secretary/stagiaires/Table'
import Filtering from '../../../components/UI/secretary/stagiaires/Filtering'
export default function StagiaireDashboard() {
  return (
    <div>
      <MainLayout>
        <h1 className="text-2xl font-bold">Gestion des Stagiaires</h1>
        <p>Bienvenue sur la page de gestion des étudiants.</p>
        <Filtering />
        <Table />
      </MainLayout>
    </div>
  )
}
