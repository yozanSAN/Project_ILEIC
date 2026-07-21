import api from "./api";

export const getAllStagiaires = async (params = {}) => {
  const response = await api.get("/stagiaires", { params });
  return response.data;
};

export const getStagiaireById = async (id) => {
  const response = await api.get(`/stagiaires/${id}`);
  return response.data;
};

export const createStagiaire = async (data) => {
  const response = await api.post("/stagiaires", data);
  return response.data;
};

export const updateStagiaire = async (id, data) => {
  const response = await api.put(`/stagiaires/${id}`, data);
  return response.data;
};

export const deleteStagiaire = async (id) => {
  const response = await api.delete(`/stagiaires/${id}`);
  return response.data;
};