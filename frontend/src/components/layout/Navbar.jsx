import React from 'react'

export default function Navbar() {

  return (
    <header className='flex justify-between items-center'>
      <div>
        <p>Dashboard</p>
        <p>Gestion Stagiaire</p>
      </div>
      <div>
        <img src="https://images.unsplash.com/photo-1491528323818-fdd1faba62cc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" alt="" class="inline-block size-6 rounded-full ring-2 ring-gray-900 outline -outline-offset-1 outline-white/10" />
        <p>Madame Naima</p>
        <p>Secrétaire Principal</p>
      </div>
    </header>
  )
}
