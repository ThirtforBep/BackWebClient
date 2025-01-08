package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Mentorias;
import com.ipn.mx.domain.Usuario;
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
    private MentoriasRepository mentoriaRepository;

    @Transactional
    @Override
    public Mentorias crearMentoria(Mentorias mentoria) {
        if (mentoria.getMentor() == null || mentoria.getMentor().getIdUsuario() == null) {
            throw new RuntimeException("El mentor es obligatorio");
        }
        if (mentoria.getAprendiz() == null || mentoria.getAprendiz().getIdUsuario() == null) {
            throw new RuntimeException("El aprendiz es obligatorio");
        }
        return mentoriaRepository.save(mentoria);
    }

    @Override
    public List<Mentorias> obtenerMentoriasPorUsuario(Long idUsuario) {
        return mentoriaRepository.findByMentorIdOrAprendizId(idUsuario, idUsuario);
    }

    @Override
    public Optional<Mentorias> getMentoriaById(Long id) {
        return mentoriaRepository.findById(id);
    }

    @Override
    public List<Mentorias> getAllMentorias() {
        return (List<Mentorias>) mentoriaRepository.findAll();
    }

    @Transactional
    @Override
    public Mentorias updateMentoria(Long id, Mentorias mentoria) {
        Mentorias existente = mentoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Mentoría no encontrada"));
        existente.setTitulo(mentoria.getTitulo());
        existente.setDescripcion(mentoria.getDescripcion());
        existente.setHora(mentoria.getHora());
        existente.setFechaInicio(mentoria.getFechaInicio());
        existente.setFechaFin(mentoria.getFechaFin());
        existente.setStatus(mentoria.getStatus());
        return mentoriaRepository.save(existente);
    }

    @Transactional
    @Override
    public void deleteMentoria(Long id) {
        mentoriaRepository.deleteById(id);
    }

    @Override
    public List<Mentorias> getMentoriasByStatus(String status) {
        return mentoriaRepository.findByStatus(status);
    }
}
