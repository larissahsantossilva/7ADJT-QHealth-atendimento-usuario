package br.com.fiap.qhealth.repository;

import br.com.fiap.qhealth.model.Endereco;
import br.com.fiap.qhealth.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
