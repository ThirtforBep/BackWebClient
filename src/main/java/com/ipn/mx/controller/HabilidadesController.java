package com.ipn.mx.controller;

import com.ipn.mx.domain.Perfil_Habilidades;
import com.ipn.mx.services.HabilidadesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/ver1/habilidades")
public class HabilidadesController {

    private final HabilidadesService habilidadesService;

    public HabilidadesController(HabilidadesService habilidadesService) {
        this.habilidadesService = habilidadesService;
    }

    @GetMapping
    public ResponseEntity<List<Perfil_Habilidades>> getAllHabilidades() {
        return ResponseEntity.ok(habilidadesService.findAll());
    }

    @PostMapping
    public ResponseEntity<Perfil_Habilidades> saveHabilidad(@RequestBody Perfil_Habilidades habilidad) {
        return ResponseEntity.ok(habilidadesService.save(habilidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil_Habilidades> getHabilidadById(@PathVariable Long id) {
        return ResponseEntity.ok(habilidadesService.findById(id));
    }
}
