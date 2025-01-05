package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @EntityGraph(attributePaths = {"mentor", "aprendiz"})
    Optional<Usuario> findById(Long id);

    List<Usuario> findByRol(String rol);

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.nivelExperiencia = :nivel")
    List<Usuario> findByNivelExperiencia(@Param("nivel") String nivel);
}
