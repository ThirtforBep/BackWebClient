package com.ipn.mx.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false)
    private String rol;

    @Column(name = "bio", nullable = false)
    private String bio;

    @Column(name = "nivelExperiencia", nullable = false)
    private String nivelExperiencia;

    @Column(name = "areasInteres", nullable = false)
    private String areasInteres;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Ignorar esta relación para evitar ciclos
    private List<Mentorias> mentorias;

    @OneToMany(mappedBy = "aprendiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Ignorar esta relación para evitar ciclos
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
