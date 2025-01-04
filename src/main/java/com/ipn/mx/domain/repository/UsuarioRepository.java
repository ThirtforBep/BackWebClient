package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<com.ipn.mx.domain.Usuario, Long> {
    List<Usuario> findByRol(String rol); // Buscar por rol (mentor o aprendiz)

    @Query("SELECT u FROM Usuario u WHERE u.nivelExperiencia = :nivel")
    List<Usuario> findByNivelExperiencia(@Param("nivel") String nivel);
}
