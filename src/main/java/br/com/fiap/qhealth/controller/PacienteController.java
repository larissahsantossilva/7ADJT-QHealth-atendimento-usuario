package br.com.fiap.qhealth.controller;

import br.com.fiap.qhealth.dto.request.PacienteAtualizarBodyRequest;
import br.com.fiap.qhealth.dto.request.PacienteBodyRequest;
import br.com.fiap.qhealth.dto.response.PacienteBodyResponse;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Paciente;
import br.com.fiap.qhealth.service.PacienteService;
import br.com.fiap.qhealth.utils.QHealthUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.qhealth.utils.QHealthConstants.*;
import static br.com.fiap.qhealth.utils.QHealthUtils.convertToPaciente;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
@RequestMapping(V1_PACIENTE)
public class PacienteController {

    private static final Logger logger = getLogger(PacienteController.class);

    private final PacienteService pacienteService;

    @Operation(
            description = "Busca todos os pacientes de forma paginada.",
            summary = "Busca todos os pacientes de forma paginada.",
            responses = {
                    @ApiResponse(
                            description = OK,
                            responseCode = HTTP_STATUS_CODE_200,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))
                    )
            }
    )
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

    @Operation(
            description = "Busca paciente por cpf.",
            summary = "Busca paciente por cpf.",
            responses = {
                    @ApiResponse(
                            description = OK,
                            responseCode = HTTP_STATUS_CODE_200,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class))
                    ),
                    @ApiResponse(
                            description = NOT_FOUND,
                            responseCode = HTTP_STATUS_CODE_404,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
                    )
            }
    )
    @GetMapping("/{cpf}")
    public ResponseEntity<PacienteBodyResponse> buscarPacientePorId(@PathVariable("cpf") String cpf) {
        logger.info("GET | {} | Iniciado buscarPacientePorId | id: {}", V1_PACIENTE, cpf);
        var paciente = pacienteService.buscarPacientePorId(cpf);
        if (paciente != null) {
            logger.info("GET | {} | Finalizado buscarPacientePorId | id: {}", V1_PACIENTE, cpf);
            return ok(convertToPaciente(paciente));
        }
        logger.info("GET | {} | Finalizado √ No Content | id: {}", V1_PACIENTE, cpf);
        return status(404).build();
    }

    @Operation(
            description = "Cria paciente.",
            summary = "Cria paciente.",
            responses = {
                    @ApiResponse(
                            description = PACIENTE_CRIADO_COM_SUCESSO,
                            responseCode = HTTP_STATUS_CODE_201,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            description = ERRO_AO_CRIAR_PACIENTE,
                            responseCode = HTTP_STATUS_CODE_422,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<String> criarPaciente(@Valid @RequestBody PacienteBodyRequest pacienteBodyRequest) {
        logger.info("POST | {} | Iniciado criarPaciente | Paciente: {}", V1_PACIENTE, pacienteBodyRequest.getNome());
        Paciente paciente = pacienteService.criarPaciente(convertToPaciente(pacienteBodyRequest));
        logger.info("POST | {} | Finalizado criarPaciente", V1_PACIENTE);
        return status(201).body(paciente.getCpf());
    }

    @Operation(
            description = "Atualiza paciente por cpf.",
            summary = "Atualiza paciente por cpf.",
            responses = {
                    @ApiResponse(
                            description = OK,
                            responseCode = HTTP_STATUS_CODE_200,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = PACIENTE_NAO_ENCONTRADO,
                            responseCode = HTTP_STATUS_CODE_404,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            description = ERRO_AO_ALTERAR_PACIENTE,
                            responseCode = HTTP_STATUS_CODE_422,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    )
            }
    )
    @PutMapping("/{cpf}")
    public ResponseEntity<String> atualizarPaciente(@PathVariable("cpf") String cpf, @Valid @RequestBody PacienteAtualizarBodyRequest pacienteBodyRequest) {
        logger.info("PUT | {} | Iniciado atualizarPaciente | id: {}", V1_PACIENTE, cpf);
        pacienteService.atualizarPacienteExistente(convertToPaciente(pacienteBodyRequest), cpf);
        logger.info("PUT | {} | Finalizado atualizarPaciente", V1_PACIENTE);
        return ok("Paciente atualizado com sucesso");
    }

    @Operation(
            description = "Exclui paciente por id.",
            summary = "Exclui paciente por id.",
            responses = {
                    @ApiResponse(
                            description = NO_CONTENT,
                            responseCode = HTTP_STATUS_CODE_204,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = PACIENTE_NAO_ENCONTRADO,
                            responseCode = HTTP_STATUS_CODE_404,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    )
            }
    )
    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> excluirPacientePorId(@PathVariable("cpf") String cpf) {
        logger.info("DELETE | {} | Iniciado excluirPaciente | id: {}", V1_PACIENTE, cpf);
        try {
            pacienteService.excluirPacientePorId(cpf);
            logger.info("DELETE | {} | Paciente excluído com sucesso | Id: {}", V1_PACIENTE, cpf);
            return noContent().build();
        } catch (UnprocessableEntityException e) {
            logger.error("DELETE | {} | Erro ao excluir paciente | Id: {} | Erro: {}", V1_PACIENTE, cpf, e.getMessage());
            return status(404).body(PACIENTE_NAO_ENCONTRADO);
        }
    }
}