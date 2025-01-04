package com.ipn.mx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Mentorias")
public class Mentorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMentoria;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "comentarios", length = 500, nullable = false)
    private String comentarios;

    //Relacion con los aprendices y los mentores
    @ManyToOne
    @JoinColumn(name = "idMentor")
    private Usuario mentor;

    @ManyToOne
    @JoinColumn(name = "idAprendiz")
    private Usuario aprendiz;

    // Relación OneToMany con Mensaje: Una mentoria puede tener muchos mensajes
    @OneToMany(mappedBy = "mentoria", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    // Relación OneToMany con Reseña: Una mentoria puede tener muchas reseñas
    @OneToMany(mappedBy = "mentoria", cascade = CascadeType.ALL)
    private List<Reseña> reseñas;
}
