// src/store/authStore.js

import { create } from "zustand";

export const useAuthStore = create((set) => ({
  token: localStorage.getItem("token"),
  role: localStorage.getItem("role"),
  email: localStorage.getItem("email"),

  login: (data) => {
    localStorage.setItem("token", data.token);
    localStorage.setItem("role", data.role);
    localStorage.setItem("email", data.email);

    set({
      token: data.token,
      role: data.role,
      email: data.email,
    });
  },

  logout: () => {
    localStorage.clear();

    set({
      token: null,
      role: null,
      email: null,
    });
  },
}));