import { useNavigate } from "react-router-dom";
import MainLayout from "../../components/layout/MainLayout";

export default function AdminDashboard() {
  const navigate = useNavigate();

  return (
    <MainLayout>
      <div className="flex flex-col items-center justify-center h-full gap-10">

        <h1 className="text-3xl font-bold">Choisir un espace</h1>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 w-full max-w-4xl">

          <button
            onClick={() => navigate("/formateur/personnel-detail")}
            className="bg-white hover:bg-white-600 text-black py-6 rounded-xl shadow-lg text-lg font-semibold"
          >
            Formateur
          </button>

          <button
            onClick={() => navigate("/secretaire/stagiaire")}
            className="bg-white hover:bg-white-600 text-black py-6 rounded-xl shadow-lg text-lg font-semibold"
          >
            Secrétaire
          </button>

          <button
            onClick={() => navigate("/stagiaire/profil")}
            className="bg-white hover:bg-white-600 text-black py-6 rounded-xl shadow-lg text-lg font-semibold"
          >
            Stagiaire
          </button>

        </div>
      </div>
    </MainLayout>
  );
}