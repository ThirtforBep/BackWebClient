package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Perfil_Habilidades;
import com.ipn.mx.domain.repository.HabilidadesRepository;
import com.ipn.mx.services.HabilidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadesServiceImpl implements HabilidadesService {

    @Autowired
    private HabilidadesRepository repository;

    @Override
    public Perfil_Habilidades save(Perfil_Habilidades habilidad) {
        return repository.save(habilidad);
    }

    @Override
    public List<Perfil_Habilidades> findAll() {
        return (List<Perfil_Habilidades>) repository.findAll();
    }

    @Override
    public Perfil_Habilidades findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Habilidad no encontrada con ID: " + id));
    }

    @Override
    public List<Perfil_Habilidades> findByUsuario(Long idUsuario) {
        return repository.findByUsuario(idUsuario);
    }

    @Override
    public List<Perfil_Habilidades> findByHabilidadAndNivel(String habilidad, String nivel) {
        return repository.findByHabilidadAndNivel(habilidad, nivel);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}