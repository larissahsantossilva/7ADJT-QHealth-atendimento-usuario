package br.com.fiap.qhealth.controller;

import br.com.fiap.qhealth.dto.request.PacienteAtualizarBodyRequest;
import br.com.fiap.qhealth.dto.request.PacienteBodyRequest;
import br.com.fiap.qhealth.dto.response.PacienteBodyResponse;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Paciente;
import br.com.fiap.qhealth.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PacienteControllerTest {

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private PacienteController pacienteController;

    private Paciente paciente;
    private PacienteBodyRequest bodyRequest;
    private PacienteAtualizarBodyRequest atualizarRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paciente = new Paciente();
        paciente.setCpf("41633271626");
        paciente.setNome("João da Silva");
        paciente.setLogin("joao.silva");
        paciente.setTelefone("11999999999");

        bodyRequest = new PacienteBodyRequest();
        bodyRequest.setNome("João da Silva");
        bodyRequest.setLogin("joao.silva");
        bodyRequest.setTelefone("11999999999");

        atualizarRequest = new PacienteAtualizarBodyRequest();
        atualizarRequest.setNome("João Atualizado");
        atualizarRequest.setLogin("joao.atualizado");
        atualizarRequest.setTelefone("11888888888");
    }

    @Test
    void deveListarPacientesComSucesso() {
        Page<Paciente> page = new PageImpl<>(List.of(paciente));
        when(pacienteService.listarPacientes(0,10)).thenReturn(page);

        ResponseEntity<List<PacienteBodyResponse>> response = pacienteController.listarPacientes(0,10);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(paciente.getNome(), response.getBody().get(0).getNome());
        verify(pacienteService).listarPacientes(0,10);
    }

    @Test
    void deveBuscarPacientePorIdComSucesso() {
        when(pacienteService.buscarPacientePorId(paciente.getCpf())).thenReturn(paciente);

        ResponseEntity<PacienteBodyResponse> response = pacienteController.buscarPacientePorId(paciente.getCpf());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(paciente.getCpf(), response.getBody().getCpf());
        assertEquals(paciente.getNome(), response.getBody().getNome());
        verify(pacienteService).buscarPacientePorId(paciente.getCpf());
    }

    @Test
    void deveRetornar404QuandoPacienteNaoEncontrado() {
        when(pacienteService.buscarPacientePorId(paciente.getCpf())).thenReturn(null);

        ResponseEntity<PacienteBodyResponse> response = pacienteController.buscarPacientePorId(paciente.getCpf());

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(pacienteService).buscarPacientePorId(paciente.getCpf());
    }

    @Test
    void deveCriarPacienteComSucesso() {
        when(pacienteService.criarPaciente(any())).thenReturn(paciente);

        ResponseEntity<String> response = pacienteController.criarPaciente(bodyRequest);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(paciente.getCpf(), response.getBody());
        verify(pacienteService).criarPaciente(any());
    }

    @Test
    void deveAtualizarPacienteComSucesso() {
        doNothing().when(pacienteService).atualizarPacienteExistente(any(), eq(paciente.getCpf()));

        ResponseEntity<String> response = pacienteController.atualizarPaciente(paciente.getCpf(), atualizarRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Paciente atualizado com sucesso", response.getBody());
        verify(pacienteService).atualizarPacienteExistente(any(), eq(paciente.getCpf()));
    }

    @Test
    void deveExcluirPacienteComSucesso() {
        doNothing().when(pacienteService).excluirPacientePorId(paciente.getCpf());

        ResponseEntity<String> response = pacienteController.excluirPacientePorId(paciente.getCpf());

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(pacienteService).excluirPacientePorId(paciente.getCpf());
    }
}
