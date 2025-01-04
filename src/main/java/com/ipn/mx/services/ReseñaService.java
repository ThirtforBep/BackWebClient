package com.ipn.mx.services;

import com.ipn.mx.domain.Reseña;
import com.ipn.mx.domain.Usuario;

import java.util.List;

public interface ReseñaService {
    List<Reseña> findAll();
    Reseña findById(Long id);
    List<Reseña> findByMentoriaId(Long mentoriaId);
    Reseña save(Reseña reseña);
    void deleteById(Long id);

    interface UsuarioService {
        List<Usuario> findAll(); // Obtener todos los usuarios

        Usuario findById(Long id); // Buscar usuario por ID

        Usuario save(Usuario usuario); // Guardar o actualizar un usuario

        void deleteById(Long id); // Eliminar un usuario por ID

        List<Usuario> findByRol(String rol); // Buscar usuarios por rol (e.g., mentor, aprendiz)
    }
}
