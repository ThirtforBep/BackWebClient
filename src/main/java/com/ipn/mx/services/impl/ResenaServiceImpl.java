package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Resena;
import com.ipn.mx.domain.repository.ResenaRepository;
import com.ipn.mx.services.ResenaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaServiceImpl implements ResenaService {
    private final ResenaRepository dao;

    public ResenaServiceImpl(ResenaRepository resenaRepository) {
        this.dao = resenaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resena> findAll() {
        return (List<Resena>) dao.findAll();
    }

    @Override
    public Resena findById(Long id) {
        Optional<Resena> resena = dao.findById(id);
        return resena.orElseThrow(() -> new RuntimeException("Resena no encontrada con ID: " + id));
    }

    @Override
    public List<Resena> findByMentoriaId(Long mentoriaId) {
        return dao.findByMentoria_IdMentoria(mentoriaId); // Ajustado para coincidir con el repositorio
    }

    @Override
    public Resena save(Resena resena) {
        return dao.save(resena);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar la resena porque no existe con ID: " + id);
        }
    }
}
