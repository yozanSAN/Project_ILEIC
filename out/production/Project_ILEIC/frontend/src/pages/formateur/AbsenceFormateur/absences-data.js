// absences-data.js

export const centers = [
    "ILEIC Agadir",
    "ILEIC Dakhla",
    "ILEIC Inzegane",
    "ILEIC Ait Melloul",
  ];
  
  export const filieres = [
    "Développement Informatique",
    "Réseaux Informatiques",
    "Gestion Informatisée",
    "Finance et Comptabilité",
    "Assistant en gestion administrative et comptable",
  ];
  
  export const groupes = [
    "DEV1", "DEV2", "DEV3",
    "RES1", "RES2",
    "GI1", "GI2",
    "FC1", "FC2",
    "AR1", "AR2",
  ];
  
  export const sampleAbsences = [
    // your objects here...
    // or leave empty for now
  ];
  
  // Helper
  export function getToday() {
    return new Date().toISOString().split("T")[0];
  }