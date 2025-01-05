package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Mensaje;
import com.ipn.mx.domain.Mentorias;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MentoriasRepository extends CrudRepository<Mentorias, Long> {
    List<Mentorias> findByStatus(String status); // Buscar por status

    @Query("SELECT m FROM Mentorias m WHERE m.mentor.idUsuario = :mentorId OR m.aprendiz.idUsuario = :aprendizId")
    List<Mentorias> findByMentorIdOrAprendizId(@Param("mentorId") Long mentorId, @Param("aprendizId") Long aprendizId);

    @Query("SELECT m FROM Mentorias m WHERE m.mentor.idUsuario = :idMentor OR m.aprendiz.idUsuario = :idAprendiz")
    List<Mentorias> findByUsuario(@Param("idMentor") Long idMentor, @Param("idAprendiz") Long idAprendiz);

    @Modifying
    @Query("DELETE FROM Mentorias m WHERE m.mentor.idUsuario = :idUsuario")
    void deleteByMentorId(@Param("idUsuario") Long idUsuario);

}
