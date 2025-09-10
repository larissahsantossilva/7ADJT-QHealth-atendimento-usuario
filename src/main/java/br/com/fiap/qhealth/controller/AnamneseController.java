package br.com.fiap.qhealth.controller;

import br.com.fiap.qhealth.dto.request.AnamneseRequest;
import br.com.fiap.qhealth.dto.response.AnamneseResponse;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Anamnese;
import br.com.fiap.qhealth.service.AnamneseService;
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

import java.util.List;
import java.util.UUID;

import static br.com.fiap.qhealth.utils.QHealthConstants.*;
import static br.com.fiap.qhealth.utils.QHealthUtils.convertToAnamnese;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
@RequestMapping(V1_ANAMNESE)
public class AnamneseController {


    private static final Logger logger = getLogger(AnamneseController.class);

    private final AnamneseService anamneseService;

    @Operation(
            description = "Busca todos os Anamneses de forma paginada.",
            summary = "Busca todos os Anamneses de forma paginada.",
            responses = {
                    @ApiResponse(
                            description = OK,
                            responseCode = HTTP_STATUS_CODE_200,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Anamnese.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<AnamneseResponse>> listarAnamneses(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        logger.info("GET | {} | Iniciado listarAnamneses", V1_ANAMNESE);
        Page<Anamnese> anamneses = this.anamneseService.listarAnamneses(page, size);
        List<AnamneseResponse> AnamneseResponses = anamneses.stream()
                .map(QHealthUtils::convertToAnamnese)
                .toList();
        logger.info("GET | {} | Finalizado listarAnamneses", V1_ANAMNESE);
        return ok(AnamneseResponses);
    }

    @Operation(
            description = "Busca Anamnese por id.",
            summary = "Busca Anamnese por id.",
            responses = {
                    @ApiResponse(
                            description = OK,
                            responseCode = HTTP_STATUS_CODE_200,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Anamnese.class))
                    ),
                    @ApiResponse(
                            description = NOT_FOUND,
                            responseCode = HTTP_STATUS_CODE_404,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<AnamneseResponse> buscarAnamnesePorId(@PathVariable("id") UUID id) {
        logger.info("GET | {} | Iniciado buscarAnamnesePorId | id: {}", V1_ANAMNESE, id);
        var anamnese = anamneseService.buscarAnamnesePorId(id);
        if (anamnese != null) {
            logger.info("GET | {} | Finalizado buscarAnamnesePorId | id: {}", V1_ANAMNESE, id);
            return ok(convertToAnamnese(anamnese));
        }
        logger.info("GET | {} | Finalizado √ No Content | id: {}", V1_ANAMNESE, id);
        return status(404).build();
    }

    @Operation(
            description = "Cria Anamnese.",
            summary = "Cria Anamnese.",
            responses = {
                    @ApiResponse(
                            description = ANAMNESE_CRIADO_COM_SUCESSO,
                            responseCode = HTTP_STATUS_CODE_201,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            description = ERRO_AO_CRIAR_ANAMNESE,
                            responseCode = HTTP_STATUS_CODE_422,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
            }
    )
    @PostMapping
    public ResponseEntity<UUID> criarAnamnese(@Valid @RequestBody AnamneseRequest AnamneseBodyRequest) {
        logger.info("POST | {} | Iniciado criarAnamnese | Anamnese: {}", V1_ANAMNESE, AnamneseBodyRequest.toString());
        Anamnese anamnese = anamneseService.criarAnamnese(convertToAnamnese(AnamneseBodyRequest));
        logger.info("POST | {} | Finalizado criarAnamnese", V1_ANAMNESE);
        return status(201).body(anamnese.getId());
    }

    @Operation(
            description = "Atualiza Anamnese por id.",
            summary = "Atualiza Anamnese por id.",
            responses = {
                    @ApiResponse(
                            description = OK,
                            responseCode = HTTP_STATUS_CODE_200,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = ANAMNESE_NAO_ENCONTRADO,
                            responseCode = HTTP_STATUS_CODE_404,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(
                            description = ERRO_AO_ALTERAR_ANAMNESE,
                            responseCode = HTTP_STATUS_CODE_422,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAnamnese(@PathVariable("id") UUID id, @Valid @RequestBody AnamneseRequest AnamneseBodyRequest) {
        logger.info("PUT | {} | Iniciado atualizarAnamnese | id: {}", V1_ANAMNESE, id);
        anamneseService.atualizarAnamneseExistente(convertToAnamnese(AnamneseBodyRequest), id);
        logger.info("PUT | {} | Finalizado atualizarAnamnese", V1_ANAMNESE);
        return ok("Anamnese atualizado com sucesso");
    }

    @Operation(
            description = "Exclui Anamnese por id.",
            summary = "Exclui Anamnese por id.",
            responses = {
                    @ApiResponse(
                            description = NO_CONTENT,
                            responseCode = HTTP_STATUS_CODE_204,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
                    ),
                    @ApiResponse(
                            description = ANAMNESE_NAO_ENCONTRADO,
                            responseCode = HTTP_STATUS_CODE_404,
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirAnamnesePorId(@PathVariable("id") UUID id) {
        logger.info("DELETE | {} | Iniciado excluirAnamnese | id: {}", V1_ANAMNESE, id);
        try {
            anamneseService.excluirAnamnesePorId(id);
            logger.info("DELETE | {} | Anamnese excluído com sucesso | Id: {}", V1_ANAMNESE, id);
            return noContent().build();
        } catch (UnprocessableEntityException e) {
            logger.error("DELETE | {} | Erro ao excluir Anamnese | Id: {} | Erro: {}", V1_ANAMNESE, id, e.getMessage());
            return status(404).body(ANAMNESE_NAO_ENCONTRADO);
        }
    }
}