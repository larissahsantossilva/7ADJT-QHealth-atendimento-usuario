package br.com.fiap.qhealth.service;

import br.com.fiap.qhealth.exception.ResourceNotFoundException;
import br.com.fiap.qhealth.exception.UnprocessableEntityException;
import br.com.fiap.qhealth.model.Anamnese;
import br.com.fiap.qhealth.repository.AnamneseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AnamneseServiceTest {

    @Mock
    private AnamneseRepository repository;

    @InjectMocks
    private AnamneseService service;

    private UUID id;
    private Anamnese anamnese;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = UUID.randomUUID();
        anamnese = new Anamnese(id, true, false, true, false,
                LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void deveListarAnamnesesComSucesso() {
        Page<Anamnese> page = new PageImpl<>(List.of(anamnese));
        when(repository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Anamnese> resultado = service.listarAnamneses(0, 10);

        assertEquals(1, resultado.getContent().size());
        verify(repository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void deveBuscarAnamnesePorId() {
        when(repository.findById(id)).thenReturn(Optional.of(anamnese));

        Anamnese result = service.buscarAnamnesePorId(id);

        assertEquals(id, result.getId());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoAnamneseNaoEncontrado() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.buscarAnamnesePorId(id));
        verify(repository).findById(id);
    }

    @Test
    void deveCriarAnamneseComSucesso() {
        when(repository.save(any())).thenReturn(anamnese);

        Anamnese result = service.criarAnamnese(anamnese);

        assertEquals(id, result.getId());
        verify(repository).save(anamnese);
    }

    @Test
    void deveLancarExcecaoAoCriarAnamneseQuandoErroNoBanco() {
        when(repository.save(any())).thenThrow(new DataAccessException("Erro ao salvar") {});

        assertThrows(UnprocessableEntityException.class, () -> service.criarAnamnese(anamnese));
        verify(repository).save(anamnese);
    }

    @Test
    void deveAtualizarAnamneseComSucesso() {
        Anamnese novo = new Anamnese();
        novo.setFumante(false);
        novo.setGravida(true);
        novo.setDiabetico(false);
        novo.setHipertenso(true);

        when(repository.findById(id)).thenReturn(Optional.of(anamnese));
        when(repository.save(any())).thenReturn(anamnese);

        service.atualizarAnamneseExistente(novo, id);

        verify(repository).findById(id);
        verify(repository).save(any(Anamnese.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoNaoEncontrado() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.atualizarAnamneseExistente(anamnese, id));
        verify(repository).findById(id);
    }

    @Test
    void deveLancarExcecaoAoAtualizarQuandoErroNoBanco() {
        when(repository.findById(id)).thenReturn(Optional.of(anamnese));
        when(repository.save(any())).thenThrow(new DataAccessException("Erro ao salvar") {});

        assertThrows(UnprocessableEntityException.class, () -> service.atualizarAnamneseExistente(anamnese, id));
        verify(repository).findById(id);
        verify(repository).save(any(Anamnese.class));
    }

    @Test
    void deveExcluirAnamneseComSucesso() {
        when(repository.findById(id)).thenReturn(Optional.of(anamnese));
        doNothing().when(repository).deleteById(id);

        service.excluirAnamnesePorId(id);

        verify(repository).findById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoAoExcluirQuandoNaoEncontrado() {
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.excluirAnamnesePorId(id));
        verify(repository).findById(id);
    }

    @Test
    void deveLancarExcecaoAoExcluirQuandoErroNoBanco() {
        when(repository.findById(id)).thenReturn(Optional.of(anamnese));
        doThrow(new DataAccessException("Erro ao deletar") {}).when(repository).deleteById(id);

        assertThrows(UnprocessableEntityException.class, () -> service.excluirAnamnesePorId(id));
        verify(repository).findById(id);
        verify(repository).deleteById(id);
    }
}
