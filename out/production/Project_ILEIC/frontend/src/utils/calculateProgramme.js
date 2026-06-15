import { calculateStudentYear } from "./CalculerAnnee"

export const calculateProgramme = (enrollmentDate) => {
  const annee = calculateStudentYear(enrollmentDate)
  if (annee === "1ère année" || annee === "2ème année") return "Diplôme"
  if (annee === "3ème année") return "Licence"
  if (annee === "4ème année" || annee === "5ème année") return "Master"
  return "N/A"
}

export const calculateMontantMensuel = (enrollmentDate) => {
  const programme = calculateProgramme(enrollmentDate)
  if (programme === "Diplôme") return 1300
  if (programme === "Licence") return 1500
  if (programme === "Master") return 2000
  return 0
}