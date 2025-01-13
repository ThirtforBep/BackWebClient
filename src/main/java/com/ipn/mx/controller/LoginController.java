package com.ipn.mx.controller;

import com.ipn.mx.domain.LoginRequest;
import com.ipn.mx.domain.Usuario;
import com.ipn.mx.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/ver1/login")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuario = usuarioService.findByEmail(loginRequest.getEmail());

        if (usuario.isPresent()) {
            Usuario user = usuario.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                // Login exitoso
                return ResponseEntity.ok(user);
            } else {
                // Contraseña incorrecta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Contraseña incorrecta");
            }
        } else {
            // Usuario no encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }
}
