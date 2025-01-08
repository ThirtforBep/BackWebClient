package com.ipn.mx.controller;

import com.ipn.mx.domain.Mentorias;
import com.ipn.mx.domain.Usuario;
import com.ipn.mx.services.MentoriaService;
import com.ipn.mx.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/v1/PlataformaMentoria/mentorias")
@Validated
public class MentoriaController {

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("El controlador está funcionando");
    }


    // Crear una nueva mentoría
    @PostMapping
    public ResponseEntity<?> crearMentoria(@RequestBody Mentorias mentoria) {
        try {
            Mentorias nuevaMentoria = mentoriaService.crearMentoria(mentoria);
            return ResponseEntity.ok(nuevaMentoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> obtenerMentoriasPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(mentoriaService.obtenerMentoriasPorUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentorias> obtenerMentoriaPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(mentoriaService.getMentoriaById(id).orElseThrow(() -> new RuntimeException("Mentoría no encontrada")));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Mentorias>> obtenerTodasLasMentorias() {
        return ResponseEntity.ok(mentoriaService.getAllMentorias());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMentoria(@PathVariable Long id, @RequestBody Mentorias mentoria) {
        try {
            Mentorias mentoriaActualizada = mentoriaService.updateMentoria(id, mentoria);
            return ResponseEntity.ok(mentoriaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Mentoría no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMentoria(@PathVariable Long id) {
        try {
            mentoriaService.deleteMentoria(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Mentoría no encontrada");
        }
    }

    @GetMapping("/estado/{status}")
    public ResponseEntity<List<Mentorias>> obtenerMentoriasPorEstado(@PathVariable String status) {
        return ResponseEntity.ok(mentoriaService.getMentoriasByStatus(status));
    }


}
