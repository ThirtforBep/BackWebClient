package com.ipn.mx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Resenas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;

    @Column(name = "puntaje", nullable = false)
    private int puntaje; // Puntaje de la calificación, puede ser un valor entre 1 y 5, por ejemplo.

    @Column(name = "comentario", length = 500, nullable = false)
    private String comentario;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // Relación con Mentorias: Una resena está asociada a una mentoria
    @ManyToOne
    @JoinColumn(name = "idMentoria", nullable = false)
    private Mentorias mentoria;

    // Relación con Usuario: El usuario que realizó la resena (puede ser el mentor o el aprendiz)
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
}
