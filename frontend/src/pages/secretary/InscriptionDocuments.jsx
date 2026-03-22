import { useState } from "react"
import MainLayout from "../../components/layout/MainLayout"
import StudentSelector from "../../components/UI/secretary/inscriptionDocuments/StudentSelector"
import DocumentsList from "../../components/UI/secretary/inscriptionDocuments/DocumentsList"
import documents from "../../data/secretary/documents"

export default function InscriptionDocuments() {
  const [selectedStagiaire, setSelectedStagiaire] = useState(null)

  const dossier = selectedStagiaire
    ? documents.find(d => d.stagiaireId === selectedStagiaire.id)
    : null

  return (
    <MainLayout>
      <div className="flex flex-col gap-6 w-full mx-5 px-6">
        <div>
          <h1 className="text-2xl font-bold text-text">Gestion des Documents d'Inscription</h1>
          <p className="text-muted text-sm">Vérification et validation formelle des dossiers stagiaires</p>
        </div>

        <StudentSelector
          selectedStagiaire={selectedStagiaire}
          setSelectedStagiaire={setSelectedStagiaire}
        />

        {dossier ? (
          <DocumentsList dossier={dossier} />
        ) : (
          <div className="bg-surface rounded-xl border border-borderLight p-12 text-center shadow-card">
            <p className="text-muted text-sm">Sélectionnez un stagiaire pour afficher son dossier</p>
          </div>
        )}
      </div>
    </MainLayout>
  )
}