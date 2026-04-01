export default function Navbar({ user, pageTitle = "Dashboard" }) {

  if (!user) return null;

  const formatRole = (r) => r ? r.charAt(0).toUpperCase() + r.slice(1).toLowerCase() : "—";

  return (
    <header className='flex justify-between items-center bg-white px-10 font-bold'>
      <div className='flex justify-between items-center gap-4 '>
        <p>Dashboard</p>
        <p className='font-bold'>&gt;</p>
        <p className='text-primary'>Gestion Stagiaire</p>
      </div>
      <div className='p-2 flex justify-center items-center gap-4'>
        <div className='flex flex-col justify-center items-center'>
          <p>Mme.{user.name}</p>
          <p className='text-muted'>{user.role}</p>
        </div>
        <img src="/default_pdp.SVG" alt="" class="inline-block size-10 rounded-full ring-2 ring-gray-900 outline -outline-offset-1 outline-white/10" />
      </div>
    </header>
  );
}