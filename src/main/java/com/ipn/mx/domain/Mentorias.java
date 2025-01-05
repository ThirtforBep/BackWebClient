package com.ipn.mx.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "El campo status no puede ser nulo")
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull(message = "Los comentarios no pueden ser nulos")
    @Column(name = "comentarios", length = 500, nullable = false)
    private String comentarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMentor", nullable = false)
    private Usuario mentor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAprendiz", nullable = false)
    private Usuario aprendiz;

    @OneToMany(mappedBy = "mentoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mensaje> mensajes;

    @OneToMany(mappedBy = "mentoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Resena> resenas;
}
