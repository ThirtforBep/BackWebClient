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
@RequestMapping("/api/ver1/PlataformaMentoria/mentorias")
@Validated
public class MentoriaController {

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private UsuarioService usuarioService;

    // Crear una nueva mentoría
    @PostMapping
    public ResponseEntity<?> crearMentoria(@RequestBody Mentorias mentoria) {
        try {
            // Logs para depurar
            System.out.println("Mentoría recibida: " + mentoria);

            // Validar mentor y aprendiz
            if (mentoria.getMentor() == null || mentoria.getMentor().getIdUsuario() == null) {
                System.out.println("Mentor es nulo o su ID es nulo.");
                return new ResponseEntity<>("Mentor no especificado", HttpStatus.BAD_REQUEST);
            }

            if (mentoria.getAprendiz() == null || mentoria.getAprendiz().getIdUsuario() == null) {
                System.out.println("Aprendiz es nulo o su ID es nulo.");
                return new ResponseEntity<>("Aprendiz no especificado", HttpStatus.BAD_REQUEST);
            }

            // Verificar existencia del mentor
            Usuario mentor = usuarioService.findById(mentoria.getMentor().getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Mentor no encontrado"));

            // Verificar existencia del aprendiz
            Usuario aprendiz = usuarioService.findById(mentoria.getAprendiz().getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Aprendiz no encontrado"));

            // Asignar mentor y aprendiz a la mentoría
            mentoria.setMentor(mentor);
            mentoria.setAprendiz(aprendiz);

            // Crear la nueva mentoría
            Mentorias nuevaMentoria = mentoriaService.createMentoria(mentoria);
            return new ResponseEntity<>(nuevaMentoria, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener mentoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mentorias> obtenerMentoriaPorId(@PathVariable Long id) {
        Optional<Mentorias> mentoria = mentoriaService.getMentoriaById(id);
        return mentoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    // Obtener todas las mentorías
    @GetMapping
    public ResponseEntity<List<Mentorias>> obtenerTodasLasMentorias() {
        return new ResponseEntity<>(mentoriaService.getAllMentorias(), HttpStatus.OK);
    }

    // Actualizar una mentoría existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMentoria(@PathVariable Long id, @RequestBody Mentorias mentoria) {
        try {
            // Validar que la mentoría exista y actualizarla
            Mentorias mentoriaActualizada = mentoriaService.updateMentoria(id, mentoria);
            return new ResponseEntity<>(mentoriaActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Mentoría no encontrada", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar una mentoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMentoria(@PathVariable Long id) {
        try {
            mentoriaService.deleteMentoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Mentoría no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // Obtener mentorías por estado
    @GetMapping("/estado/{status}")
    public ResponseEntity<List<Mentorias>> obtenerMentoriasPorEstado(@PathVariable String status) {
        List<Mentorias> mentorias = mentoriaService.getMentoriasByStatus(status);
        return new ResponseEntity<>(mentorias, HttpStatus.OK);
    }

    // Obtener mentorías de un usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Mentorias>> obtenerMentoriasPorUsuario(@PathVariable Long idUsuario) {
        List<Mentorias> mentorias = mentoriaService.getMentoriasByUsuario(idUsuario);
        return new ResponseEntity<>(mentorias, HttpStatus.OK);
    }
}
