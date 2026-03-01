import React from 'react'
import stagiaires from '../../../../data/secretary/stagiaires'
import { calculateStudentYear } from '../../../../utils/CalculerAnnee'
export default function Table() {
    return (
        <div>
            <table className="table-fixed bg-surface">
                <thead>
                    <tr>
                        <th>Nom & Prénom</th>
                        <th>Filière</th>
                        <th>Année</th>
                    </tr>
                </thead>
                <tbody>
                    {stagiaires.map((stagiair) => (
                        <tr>
                            <td>{stagiair.name}</td>
                            <td>{stagiair.Filiere}</td>
                            <td>{calculateStudentYear(stagiair.AnneeDincription)}</td>
                        </tr>
                    ))
                    }
                </tbody>
            </table>
        </div>
    )
}
