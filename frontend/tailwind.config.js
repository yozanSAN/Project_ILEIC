/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  // This is critical when using MUI alongside Tailwind v3
  corePlugins: {
    preflight: false,
  },
  plugins: [],
}