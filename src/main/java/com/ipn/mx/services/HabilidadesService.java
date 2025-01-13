package com.ipn.mx.services;

import com.ipn.mx.domain.Perfil_Habilidades;

import java.util.List;

public interface HabilidadesService {
    Perfil_Habilidades save(Perfil_Habilidades habilidad);
    List<Perfil_Habilidades> findAll();
    Perfil_Habilidades findById(Long id);
    List<Perfil_Habilidades> findByUsuario(Long idUsuario);
    List<Perfil_Habilidades> findByHabilidadAndNivel(String habilidad, String nivel);
    void deleteById(Long id);
}