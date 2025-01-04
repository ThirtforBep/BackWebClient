package com.ipn.mx.services;

import com.ipn.mx.domain.Resena;
import com.ipn.mx.domain.Usuario;

import java.util.List;

public interface ResenaService {
    List<Resena> findAll();
    Resena findById(Long id);
    List<Resena> findByMentoriaId(Long mentoriaId);
    Resena save(Resena resena);
    void deleteById(Long id);

    interface UsuarioService {
        List<Usuario> findAll(); // Obtener todos los usuarios

        Usuario findById(Long id); // Buscar usuario por ID

        Usuario save(Usuario usuario); // Guardar o actualizar un usuario

        void deleteById(Long id); // Eliminar un usuario por ID

        List<Usuario> findByRol(String rol); // Buscar usuarios por rol (e.g., mentor, aprendiz)
    }
}
