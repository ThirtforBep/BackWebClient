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

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ipn.mx.domain.repository")
public class ProyectoWebClientApplication implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MentoriaService mentoriaService;

    @Autowired
    private ReseñaService reseñaService;

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
                .nombre("Luis Ángel")
                .email("luis.angel@example.com")
                .rol("Mentor")
                .bio("Desarrollador apasionado con experiencia en tecnologías backend.")
                .nivelExperiencia("Avanzado")
                .areasInteres("Desarrollo de software, Inteligencia Artificial")
                .build();
        mentor = usuarioService.save(mentor);
        System.out.println("Mentor creado: " + mentor);

        // Crear un aprendiz
        Usuario aprendiz = Usuario.builder()
                .nombre("María Pérez")
                .email("maria.perez@example.com")
                .rol("Aprendiz")
                .bio("Estudiante entusiasta con interés en programación.")
                .nivelExperiencia("Principiante")
                .areasInteres("Programación, Ciencia de Datos")
                .build();
        aprendiz = usuarioService.save(aprendiz);
        System.out.println("Aprendiz creado: " + aprendiz);

        // Crear una mentoría
        Mentorias mentoria = Mentorias.builder()
                .fechaInicio(LocalDate.now())
                .fechaFin(LocalDate.now().plusMonths(2))
                .status("Activa")
                .comentarios("Mentoría sobre introducción al desarrollo web.")
                .mentor(mentor)
                .aprendiz(aprendiz)
                .build();
        mentoria = mentoriaService.createMentoria(mentoria);
        System.out.println("Mentoría creada: " + mentoria);

        // Crear una reseña por el aprendiz
        Reseña reseñaAprendiz = Reseña.builder()
                .puntaje(5)
                .comentario("La mentoría fue increíble. Aprendí mucho sobre el tema.")
                .fecha(LocalDateTime.now())
                .mentoria(mentoria)
                .usuario(aprendiz) // El aprendiz evalúa al mentor
                .build();
        reseñaAprendiz = reseñaService.save(reseñaAprendiz);
        System.out.println("Reseña creada por el aprendiz: " + reseñaAprendiz);

        // Crear una reseña por el mentor
        Reseña reseñaMentor = Reseña.builder()
                .puntaje(4)
                .comentario("El aprendiz mostró mucho interés y compromiso.")
                .fecha(LocalDateTime.now().plusHours(2))
                .mentoria(mentoria)
                .usuario(mentor) // El mentor evalúa al aprendiz
                .build();
        reseñaMentor = reseñaService.save(reseñaMentor);
        System.out.println("Reseña creada por el mentor: " + reseñaMentor);

        // Crear habilidades para el mentor
        Perfil_Habilidades habilidad1 = Perfil_Habilidades.builder()
                .habilidad("Desarrollo Backend")
                .nivelHabilidad("Avanzado")
                .build();
        habilidad1 = habilidadesService.save(habilidad1);
        System.out.println("Habilidad creada: " + habilidad1);

        Perfil_Habilidades habilidad2 = Perfil_Habilidades.builder()
                .habilidad("Inteligencia Artificial")
                .nivelHabilidad("Intermedio")
                .build();
        habilidad2 = habilidadesService.save(habilidad2);
        System.out.println("Habilidad creada: " + habilidad2);

        // Crear habilidades para el aprendiz
        Perfil_Habilidades habilidad3 = Perfil_Habilidades.builder()
                .habilidad("Desarrollo Frontend")
                .nivelHabilidad("Principiante")
                .build();
        habilidad3 = habilidadesService.save(habilidad3);
        System.out.println("Habilidad creada: " + habilidad3);

        // Crear mensajes para la mentoría
        Mensaje mensaje1 = Mensaje.builder()
                .contenido("Hola, ¿cuándo comenzamos con la mentoría?")
                .fechaEnvio(LocalDateTime.now())
                .mentoria(mentoria)
                .usuario(aprendiz)
                .build();
        mensaje1 = mensajeService.save(mensaje1);
        System.out.println("Mensaje creado: " + mensaje1);

        Mensaje mensaje2 = Mensaje.builder()
                .contenido("Podemos iniciar mañana. Prepararé los materiales.")
                .fechaEnvio(LocalDateTime.now().plusMinutes(10))
                .mentoria(mentoria)
                .usuario(mentor)
                .build();
        mensaje2 = mensajeService.save(mensaje2);
        System.out.println("Mensaje creado: " + mensaje2);

        Mensaje mensaje3 = Mensaje.builder()
                .contenido("Perfecto, estaré lista.")
                .fechaEnvio(LocalDateTime.now().plusMinutes(20))
                .mentoria(mentoria)
                .usuario(aprendiz)
                .build();
        mensaje3 = mensajeService.save(mensaje3);
        System.out.println("Mensaje creado: " + mensaje3);
    }
}
