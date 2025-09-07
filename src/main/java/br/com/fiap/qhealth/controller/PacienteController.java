package br.com.fiap.qhealth.controller;

import br.com.fiap.qhealth.service.PacienteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(PacienteController.V1_PACIENTE)
@AllArgsConstructor
public class PacienteController {

    public static final String V1_PACIENTE = "/api/v1/pacientes";

    private static final Logger logger = getLogger(PacienteController.class);

    private final PacienteService pacienteService;
    

}