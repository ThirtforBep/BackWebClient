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
@Table(name = "Perfil_Habilidades")
public class Perfil_Habilidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfilHabilidades;

    @Column(name = "habilidad", length = 50, nullable = false)
    private String habilidad;

    @Column(name = "nivelHabilidad", length = 50, nullable = true)
    private String nivelHabilidad;

    @ManyToMany(mappedBy = "perfilHabilidades")
    @JsonIgnore // Ignorar esta relación para evitar ciclos de serialización
    private List<Usuario> usuarios;
}
