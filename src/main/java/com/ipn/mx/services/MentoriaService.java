package com.ipn.mx.services;

import com.ipn.mx.domain.Mentorias;

import java.util.List;
import java.util.Optional;

public interface MentoriaService {

    Mentorias crearMentoria(Mentorias mentoria);
    List<Mentorias> obtenerMentoriasPorUsuario(Long idUsuario);
    Optional<Mentorias> getMentoriaById(Long id);
    List<Mentorias> getAllMentorias();
    Mentorias updateMentoria(Long id, Mentorias mentoria);
    void deleteMentoria(Long id);
    List<Mentorias> getMentoriasByStatus(String status);
    void inscribirAprendiz(Long idMentoria, Long idAprendiz);


}
