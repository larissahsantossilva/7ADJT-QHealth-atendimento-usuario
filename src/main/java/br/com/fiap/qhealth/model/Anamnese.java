package br.com.fiap.qhealth.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "anamnese", schema = "usuario")
public class Anamnese {

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    private boolean fumante;

    private boolean gravida;

    private boolean diabetico;

    private boolean hipertenso;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_alteracao")
    private LocalDateTime dataUltimaAlteracao;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataUltimaAlteracao = LocalDateTime.now();
    }
}
