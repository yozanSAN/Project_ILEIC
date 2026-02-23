/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: "#9A0002",
        primaryHover: "#7F0001",
        background: "#EFE6DE",
        surface: "#FFFFFF",
        sidebar: "#9A0002",
        sidebarHover: "#B00003",
        text: "#1F2937",
        muted: "#6B7280",
        borderLight: "#E5E7EB",
      },
      fontFamily: {
        sans: ['Inter', 'ui-sans-serif', 'system-ui', 'sans-serif'],
      },
      boxShadow: {
        card: "0 4px 10px rgba(0,0,0,0.05)",
      },
      borderRadius: {
        xl: "14px",
      },
    },
  },
  plugins: [],
}