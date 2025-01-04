package com.ipn.mx.services;

import com.ipn.mx.domain.Mensaje;

import java.util.List;

public interface MensajeService {
    Mensaje save(Mensaje mensaje);
    List<Mensaje> findAll();
    Mensaje findById(Long id);
    List<Mensaje> findByMentoriaId(Long idMentoria);
    List<Mensaje> findByUsuarioRemitente(Long idUsuario);
    void deleteById(Long id);
}