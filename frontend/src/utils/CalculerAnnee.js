export const calculateStudentYear = (enrollmentDate) => {
  const start = new Date(enrollmentDate);
  const now = new Date();
  
  // Calculate difference in years
  let diff = now.getFullYear() - start.getFullYear();
  
  // If we haven't reached October (Month 9 in JS because it starts at 0)
  // then the "academic year" hasn't increased yet.
  if (now.getMonth() < 9) {
    diff = diff; 
  } else {
    // If it's October or later, we are in the next academic year
    diff = diff + 1;
  }

  // Return a readable string
  if (diff <= 1) return "1ère année";
  if (diff === 2) return "2ème année";
  if (diff >= 3) return "3ème année "; 
  if (diff >= 4) return "4ème année "; 
  if (diff >= 5) return "5ème année "; 
  return "N/A";
};