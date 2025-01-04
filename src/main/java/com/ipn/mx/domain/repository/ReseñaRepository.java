package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Reseña;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReseñaRepository extends CrudRepository<Reseña, Long> {
    List<Reseña> findByMentoria_IdMentoria(Long idMentoria); // Buscar por mentoría

    @Query("SELECT r FROM Reseña r WHERE r.puntaje >= :minPuntaje")
    List<Reseña> findByMinPuntaje(@Param("minPuntaje") int minPuntaje);
}
