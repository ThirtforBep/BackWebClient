package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Resena;
import com.ipn.mx.domain.Usuario;
import com.ipn.mx.domain.repository.MentoriasRepository;
import com.ipn.mx.domain.repository.ResenaRepository;
import com.ipn.mx.services.ResenaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaServiceImpl implements ResenaService {

    private final ResenaRepository resenaRepository;
    private final MentoriasRepository mentoriasRepository;

    // Constructor con inyección de dependencias
    public ResenaServiceImpl(ResenaRepository resenaRepository, MentoriasRepository mentoriasRepository) {
        this.resenaRepository = resenaRepository;
        this.mentoriasRepository = mentoriasRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resena> findAll() {
        return (List<Resena>) resenaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Resena findById(Long id) {
        return resenaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resena> findByMentoriaId(Long mentoriaId) {
        return resenaRepository.findByMentoria_IdMentoria(mentoriaId);
    }

    @Override
    @Transactional
    public Resena save(Resena resena) {
        if (resena.getMentoria() == null || resena.getMentoria().getIdMentoria() == null) {
            throw new IllegalArgumentException("La mentoría asociada no puede ser nula.");
        }

        // Validar si la mentoría existe en la base de datos
        if (!mentoriasRepository.existsById(resena.getMentoria().getIdMentoria())) {
            throw new RuntimeException("La mentoría con ID " + resena.getMentoria().getIdMentoria() + " no existe.");
        }

        return resenaRepository.save(resena);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!resenaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar la reseña porque no existe con ID: " + id);
        }

        resenaRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findByRol(String rol) {
        return List.of();
    }
}
