package com.ipn.mx.controller;

import com.ipn.mx.domain.Reseña;
import com.ipn.mx.services.ReseñaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/ver1/reseñas")
public class ReseñaController {
    private final ReseñaService reseñaService;


    public ReseñaController(ReseñaService reseñaService) {
        this.reseñaService = reseñaService;
    }

    // Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<Reseña>> getAllReseñas() {
        return ResponseEntity.ok(reseñaService.findAll());
    }

    // Obtener una reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reseña> getReseñaById(@PathVariable Long id) {
        return ResponseEntity.ok(reseñaService.findById(id));
    }

    // Obtener reseñas por ID de mentoría
    @GetMapping("/mentoria/{mentoriaId}")
    public ResponseEntity<List<Reseña>> getReseñasByMentoriaId(@PathVariable Long mentoriaId) {
        return ResponseEntity.ok(reseñaService.findByMentoriaId(mentoriaId));
    }

    // Crear una nueva reseña
    @PostMapping
    public ResponseEntity<Reseña> createReseña(@RequestBody Reseña reseña) {
        return ResponseEntity.ok(reseñaService.save(reseña));
    }

    // Actualizar una reseña
    @PutMapping("/{id}")
    public ResponseEntity<Reseña> updateReseña(@PathVariable Long id, @RequestBody Reseña reseña) {
        Reseña reseñaExistente = reseñaService.findById(id);
        reseña.setIdReseña(reseñaExistente.getIdReseña()); // Asegúrate de mantener el mismo ID
        return ResponseEntity.ok(reseñaService.save(reseña));
    }

    // Eliminar una reseña
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReseña(@PathVariable Long id) {
        reseñaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
