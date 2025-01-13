package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Mensaje;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepository extends CrudRepository<Mensaje, Long> {
    List<Mensaje> findByMentoria_IdMentoria(Long idMentoria);// Buscar mensajes de una mentoría específica

    @Query("SELECT m FROM Mensaje m WHERE m.usuario.idUsuario = :idUsuario")
    List<Mensaje> findByUsuarioRemitente(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Query("DELETE FROM Mensaje m WHERE m.usuario.idUsuario = :idUsuario")
    void deleteByUsuarioId(@Param("idUsuario") Long idUsuario);

    @Query("SELECT m FROM Mensaje m WHERE m.mentoria.idMentoria = :idMentoria ORDER BY m.idMensaje ASC")
    List<Mensaje> findByMentoriaIdOrdered(@Param("idMentoria") Long idMentoria);
}
