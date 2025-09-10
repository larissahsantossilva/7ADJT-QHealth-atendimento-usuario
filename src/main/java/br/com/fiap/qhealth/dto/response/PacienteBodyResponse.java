package br.com.fiap.qhealth.dto.response;

import br.com.fiap.qhealth.model.Endereco;
import br.com.fiap.qhealth.model.Paciente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para retorno de Paciente")
public class PacienteBodyResponse {

    private UUID id;

    private String nome;

    private String email;

    private String login;

    private String cpf;

    private String genero;

    private String telefone;

    private LocalDate dataNascimento;

    private EnderecoBodyResponse endereco;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataUltimaAlteracao;

}

