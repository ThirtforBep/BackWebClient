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

    @Size(min = 3, max = 20, message = "El nombre debe iniciar por el año que inició el alumno")
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

    @OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
    private List<Mentorias> mentor;

    @OneToMany(mappedBy = "aprendiz", fetch = FetchType.LAZY)
    private List<Mentorias> aprendiz;

    @ManyToMany
    @JoinTable(
            name = "usuario_habilidad",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idPerfilHabilidades")
    )
    private List<Perfil_Habilidades> perfilHabilidades;

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", rol='" + rol + '\'' +
                ", bio='" + bio + '\'' +
                ", nivelExperiencia='" + nivelExperiencia + '\'' +
                ", areasInteres='" + areasInteres + '\'' +
                '}';
    }
}
