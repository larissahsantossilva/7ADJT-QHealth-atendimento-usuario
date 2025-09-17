package br.com.fiap.qhealth.service;

import br.com.fiap.qhealth.exception.ResourceNotFoundException;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Endereco;
import br.com.fiap.qhealth.model.Paciente;
import br.com.fiap.qhealth.repository.EnderecoRepository;
import br.com.fiap.qhealth.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private PacienteService service;

    private UUID id;
    private Paciente paciente;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        endereco = new Endereco(UUID.randomUUID(), "Rua A", 123, "12345678",
                "Apto 1", "Bairro", "Cidade", LocalDateTime.now(), LocalDateTime.now());
        paciente = new Paciente(id, "João", "joao@email.com", "login",
                "senha123", "12345678901", "M", "11999999999",
                LocalDate.of(1990, 1, 1), endereco,
                LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void deveListarPacientesComSucesso() {
        Page<Paciente> page = new PageImpl<>(List.of(paciente));
        when(pacienteRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Paciente> resultado = service.listarPacientes(0, 10);

        assertEquals(1, resultado.getContent().size());
        verify(pacienteRepository).findAll(any(PageRequest.class));
    }

    @Test
    void deveBuscarPacientePorId() {
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        Paciente result = service.buscarPacientePorId(id);

        assertEquals(id, result.getId());
        verify(pacienteRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoEncontrado() {
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarPacientePorId(id));
        verify(pacienteRepository).findById(id);
    }

    @Test
    void deveCriarPacienteComEndereco() {
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente result = service.criarPaciente(paciente);

        assertNotNull(result);
        verify(enderecoRepository).save(endereco);
        verify(pacienteRepository).save(paciente);
    }

    @Test
    void deveCriarPacienteSemEndereco() {
        paciente.setEndereco(null);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente result = service.criarPaciente(paciente);

        assertNotNull(result);
        verify(enderecoRepository, never()).save(any());
        verify(pacienteRepository).save(paciente);
    }

    @Test
    void deveLancarExcecaoAoCriarPacienteQuandoErroNoBanco() {
        when(pacienteRepository.save(any())).thenThrow(new DataAccessException("Erro ao salvar") {});

        assertThrows(UnprocessableEntityException.class, () -> service.criarPaciente(paciente));
        verify(pacienteRepository).save(paciente);
    }

    @Test
    void deveAtualizarPacienteComSucesso() {
        Paciente novo = new Paciente();
        novo.setNome("Maria");
        novo.setEmail("maria@email.com");

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        service.atualizarPacienteExistente(novo, id);

        verify(pacienteRepository).findById(id);
        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoNaoEncontrado() {
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.atualizarPacienteExistente(paciente, id));
        verify(pacienteRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoErroNoBanco() {
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));
        when(pacienteRepository.save(any())).thenThrow(new DataAccessException("Erro ao salvar") {});

        assertThrows(UnprocessableEntityException.class, () -> service.atualizarPacienteExistente(paciente, id));
        verify(pacienteRepository).findById(id);
        verify(pacienteRepository).save(any(Paciente.class));
    }

    @Test
    void deveExcluirPacienteComSucesso() {
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));
        doNothing().when(pacienteRepository).deleteById(id);

        service.excluirPacientePorId(id);

        verify(pacienteRepository).findById(id);
        verify(pacienteRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoAoExcluirQuandoNaoEncontrado() {
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.excluirPacientePorId(id));
        verify(pacienteRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoAoExcluirQuandoErroNoBanco() {
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));
        doThrow(new DataAccessException("Erro ao deletar") {}).when(pacienteRepository).deleteById(id);

        assertThrows(UnprocessableEntityException.class, () -> service.excluirPacientePorId(id));
        verify(pacienteRepository).findById(id);
        verify(pacienteRepository).deleteById(id);
    }
}
