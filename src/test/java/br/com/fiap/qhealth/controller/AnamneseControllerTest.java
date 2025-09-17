package br.com.fiap.qhealth.controller;

import br.com.fiap.qhealth.dto.request.AnamneseRequest;
import br.com.fiap.qhealth.dto.response.AnamneseResponse;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Anamnese;
import br.com.fiap.qhealth.service.AnamneseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnamneseControllerTest {

    @Mock
    private AnamneseService anamneseService;

    @InjectMocks
    private AnamneseController anamneseController;

    private Anamnese anamnese;
    private AnamneseRequest request;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        anamnese = new Anamnese();
        anamnese.setId(UUID.randomUUID());
        anamnese.setFumante(true);
        anamnese.setDiabetico(false);
        anamnese.setHipertenso(true);
        anamnese.setDataCriacao(LocalDateTime.now());

        request = new AnamneseRequest();
        request.setFumante(true);
        request.setDiabetico(false);
        request.setHipertenso(true);
    }

    @Test
    void deveListarAnamnesesComSucesso() {
        Page<Anamnese> page = new PageImpl<>(List.of(anamnese));
        when(anamneseService.listarAnamneses(0,10)).thenReturn(page);

        ResponseEntity<List<AnamneseResponse>> response = anamneseController.listarAnamneses(0,10);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(anamneseService).listarAnamneses(0,10);
    }

    @Test
    void deveBuscarAnamnesePorIdComSucesso() {
        when(anamneseService.buscarAnamnesePorId(anamnese.getId())).thenReturn(anamnese);

        ResponseEntity<AnamneseResponse> response = anamneseController.buscarAnamnesePorId(anamnese.getId());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(anamnese.getId(), response.getBody().getId());
        verify(anamneseService).buscarAnamnesePorId(anamnese.getId());
    }

    @Test
    void deveRetornar404QuandoAnamneseNaoEncontrada() {
        when(anamneseService.buscarAnamnesePorId(anamnese.getId())).thenReturn(null);

        ResponseEntity<AnamneseResponse> response = anamneseController.buscarAnamnesePorId(anamnese.getId());

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(anamneseService).buscarAnamnesePorId(anamnese.getId());
    }

    @Test
    void deveCriarAnamneseComSucesso() {
        when(anamneseService.criarAnamnese(any())).thenReturn(anamnese);

        ResponseEntity<UUID> response = anamneseController.criarAnamnese(request);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(anamnese.getId(), response.getBody());
        verify(anamneseService).criarAnamnese(any());
    }

    @Test
    void deveAtualizarAnamneseComSucesso() {
        doNothing().when(anamneseService).atualizarAnamneseExistente(any(), eq(anamnese.getId()));

        ResponseEntity<String> response = anamneseController.atualizarAnamnese(anamnese.getId(), request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Anamnese atualizado com sucesso", response.getBody());
        verify(anamneseService).atualizarAnamneseExistente(any(), eq(anamnese.getId()));
    }

    @Test
    void deveExcluirAnamneseComSucesso() {
        doNothing().when(anamneseService).excluirAnamnesePorId(anamnese.getId());

        ResponseEntity<String> response = anamneseController.excluirAnamnesePorId(anamnese.getId());

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(anamneseService).excluirAnamnesePorId(anamnese.getId());
    }
}
