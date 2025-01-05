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

    }
}
