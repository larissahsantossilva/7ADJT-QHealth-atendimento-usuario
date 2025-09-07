package br.com.fiap.qhealth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paciente", schema = "usuario")
public class Paciente {

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    private String nome;

    private String email;

    private String login;

    private String senha;

    private String cpf;

    private String genero;

    private String telefone;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "enderecoId", nullable = false)
    private Endereco endereco;

    @Column(name = "dataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "dataUltimaAlteracao")
    private LocalDateTime dataUltimaAlteracao;
}
