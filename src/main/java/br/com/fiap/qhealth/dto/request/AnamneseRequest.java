package br.com.fiap.qhealth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO para salvar Anamnese")
public class AnamneseRequest{

        private boolean fumante;

        private boolean gravida;

        private boolean diabetico;

        private boolean hipertenso;
}