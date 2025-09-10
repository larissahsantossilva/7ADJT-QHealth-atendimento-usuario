package br.com.fiap.qhealth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "DTO para retorno de Anamnese")
public record AnamneseResponse(
        UUID id,
        boolean fumante,
        boolean gravida,
        boolean diabetico,
        boolean hipertenso,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAlteracao
) {}