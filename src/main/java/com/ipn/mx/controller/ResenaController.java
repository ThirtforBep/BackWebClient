package com.ipn.mx.controller;

import com.ipn.mx.domain.Resena;
import com.ipn.mx.services.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ver1/resenas")
public class ResenaController {
    private final ResenaService resenaService;


    public ResenaController(ResenaService resenaService) {
        this.resenaService = resenaService;
    }

    // Obtener todas las resenas
    @GetMapping
    public ResponseEntity<List<Resena>> getAllResenas() {
        return ResponseEntity.ok(resenaService.findAll());
    }

    // Obtener una resena por ID
    @GetMapping("/{id}")
    public ResponseEntity<Resena> getResenaById(@PathVariable Long id) {
        return ResponseEntity.ok(resenaService.findById(id));
    }

    // Obtener resenas por ID de mentoría
    @GetMapping("/mentoria/{mentoriaId}")
    public ResponseEntity<List<Resena>> getResenasByMentoriaId(@PathVariable Long mentoriaId) {
        return ResponseEntity.ok(resenaService.findByMentoriaId(mentoriaId));
    }

    // Crear una nueva resena
    @PostMapping
    public ResponseEntity<Resena> createResena(@RequestBody Resena resena) {
        return ResponseEntity.ok(resenaService.save(resena));
    }

    // Actualizar una resena
    @PutMapping("/{id}")
    public ResponseEntity<Resena> updateResena(@PathVariable Long id, @RequestBody Resena resena) {
        Resena resenaExistente = resenaService.findById(id);
        resena.setIdResena(resenaExistente.getIdResena()); // Asegúrate de mantener el mismo ID
        return ResponseEntity.ok(resenaService.save(resena));
    }

    // Eliminar una resena
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Long id) {
        resenaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
