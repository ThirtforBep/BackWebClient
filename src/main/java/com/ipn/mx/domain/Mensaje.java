package com.ipn.mx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Mensaje")
public class Mensaje{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensaje;

    @Column(name = "contenido", nullable = false, length = 1000)
    private String contenido;

    @Column(name = "fechaEnvio", nullable = false)
    private LocalDateTime fechaEnvio;

    // Relación con Mentorias: Muchos mensajes pueden estar asociados a una mentoria
    @ManyToOne
    @JoinColumn(name = "idMentoria", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Mentorias mentoria;

    // Relación con Usuario: El mensaje es enviado por un usuario (mentor o aprendiz)
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // Configura ON DELETE CASCADE
    private Usuario usuario;
}
