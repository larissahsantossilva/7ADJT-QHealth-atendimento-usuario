package br.com.fiap.qhealth.service;

import br.com.fiap.qhealth.model.Paciente;
import br.com.fiap.qhealth.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.fiap.qhealth.utils.QHealthConstants.ID_NAO_ENCONTRADO;
import static br.com.fiap.qhealth.utils.QHealthUtils.uuidValidator;
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

//    public Paciente criarPaciente(Paciente paciente) {
//        try {
//            return pacienteRepository.save(paciente);
//        } catch (DataAccessException e) {
//            logger.error(ERRO_AO_CRIAR_PACIENTE, e);
//            throw new UnprocessableEntityException(ERRO_AO_CRIAR_PACIENTE);
//        }
//    }
//
//    public Paciente atualizarPaciente(Paciente paciente, UUID id) {
//        Paciente pacienteExistente = pacienteRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(PACIENTE_NAO_ENCONTRADO));
//
//        if (paciente.getUsuario() != null) {
//            Usuario usuarioExistente = pacienteExistente.getUsuario();
//            Usuario usuarioAtualizado = paciente.getUsuario();
//
//            if (usuarioAtualizado.getNome() != null) usuarioExistente.setNome(usuarioAtualizado.getNome());
//            if (usuarioAtualizado.getEmail() != null) usuarioExistente.setEmail(usuarioAtualizado.getEmail());
//            if (usuarioAtualizado.getLogin() != null) usuarioExistente.setLogin(usuarioAtualizado.getLogin());
//            if (usuarioAtualizado.getSenha() != null) usuarioExistente.setSenha(usuarioAtualizado.getSenha());
//            if (usuarioAtualizado.getCpf() != null) usuarioExistente.setCpf(usuarioAtualizado.getCpf());
//            if (usuarioAtualizado.getTelefone() != null) usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());
//            if (usuarioAtualizado.getDataNascimento() != null) usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
//            if (usuarioAtualizado.getUltimaAlteracao() != null) usuarioExistente.setUltimaAlteracao(LocalDateTime.now());
//        }
//
//        pacienteExistente.setUltimaAlteracao(LocalDateTime.now());
//
//        try {
//            return pacienteRepository.save(pacienteExistente);
//        } catch (DataAccessException e) {
//            logger.error(ERRO_AO_ALTERAR_PACIENTE, e);
//            throw new UnprocessableEntityException(ERRO_AO_ALTERAR_PACIENTE);
//        }
//    }
//
//    public void excluirPacientePorId(UUID id) {
//        try {
//            pacienteRepository.deleteById(id);
//        } catch (DataAccessException e) {
//            logger.error(ERRO_AO_DELETAR_PACIENTE, e);
//            throw new UnprocessableEntityException(ERRO_AO_DELETAR_PACIENTE);
//        }
//    }
}
