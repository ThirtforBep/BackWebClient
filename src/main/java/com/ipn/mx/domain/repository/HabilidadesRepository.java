package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Mensaje;
import com.ipn.mx.domain.Perfil_Habilidades;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabilidadesRepository extends CrudRepository<Perfil_Habilidades, Long> {
    @Query("SELECT h FROM Perfil_Habilidades h JOIN h.usuarios u WHERE u.idUsuario = :idUsuario")
    List<Perfil_Habilidades> findByUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT h FROM Perfil_Habilidades h WHERE h.habilidad = :habilidad AND h.nivelHabilidad = :nivel")
    List<Perfil_Habilidades> findByHabilidadAndNivel(@Param("habilidad") String habilidad, @Param("nivel") String nivel);
}
