package com.ipn.mx;

import com.ipn.mx.domain.*;
import com.ipn.mx.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ipn.mx.domain.repository")
public class ProyectoWebClientApplication implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private ResenaService resenaService;

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private HabilidadesService habilidadesService;

    public static void main(String[] args) {
        SpringApplication.run(ProyectoWebClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear un mentor
        Usuario mentor = Usuario.builder()
                .nombre("Luis Ángel lopez")
                .email("luis.angel.lopez@example.com")
                .rol("Mentor")
                .bio("Desarrollador apasionado con experiencia en tecnologías backend.")
                .nivelExperiencia("Avanzado")
                .areasInteres("Desarrollo de software, Inteligencia Artificial")
                .password("12332")
                .build();
        mentor = usuarioService.save(mentor);
        System.out.println("Mentor creado: " + mentor);

        Usuario mentorduplicado = Usuario.builder()
                .nombre("Luis Ángel bernal")
                .email("luis.angel.bernal@example.com")
                .rol("Mentor")
                .bio("Desarrollador apasionado con experiencia en tecnologías backend popo.")
                .nivelExperiencia("Avanzado")
                .areasInteres("Desarrollo de software, Inteligencia Artificial")
                .password("12332")
                .build();
        mentor = usuarioService.save(mentorduplicado);
        System.out.println("Mentor creado: " + mentor);

        List<Usuario> usuarios = usuarioService.findByRol("Mentor");
        System.out.println("Total mentores: " + usuarios.size()); // Debe ser 1

        // Crear un aprendiz
        Usuario aprendiz = Usuario.builder()
                .nombre("María Perez Lara")
                .email("maria.perez.lara@example.com")
                .rol("Aprendiz")
                .bio("Estudiante entusiasta con interés en programación.")
                .nivelExperiencia("Principiante")
                .areasInteres("Programación, Ciencia de Datos")
                .password("12332")
                .build();
        aprendiz = usuarioService.save(aprendiz);
        System.out.println("Aprendiz creado: " + aprendiz);

        // Crear un aprendiz
        Usuario aprendizduplicado = Usuario.builder()
                .nombre("María Bernal Rodriguez")
                .email("maria.bernal.rodriguez@example.com")
                .rol("Aprendiz")
                .bio("Estudiante entusiasta con interés en programación.")
                .nivelExperiencia("Principiante")
                .areasInteres("Programación, Ciencia de Datos")
                .password("12332")
                .build();
        aprendiz = usuarioService.save(aprendizduplicado);
        System.out.println("Aprendiz creado: " + aprendiz);

        List<Usuario> usuarios2 = usuarioService.findByRol("Aprendiz");
        System.out.println("Total aprendices: " + usuarios2.size()); // Debe ser 1

    }
}
