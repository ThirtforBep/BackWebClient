package com.ipn.mx.domain.repository;

import com.ipn.mx.domain.Resena;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResenaRepository extends CrudRepository<Resena, Long> {
    List<Resena> findByMentoria_IdMentoria(Long idMentoria); // Buscar por mentoría

    @Query("SELECT r FROM Resena r WHERE r.puntaje >= :minPuntaje")
    List<Resena> findByMinPuntaje(@Param("minPuntaje") int minPuntaje);
}
