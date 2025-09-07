package br.com.fiap.qhealth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco", schema = "usuario")
public class Endereco {

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    private String rua;

    private Integer numero;

    private String cep;

    private String complemento;

    private String bairro;

    private String cidade;

    @Column(name = "dataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "dataUltimaAlteracao")
    private LocalDateTime dataUltimaAlteracao;
}
