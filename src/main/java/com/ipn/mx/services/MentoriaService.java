package com.ipn.mx.services;

import com.ipn.mx.domain.Mentorias;

import java.util.List;
import java.util.Optional;

public interface MentoriaService {

    Mentorias createMentoria(Mentorias mentoria);
    Optional<Mentorias> getMentoriaById(Long id);
    List<Mentorias> getAllMentorias();
    Mentorias updateMentoria(Long id, Mentorias m);
    void deleteMentoria(Long id);
    List<Mentorias> getMentoriasByStatus(String status);
    List<Mentorias> getMentoriasByUsuario(Long idUsuario);

}
