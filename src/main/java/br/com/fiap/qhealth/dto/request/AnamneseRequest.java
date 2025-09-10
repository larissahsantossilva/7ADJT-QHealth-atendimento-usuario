package br.com.fiap.qhealth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para salvar Anamnese")
public record AnamneseRequest(
        boolean fumante,
        boolean gravida,
        boolean diabetico,
        boolean hipertenso
) {}