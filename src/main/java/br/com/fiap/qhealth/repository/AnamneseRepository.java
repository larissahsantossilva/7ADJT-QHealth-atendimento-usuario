package br.com.fiap.qhealth.repository;

import br.com.fiap.qhealth.model.Anamnese;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;


public interface AnamneseRepository extends JpaRepository<Anamnese, UUID> {}