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
@Table(name = "Perfil_Habilidades")
public class Perfil_Habilidades implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfilHabilidades;


    @Column(name = "habilidad", length = 50, nullable = false)
    private String habilidad;

    @Column(name = "nivelHabilidad", length = 50, nullable = false)
    private String nivelHabilidad;

    // Relación ManyToMany con Usuario
    @ManyToMany(mappedBy = "perfilHabilidades")
    private List<Usuario> usuarios;
}
