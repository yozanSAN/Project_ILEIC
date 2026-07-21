// src/services/authService.js
import api from "./api";

export const loginUser = async (credentials) => {
  const response = await api.post("/auth/login", credentials);
  return response.data;
};

export const saveAuth = (data) => {
    localStorage.setItem("token", data.token);
    localStorage.setItem("role", data.role);
    localStorage.setItem("email", data.email);
  };
  
  export const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    localStorage.removeItem("email");
  };
  
  export const getToken = () => {
    return localStorage.getItem("token");
  };
  
  export const getRole = () => {
    return localStorage.getItem("role");
  };
  
  export const isAuthenticated = () => {
    return !!localStorage.getItem("token");
  };