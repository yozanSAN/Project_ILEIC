// src/pages/formateur/EmploisFormateur/EmploisFormateur.jsx
import { Calendar, Download } from "lucide-react";
import { emplois, horaires, emploiFile } from "./EmploisFormateurdata";

export default function EmploisFormateur() {
  return (
    <div className="min-h-screen bg-gray-50 p-6 md:p-8">

      {/* HEADER */}
      <div className="flex items-center justify-between mb-8">
        <div className="flex items-center gap-3">
          <Calendar className="text-red-500" size={28} />
          <h1 className="text-2xl font-semibold text-gray-800">
            Emploi du temps
          </h1>
        </div>

        {/* DOWNLOAD BUTTON */}
        <a
          href={emploiFile}
          download
          className="flex items-center gap-2 bg-red-500 hover:bg-red-600 text-white px-5 py-2.5 rounded-xl shadow transition"
        >
          <Download size={18} />
          Télécharger votre emploi
        </a>
      </div>

      {/* TIMETABLE CARD */}
      <div className="bg-white rounded-2xl shadow border border-gray-100 p-6">

        <h2 className="text-lg font-semibold text-gray-700 mb-4">
          Votre emploi du temps
        </h2>

        <div className="overflow-x-auto">

          <table className="min-w-full border border-gray-200 rounded-lg overflow-hidden">

            {/* TABLE HEADER */}
            <thead className="bg-gray-100 text-gray-700 text-sm">
              <tr>
                <th className="p-3 border">Jour</th>

                {horaires.map((horaire, index) => (
                  <th key={index} className="p-3 border">
                    {horaire}
                  </th>
                ))}

              </tr>
            </thead>

            {/* TABLE BODY */}
            <tbody className="text-sm text-gray-600">

              {emplois.map((jour, index) => (
                <tr key={index} className="hover:bg-gray-50">

                  <td className="p-3 border font-medium">
                    {jour.jour}
                  </td>

                  {jour.cours.map((cours, i) => (
                    <td key={i} className="p-3 border">
                      {cours}
                    </td>
                  ))}

                </tr>
              ))}

            </tbody>

          </table>

        </div>

      </div>

    </div>
  );
}
