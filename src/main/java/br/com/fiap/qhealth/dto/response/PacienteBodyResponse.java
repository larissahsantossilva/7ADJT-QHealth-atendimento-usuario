package br.com.fiap.qhealth.dto.response;

import br.com.fiap.qhealth.model.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PacienteBodyResponse {

    private UUID id;

    public PacienteBodyResponse(Paciente paciente) {
        this.id = paciente.getId();
    }

}

