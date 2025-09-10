package br.com.fiap.qhealth.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnamneseResponse(
        UUID id,
        boolean fumante,
        boolean gravida,
        boolean diabetico,
        boolean hipertenso,
        LocalDateTime dataCriacao,
        LocalDateTime dataUltimaAlteracao
) {}