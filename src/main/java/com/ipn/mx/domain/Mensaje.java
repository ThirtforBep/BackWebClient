package com.ipn.mx.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMentoria", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore // Ignorar esta relación en la serialización
    private Mentorias mentoria;

    // Relación con Usuario: El mensaje es enviado por un usuario (mentor o aprendiz)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // Configura ON DELETE CASCADE
    @JsonIgnore // Ignorar esta relación en la serialización
    private Usuario usuario;
}
