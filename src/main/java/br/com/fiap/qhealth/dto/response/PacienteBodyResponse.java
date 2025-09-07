package br.com.fiap.qhealth.dto.response;

import br.com.fiap.qhealth.model.Endereco;
import br.com.fiap.qhealth.model.Paciente;
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

