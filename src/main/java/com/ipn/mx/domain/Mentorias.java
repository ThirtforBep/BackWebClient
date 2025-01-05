package com.ipn.mx.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JoinColumn(name = "idMentor", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // Configura ON DELETE CASCADE
    private Usuario mentor;

    @ManyToOne
    @JoinColumn(name = "idAprendiz")
    private Usuario aprendiz;

    // Relación OneToMany con Mensaje: Una mentoria puede tener muchos mensajes
    @OneToMany(mappedBy = "mentoria", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    // Relación OneToMany con Resena: Una mentoria puede tener muchas resenas
    @OneToMany(mappedBy = "mentoria", cascade = CascadeType.ALL)
    private List<Resena> resenas;
}
