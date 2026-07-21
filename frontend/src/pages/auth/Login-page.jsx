import React, { useState } from "react";
import { motion } from "framer-motion";
import {LockClosedIcon,EnvelopeIcon,ArrowRightIcon,} from "@heroicons/react/24/outline";
import { useNavigate } from "react-router-dom";
import { useAuthStore } from "../../store/authStore";
import { loginUser } from "../../services/authService";

const Login = () => {
  const navigate = useNavigate();
  const login = useAuthStore((state) => state.login);
  const [error, setError] = useState("");

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

const handleSubmit = async (e) => {
  e.preventDefault();

  setLoading(true);
  setError("");

  try {
    const data = await loginUser({ email, password });
    console.log("LOGIN RESPONSE:", data);

    login({
      token: data.token,
      role: data.role,
      email: data.email,
    });

    switch (data.role) {
      case "ADMIN":
        navigate("/admin");
        break;

      case "FORMATEUR":
        navigate("/formateur");
        break;

      case "SECRETAIRE":
        navigate("/secretaire");
        break;

      case "STAGIAIRE":
        navigate("/stagiaire/profil");
        break;

      default:
        navigate("/");
    }
  } catch (err) {
    setError(err.response?.data?.message || err.response?.data || "Email ou mot de passe incorrect");
  } finally {
    setLoading(false);
  }
};

  return (
    <div className="w-screen h-screen flex items-center justify-center bg-gray-900 px-4">

      {/* MAIN CARD */}
      <div className="w-full max-w-6xl h-[650px] bg-white rounded-3xl shadow-2xl flex overflow-hidden">

        {/* LEFT SIDE */}
        <div className="w-full lg:w-1/2 flex flex-col justify-center items-center px-10">

          {/* LOGO + TEXT */}
          <div className="mb-3 flex flex-col items-center">
            <img
              src="logo.png"
              alt="Institut ILEIC"
              className="w-58 h-48 object-contain mb-4"
            />
          </div>

          <h2 className="text-3xl font-bold text-gray-900 mb-2 text-center">
            Connexion
          </h2>
          <p className="text-gray-500 mb-8 text-center">
            Accédez à votre espace pédagogique
          </p>

            {  
            error && (
              <div className="mb-4 w-full max-w-sm rounded-lg bg-red-100 p-3 text-red-700">
                {error}
              </div>
            )}
          

          <form onSubmit={handleSubmit} className="space-y-5 w-full max-w-sm">

            {/* EMAIL */}
            <div className="relative">
              <EnvelopeIcon className="w-5 h-5 text-gray-400 absolute left-4 top-1/2 -translate-y-1/2" />
              <input
                type="email"
                placeholder="email@ileic.ma"
                className="w-full pl-12 pr-4 py-3 rounded-full bg-gray-100
                           focus:outline-none focus:ring-2 focus:ring-purple-400"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>

            {/* PASSWORD */}
            <div className="relative">
              <LockClosedIcon className="w-5 h-5 text-gray-400 absolute left-4 top-1/2 -translate-y-1/2" />
              <input
                type="password"
                placeholder="Mot de passe"
                className="w-full pl-12 pr-4 py-3 rounded-full bg-gray-100
                           focus:outline-none focus:ring-2 focus:ring-purple-400"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>

            {/* BUTTON */}
            <motion.button
              whileHover={{ scale: 1.03 }}
              whileTap={{ scale: 0.97 }}
              type="submit"
              disabled={loading}
              className="w-full py-3 rounded-full text-white font-semibold
                         bg-red-700 hover:bg-red-800
                         transition flex items-center justify-center gap-2"
            >
              {loading ? "Connexion..." : (
                <>
                  Se connecter
                  <ArrowRightIcon className="w-5 h-5" />
                </>
              )}
            </motion.button>

          </form>
        </div>

        {/* RIGHT SIDE IMAGE */}
        <div className="hidden lg:flex lg:w-1/2 h-full items-center justify-end">
          <img
            src="LoginimgIleic1.png"
            alt="visual"
            className="max-h-full object-contain"
          />
        </div>

      </div>
    </div>
  );
};

export default Login;