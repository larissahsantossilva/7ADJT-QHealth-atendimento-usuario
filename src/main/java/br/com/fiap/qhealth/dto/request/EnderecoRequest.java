package br.com.fiap.qhealth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoRequest {

    @NotBlank
    private String rua;

    @NotNull
    private Integer numero;

    @NotBlank @Pattern(regexp = "\\d{8}")
    private String cep;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;
}
