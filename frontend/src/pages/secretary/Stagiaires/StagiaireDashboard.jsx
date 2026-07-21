import { useState, useEffect } from 'react'
import MainLayout from '../../../components/layout/MainLayout'
import Table from '../../../components/UI/secretary/stagiaires/Table'
import Filter from '../../../components/UI/secretary/stagiaires/Filter'
import { getAllStagiaires } from '../../../services/stagiaireService'
import { calculateStudentYear } from '../../../utils/CalculerAnnee'

export default function StagiaireDashboard() {

  const [selectedFiliere, setSelectedFiliere] = useState("")
  const [selectedAnnee, setSelectedAnnee] = useState("")
  const [search, setSearch] = useState("")

  const [stagiaires, setStagiaires] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const fetchStagiaires = async () => {
      try {
        const data = await getAllStagiaires();
        setStagiaires(data);
      } catch (error) {
        console.error("Error fetching stagiaires:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchStagiaires();
  }, []);

  const filteredStagiaires = stagiaires
    .filter(s => (s.fullName || "").toLowerCase().includes(search.toLowerCase()))
    .filter(s => selectedFiliere ? s.filiereName === selectedFiliere : true)
    .filter(s => selectedAnnee ? calculateStudentYear(s.enrollmentDate) === selectedAnnee : true)

  return (
    <div >
      <MainLayout>
        <div className='flex flex-col gap-4 w-full mx-5 px-6 '>
          <div>
            <h1 className="text-2xl font-bold text-text">Gestion des Stagiaires</h1>
            <p className="text-muted text-sm ">Bienvenue sur la page de gestion des étudiants.</p>
          </div>
          <Filter search={search} setSearch={setSearch} selectedFiliere={selectedFiliere} setSelectedFiliere={setSelectedFiliere}
            selectedAnnee={selectedAnnee} setSelectedAnnee={setSelectedAnnee}
          />
          
          {loading ? (
             <div className="flex justify-center p-10">Chargement...</div>
          ) : (
            <Table stagiaires={filteredStagiaires} />
          )}
        </div>
      </MainLayout>
    </div>
  )
}