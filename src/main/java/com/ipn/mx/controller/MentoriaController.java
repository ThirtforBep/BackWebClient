package com.ipn.mx.controller;

import com.ipn.mx.domain.Mentorias;
import com.ipn.mx.services.MentoriaService;
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

    // Crear una nueva mentoría
    @PostMapping
    public ResponseEntity<Mentorias> crearMentoria(@RequestBody Mentorias mentoria) {
        try {
            Mentorias nuevaMentoria = mentoriaService.createMentoria(mentoria);
            return new ResponseEntity<>(nuevaMentoria, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Obtener mentoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Mentorias> obtenerMentoriaPorId(@PathVariable Long id) {
        Optional<Mentorias> mentoria = mentoriaService.getMentoriaById(id);
        if (mentoria.isPresent()) {
            return new ResponseEntity<>(mentoria.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todas las mentorías
    @GetMapping
    public ResponseEntity<List<Mentorias>> obtenerTodasLasMentorias() {
        List<Mentorias> mentorias = mentoriaService.getAllMentorias();
        return new ResponseEntity<>(mentorias, HttpStatus.OK);
    }

    // Actualizar una mentoría existente
    @PutMapping("/{id}")
    public ResponseEntity<Mentorias> actualizarMentoria(@PathVariable Long id, @RequestBody Mentorias mentoria) {
        try {
            Mentorias mentoriaActualizada = mentoriaService.updateMentoria(id, mentoria);
            return new ResponseEntity<>(mentoriaActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una mentoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMentoria(@PathVariable Long id) {
        try {
            mentoriaService.deleteMentoria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
