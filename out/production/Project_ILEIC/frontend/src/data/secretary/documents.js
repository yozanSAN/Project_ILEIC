const documents = [
  {
    stagiaireId: 1111,
    dossierValide: true,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Validé", fichier: "cin_ayoub.pdf" },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "Validé", fichier: "bac_ayoub.pdf" },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "Validé", fichier: "notes_ayoub.pdf" },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "Validé", fichier: "photos_ayoub.jpg" },
    ]
  },
  {
    stagiaireId: 1112,
    dossierValide: false,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Validé", fichier: "cin_sara.pdf" },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "En attente", fichier: "bac_sara.pdf" },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "Rejeté", fichier: null },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "Non soumis", fichier: null },
    ]
  },
  {
    stagiaireId: 1113,
    dossierValide: false,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Validé", fichier: "cin_youssef.pdf" },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "Non soumis", fichier: null },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "Non soumis", fichier: null },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "En attente", fichier: "photos_youssef.jpg" },
    ]
  },
  {
    stagiaireId: 1114,
    dossierValide: false,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Validé", fichier: "cin_fatima.pdf" },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "Validé", fichier: "bac_fatima.pdf" },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "En attente", fichier: "notes_fatima.pdf" },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "Non soumis", fichier: null },
    ]
  },
  {
    stagiaireId: 1115,
    dossierValide: false,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Rejeté", fichier: null },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "Validé", fichier: "bac_mehdi.pdf" },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "Validé", fichier: "notes_mehdi.pdf" },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "En attente", fichier: "photos_mehdi.jpg" },
    ]
  },
  {
    stagiaireId: 1116,
    dossierValide: false,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Non soumis", fichier: null },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "Non soumis", fichier: null },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "Non soumis", fichier: null },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "Non soumis", fichier: null },
    ]
  },
  {
    stagiaireId: 1117,
    dossierValide: false,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Validé", fichier: "cin_karim.pdf" },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "En attente", fichier: "bac_karim.pdf" },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "En attente", fichier: "notes_karim.pdf" },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "Non soumis", fichier: null },
    ]
  },
  {
    stagiaireId: 1118,
    dossierValide: false,
    pieces: [
      { id: 1, nom: "Carte d'Identité Nationale (CIN)", description: "Recto-Verso obligatoire", statut: "Validé", fichier: "cin_nadia.pdf" },
      { id: 2, nom: "Diplôme de Baccalauréat", description: "Copie certifiée conforme", statut: "Validé", fichier: "bac_nadia.pdf" },
      { id: 3, nom: "Relevé de Notes", description: "Notes du baccalauréat", statut: "Rejeté", fichier: null },
      { id: 4, nom: "Photos d'Identité", description: "Format 3.5cm x 4.5cm (x4)", statut: "En attente", fichier: "photos_nadia.jpg" },
    ]
  },
]

export default documents