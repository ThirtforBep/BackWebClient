package com.ipn.mx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MensajeDTO {
    private Long idMensaje;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private String usuarioRemitente;
    private Long idUsuario;// Nombre del usuario remitente
}
