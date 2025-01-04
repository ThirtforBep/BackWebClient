package com.ipn.mx.controller;

import com.ipn.mx.domain.Mensaje;
import com.ipn.mx.services.MensajeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/ver1/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping
    public ResponseEntity<List<Mensaje>> getAllMensajes() {
        return ResponseEntity.ok(mensajeService.findAll());
    }

    @PostMapping
    public ResponseEntity<Mensaje> saveMensaje(@RequestBody Mensaje mensaje) {
        return ResponseEntity.ok(mensajeService.save(mensaje));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable Long id) {
        return ResponseEntity.ok(mensajeService.findById(id));
    }
}
