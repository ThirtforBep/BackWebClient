package com.ipn.mx.services.impl;

import com.ipn.mx.domain.Usuario;
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

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        Hibernate.initialize(usuario.getMentor());
        Hibernate.initialize(usuario.getAprendiz());
        return usuario;
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
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar el usuario porque no existe con ID: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }
}
