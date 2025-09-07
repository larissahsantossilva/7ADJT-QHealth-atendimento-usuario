package br.com.fiap.qhealth.controller;

import br.com.fiap.qhealth.dto.response.PacienteBodyResponse;
import br.com.fiap.qhealth.model.Paciente;
import br.com.fiap.qhealth.service.PacienteService;
import br.com.fiap.qhealth.utils.QHealthUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.qhealth.utils.QHealthUtils.convertToPaciente;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(PacienteController.V1_PACIENTE)
@AllArgsConstructor
public class PacienteController {

    public static final String V1_PACIENTE = "/api/v1/pacientes";

    private static final Logger logger = getLogger(PacienteController.class);

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteBodyResponse>> listarPacientes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET | {} | Iniciado listarPacientes", V1_PACIENTE);
        Page<Paciente> pacientes = this.pacienteService.listarPacientes(page, size);
        List<PacienteBodyResponse> pacienteResponses = pacientes.stream()
                .map(QHealthUtils::convertToPaciente)
                .toList();
        logger.info("GET | {} | Finalizado listarPacientes", V1_PACIENTE);
        return ok(pacienteResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteBodyResponse> buscarPacientePorId(@PathVariable("id") UUID id) {
        logger.info("GET | {} | Iniciado buscarPacientePorId | id: {}", V1_PACIENTE, id);
        var paciente = pacienteService.buscarPacientePorId(id);
        if (paciente != null) {
            logger.info("GET | {} | Finalizado buscarPacientePorId | id: {}", V1_PACIENTE, id);
            return ok(convertToPaciente(paciente));
        }
        logger.info("GET | {} | Finalizado √ No Content | id: {}", V1_PACIENTE, id);
        return status(NOT_FOUND).build();
    }
}