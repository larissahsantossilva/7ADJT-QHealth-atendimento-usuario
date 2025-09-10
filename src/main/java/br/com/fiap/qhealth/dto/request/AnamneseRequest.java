package br.com.fiap.qhealth.dto.request;

public record AnamneseRequest(
        boolean fumante,
        boolean gravida,
        boolean diabetico,
        boolean hipertenso
) {}