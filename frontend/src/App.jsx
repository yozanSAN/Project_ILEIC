import { Routes, Route } from "react-router-dom"
function App() {

  return (
    <>
      <Route path="/secretary">
        <Route path="stagiaires" element={<StagiaireDashboard />} />
        <Route path="stagiaires/ajouter" element={<AjouerStagiaire />} />
        <Route path="controles" element={<Controles />} />
      </Route>
    </>
  )
}

export default App
