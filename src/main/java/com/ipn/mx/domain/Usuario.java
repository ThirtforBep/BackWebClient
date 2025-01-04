package com.ipn.mx.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Size(min = 3, max = 20, message = "El nombre debe iniciar por el ano que inició el alumno")
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "rol", length = 100, nullable = false)
    private String rol;

    @Column(name = "bio", length = 500, nullable = false)
    private String bio;

    @Column(name = "nivelExperiencia", length = 500, nullable = false)
    private String nivelExperiencia;

    @Column(name = "areasInteres", length = 500, nullable = false)
    private String areasInteres;

    // Relación OneToMany con Mentorias//////////////////////////////////////////
    @OneToMany(mappedBy = "mentor")  // El mapeo se hace por la propiedad "mentor" en Mentorias
    private List<Mentorias> mentor;

    @OneToMany(mappedBy = "aprendiz") // El mapeo se hace por la propiedad "aprendiz" en Mentorias
    private List<Mentorias> aprendiz;

    // Relación ManyToMany con Perfil_Habilidades /////////////////////////////////
    @ManyToMany
    @JoinTable(
            name = "usuario_habilidad",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfilHabilidades")
    )
    private List<Perfil_Habilidades> perfilHabilidades;
}
