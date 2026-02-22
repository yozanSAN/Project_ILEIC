import React, { useState, useRef } from "react";
import { motion } from "framer-motion";
import { Canvas, useFrame } from "@react-three/fiber";
import { Points, PointMaterial, Float } from "@react-three/drei";
import * as THREE from "three";
import {
  LockClosedIcon,
  UserIcon,
  ArrowRightIcon,
} from "@heroicons/react/24/outline";

/* ========================= */
/* 3D Animated Particles */
/* ========================= */

function Particles() {
  const ref = useRef();

  const particlesPosition = React.useMemo(() => {
    const positions = new Float32Array(5000 * 3);
    for (let i = 0; i < 5000; i++) {
      positions[i * 3] = (Math.random() - 0.5) * 20;
      positions[i * 3 + 1] = (Math.random() - 0.5) * 20;
      positions[i * 3 + 2] = (Math.random() - 0.5) * 20;
    }
    return positions;
  }, []);

  useFrame((state, delta) => {
    ref.current.rotation.y += delta * 0.05;
    ref.current.rotation.x += delta * 0.02;
  });

  return (
    <Points ref={ref} positions={particlesPosition} stride={3}>
      <PointMaterial
        transparent
        color="#ff0000"
        size={0.02}
        sizeAttenuation
        depthWrite={false}
      />
    </Points>
  );
}

/* ========================= */
/* Login Component */
/* ========================= */

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Email:", email);
    console.log("Password:", password);
  };

  return (
    <div className="relative w-screen h-screen bg-black overflow-hidden">

      {/* 3D BACKGROUND */}
      <Canvas camera={{ position: [0, 0, 5] }}>
        <Particles />
      </Canvas>

      {/* Login Card */}
      <div className="absolute inset-0 flex items-center justify-center">
        <motion.div
          initial={{ opacity: 0, y: 40, scale: 0.96 }}
          animate={{ opacity: 1, y: 0, scale: 1 }}
          transition={{ duration: 0.8 }}
          className="relative z-10 w-full max-w-md rounded-3xl
           bg-white/5 backdrop-blur-xl
           border border-white/10
           shadow-2xl
           px-6 sm:px-8 py-8 sm:py-10"
        >
          <h2 className="text-2xl font-semibold text-center text-blue-500 mb-2">
            Login
          </h2>

          <p className="text-center text-gray-400 mb-8 text-sm">
            Connexion à la plateforme du Groupe ILEIC
          </p>

          <form onSubmit={handleSubmit} className="space-y-5 max-w-sm mx-auto w-full">
            <div className="relative">
            <UserIcon className="w-5 h-5 text-gray-400 absolute left-4 top-1/2 -translate-y-1/2" />              <input
                type="email"
                name="email"
                autoComplete="off"
                placeholder="p.nom@groupe-ileic.fr"
                className="w-full box-border pl-12 pr-4 py-3 rounded-full
                           bg-black/60 text-white
                           border border-white/10
                           focus:outline-none focus:border-red-500"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>

            <div className="relative">
              <LockClosedIcon className="w-5 h-5 text-gray-400 absolute left-4 top-1/2 -translate-y-1/2" />
              <input
                type="password"
                name="password"
                autoComplete="new-password"
                placeholder="Mot de passe"
                className="w-full box-border pl-12 pr-4 py-3 rounded-full
                           bg-black/60 text-white
                           border border-white/10
                           focus:outline-none focus:border-red-500"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>

            <motion.button
              whileHover={{ scale: 1.03 }}
              whileTap={{ scale: 0.97 }}
              type="submit"
              className="w-full flex items-center justify-center gap-2
                         py-3 rounded-full
                         bg-red-600 hover:bg-red-700
                         text-white font-semibold shadow-xl"
            >
              SE CONNECTER
              <ArrowRightIcon className="w-5 h-5" />
            </motion.button>
          </form>
        </motion.div>
      </div>
    </div>
  );
};

export default Login;