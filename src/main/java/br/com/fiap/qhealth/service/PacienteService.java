package br.com.fiap.qhealth.service;

import br.com.fiap.qhealth.exception.ResourceNotFoundException;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Paciente;
import br.com.fiap.qhealth.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.fiap.qhealth.utils.QHealthConstants.*;
import static br.com.fiap.qhealth.utils.QHealthUtils.uuidValidator;
import static java.time.LocalDateTime.now;
import static org.slf4j.LoggerFactory.getLogger;

@Service
@AllArgsConstructor
public class PacienteService {

    private static final Logger logger = getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;

    public Page<Paciente> listarPacientes(int page, int size) {
        return pacienteRepository.findAll(PageRequest.of(page, size));
    }

    public Paciente buscarPacientePorId(UUID id) {
        uuidValidator(id);
        return pacienteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ID_NAO_ENCONTRADO));
    }

    public Paciente criarPaciente(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
        } catch (DataAccessException e) {
            logger.error(ERRO_AO_CRIAR_PACIENTE, e);
            throw new UnprocessableEntityException(ERRO_AO_CRIAR_PACIENTE);
        }
    }

    public void atualizarPaciente(Paciente paciente, UUID id) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PACIENTE_NAO_ENCONTRADO));
        if (paciente != null) {
            if (paciente.getNome() != null) pacienteExistente.setNome(paciente.getNome());
            if (paciente.getEmail() != null) pacienteExistente.setEmail(paciente.getEmail());
            if (paciente.getLogin() != null) pacienteExistente.setLogin(paciente.getLogin());
            if (paciente.getSenha() != null) pacienteExistente.setSenha(paciente.getSenha());
            if (paciente.getCpf() != null) pacienteExistente.setCpf(paciente.getCpf());
            if (paciente.getTelefone() != null) pacienteExistente.setTelefone(paciente.getTelefone());
            if (paciente.getDataNascimento() != null) pacienteExistente.setDataNascimento(paciente.getDataNascimento());
            if (paciente.getDataUltimaAlteracao() != null) pacienteExistente.setDataUltimaAlteracao(now());
        }
        try {
            pacienteRepository.save(pacienteExistente);
        } catch (DataAccessException e) {
            logger.error(ERRO_AO_ALTERAR_PACIENTE, e);
            throw new UnprocessableEntityException(ERRO_AO_ALTERAR_PACIENTE);
        }
    }

    public void excluirPacientePorId(UUID id) {
        uuidValidator(id);
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PACIENTE_NAO_ENCONTRADO));
        UUID usuarioId = paciente.getId();
        try {
            pacienteRepository.deleteById(id);
        } catch (DataAccessException e) {
            logger.error(ERRO_AO_DELETAR_PACIENTE, e);
            throw new UnprocessableEntityException(ERRO_AO_DELETAR_PACIENTE);
        }
    }
}
