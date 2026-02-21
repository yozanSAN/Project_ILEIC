import React from 'react'

export default function Navbar() {
  const secretarySidebarItems = [
    { label: 'Stagaires',icon:, path: '/frontend/src/pages/secretary/Stagiaires.jsx' },
    { label: 'Stagaires', path: '/frontend/src/pages/secretary/Stagiaires.jsx' },
  ]

  return (
    <nav>

      <div>
        <img src="" alt="" />
        <p>Mme.Naima</p>
        <p>Secrétaire Académique</p>
      </div>
      <div>
        {
          secretarySidebarItems.map((item) => <div>
            
          </div>)
        }
      </div>
    </nav>
  )
}
