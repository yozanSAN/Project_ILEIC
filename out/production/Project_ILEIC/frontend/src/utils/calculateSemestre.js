export const calculateSemestre = () => {
  const month = new Date().getMonth();
  if (month >= 8 || month === 0) return 1; // Sep→Jan
  if (month >= 1 && month <= 6) return 2;  // Feb→Jul
  return 1; // August default
}