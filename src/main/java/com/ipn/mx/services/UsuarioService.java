package com.ipn.mx.services;

import com.ipn.mx.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll(); // Obtener todos los usuarios

    Optional<Usuario> findById(Long id);; // Buscar usuario por ID

    Usuario save(Usuario usuario); // Guardar o actualizar un usuario

    void deleteById(Long id); // Eliminar un usuario por ID

    List<Usuario> findByRol(String rol); // Buscar usuarios por rol (e.g., mentor, aprendiz)

    Optional<Usuario> findByEmail(String email); // Buscar usuario por correo electrónico
}