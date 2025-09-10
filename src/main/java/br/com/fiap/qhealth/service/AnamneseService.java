package br.com.fiap.qhealth.service;

import br.com.fiap.qhealth.exception.ResourceNotFoundException;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Anamnese;
import br.com.fiap.qhealth.model.Paciente;
import br.com.fiap.qhealth.repository.AnamneseRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.fiap.qhealth.utils.QHealthConstants.*;
import static br.com.fiap.qhealth.utils.QHealthUtils.uuidValidator;
import static java.time.LocalDateTime.now;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.data.domain.PageRequest.of;

@Service
@AllArgsConstructor
public class AnamneseService {

    private final AnamneseRepository repository;

    private static final Logger logger = getLogger(AnamneseService.class);

    public Page<Anamnese> listarPacientes(int page, int size) {
        return repository.findAll(of(page, size));
    }

    public Anamnese buscarAnamnesePorId(UUID id) {
        uuidValidator(id);
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ID_NAO_ENCONTRADO));
    }

    public Anamnese criarAnamnese(Anamnese request) {
        try {
            return repository.save(request);
        } catch (DataAccessException e) {
            logger.error(ERRO_AO_CRIAR_ANAMNESE, e);
            throw new UnprocessableEntityException(ERRO_AO_CRIAR_ANAMNESE);
        }
    }

    public void atualizarAnamneseExistente(Anamnese anamnese, UUID id) {
        Anamnese anamneseExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PACIENTE_NAO_ENCONTRADO));
        if (anamnese != null) {
            anamneseExistente.setDiabetico(anamnese.isDiabetico());
            anamneseExistente.setFumante(anamnese.isFumante());
            anamneseExistente.setGravida(anamnese.isGravida());
            anamneseExistente.setHipertenso(anamnese.isHipertenso());
            anamnese.setDataUltimaAlteracao(now());
        }
        try {
            repository.save(anamneseExistente);
        } catch (DataAccessException e) {
            logger.error(ERRO_AO_ALTERAR_PACIENTE, e);
            throw new UnprocessableEntityException(ERRO_AO_ALTERAR_PACIENTE);
        }
    }

    public void excluirAnamnesePorId(UUID id) {
        uuidValidator(id);
        Anamnese anamnese = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PACIENTE_NAO_ENCONTRADO));
        UUID usuarioId = anamnese.getId();
        try {
            repository.deleteById(id);
        } catch (DataAccessException e) {
            logger.error(ERRO_AO_DELETAR_PACIENTE, e);
            throw new UnprocessableEntityException(ERRO_AO_DELETAR_PACIENTE);
        }
    }
}