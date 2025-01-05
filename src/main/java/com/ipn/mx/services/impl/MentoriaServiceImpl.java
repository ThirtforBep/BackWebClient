package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Mentorias;
import com.ipn.mx.domain.repository.MentoriasRepository;
import com.ipn.mx.services.MentoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MentoriaServiceImpl implements MentoriaService {
    @Autowired
    MentoriasRepository dao;
    @Autowired
    private MentoriasRepository mentoriasRepository;

    @Override
    public Mentorias createMentoria(Mentorias mentoria) {
        return mentoriasRepository.save(mentoria);
    }

    @Override
    public Optional<Mentorias> getMentoriaById(Long id) {
        return mentoriasRepository.findById(id);
    }

    @Override
    public List<Mentorias> getAllMentorias() {
        return (List<Mentorias>) dao.findAll();
    }

    @Override
    @Transactional
    public Mentorias updateMentoria(Long id, Mentorias m) {
        Mentorias existing = dao.findById(id).orElseThrow(() -> new RuntimeException("Mentoría no encontrada"));
        existing.setFechaInicio(m.getFechaInicio());
        existing.setFechaFin(m.getFechaFin());
        existing.setStatus(m.getStatus());
        existing.setComentarios(m.getComentarios());
        return dao.save(existing);
    }

    @Override
    @Transactional
    public void deleteMentoria(Long id) {
        dao.deleteById(id);
    }

    @Override
    public List<Mentorias> getMentoriasByStatus(String status) {
        return dao.findByStatus(status);
    }

    @Override
    public List<Mentorias> getMentoriasByUsuario(Long idUsuario) {
        return dao.findByMentorIdOrAprendizId(idUsuario, idUsuario);
    }

}