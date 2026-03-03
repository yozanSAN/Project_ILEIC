import MainLayout from "../../../components/layout/MainLayout"
import InformaionsStagiaires from "../../../components/UI/secretary/stagiaires/InformaionsStagiaires"
export default function AjouterStagiaire() {
  return (
    <div>
      <MainLayout>
        <div className="flex flex-col justify-evenly items-start gap-5">
          <div>
            <h1 className="text-2xl font-bold text-text">Ajouter un Stagiaire</h1>
            <p className="text-muted text-sm mt-1">Enregistrez un noubeau stagiaire dons le center</p>
          </div>
          <InformaionsStagiaires />
        </div>
      </MainLayout>
    </div>
  )
}
