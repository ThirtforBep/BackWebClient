package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Reseña;
import com.ipn.mx.domain.repository.ReseñaRepository;
import com.ipn.mx.services.ReseñaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReseñaServiceImpl implements ReseñaService {
    private final ReseñaRepository dao;

    public ReseñaServiceImpl(ReseñaRepository reseñaRepository) {
        this.dao = reseñaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reseña> findAll() {
        return (List<Reseña>) dao.findAll();
    }

    @Override
    public Reseña findById(Long id) {
        Optional<Reseña> reseña = dao.findById(id);
        return reseña.orElseThrow(() -> new RuntimeException("Reseña no encontrada con ID: " + id));
    }

    @Override
    public List<Reseña> findByMentoriaId(Long mentoriaId) {
        return dao.findByMentoria_IdMentoria(mentoriaId); // Ajustado para coincidir con el repositorio
    }

    @Override
    public Reseña save(Reseña reseña) {
        return dao.save(reseña);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar la reseña porque no existe con ID: " + id);
        }
    }
}
