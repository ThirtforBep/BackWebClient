package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Mensaje;
import com.ipn.mx.domain.repository.MensajeRepository;
import com.ipn.mx.dto.MensajeDTO;
import com.ipn.mx.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository repository;

    @Override
    public Mensaje save(Mensaje mensaje) {
        return repository.save(mensaje);
    }

    @Override
    public List<Mensaje> findAll() {
        return (List<Mensaje>) repository.findAll();
    }

    @Override
    public Mensaje findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Mensaje no encontrado con ID: " + id));
    }

    @Override
    public List<Mensaje> findByMentoriaId(Long idMentoria) {
        return repository.findByMentoria_IdMentoria(idMentoria);
    }

    @Override
    public List<Mensaje> findByUsuarioRemitente(Long idUsuario) {
        return repository.findByUsuarioRemitente(idUsuario);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<MensajeDTO> findConversacionByMentoriaId(Long idMentoria) {
        return repository.findByMentoria_IdMentoria(idMentoria).stream()
                .map(mensaje -> new MensajeDTO(
                        mensaje.getIdMensaje(),
                        mensaje.getContenido(),
                        mensaje.getFechaEnvio(),
                        mensaje.getUsuario().getIdUsuario() // Obteniendo el nombre del usuario remitente
                ))
                .collect(Collectors.toList());
    }


}