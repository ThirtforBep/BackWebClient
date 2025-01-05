package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Usuario;
import com.ipn.mx.domain.repository.MensajeRepository;
import com.ipn.mx.domain.repository.MentoriasRepository;
import com.ipn.mx.domain.repository.UsuarioRepository;
import com.ipn.mx.services.UsuarioService;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final MensajeRepository mensajeRepository;
    private final MentoriasRepository mentoriasRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, MensajeRepository mensajeRepository, MentoriasRepository mentoriasRepository) {
        this.usuarioRepository = usuarioRepository;
        this.mensajeRepository = mensajeRepository;
        this.mentoriasRepository = mentoriasRepository;
    }

    //Parte para el login///////

    // Cierre del login//////

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    Hibernate.initialize(usuario.getMentorias());
                    return usuario;
                });
    }


    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar el usuario porque no existe con ID: " + id);
        }

        // Eliminar mensajes relacionados
        mensajeRepository.deleteByUsuarioId(id);

        // Si existe una relación con mentorías, elimina los registros asociados.
        mentoriasRepository.deleteByMentorId(id); // Implementa este método en el repositorio correspondiente.

        // Finalmente, elimina el usuario
        usuarioRepository.deleteById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }
}
