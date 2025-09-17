package br.com.fiap.qhealth.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveSetarDatasNoOnCreate() {
        Endereco endereco = new Endereco();
        assertNull(endereco.getDataCriacao());
        assertNull(endereco.getDataUltimaAlteracao());

        endereco.onCreate();

        assertNotNull(endereco.getDataCriacao());
        assertNotNull(endereco.getDataUltimaAlteracao());
        assertFalse(endereco.getDataCriacao().isAfter(LocalDateTime.now()));
    }

    @Test
    void deveAtualizarDataUltimaAlteracaoNoOnUpdate() throws InterruptedException {
        Endereco endereco = new Endereco();
        endereco.onCreate();
        LocalDateTime antiga = endereco.getDataUltimaAlteracao();
        Thread.sleep(10); // garante diferença perceptível

        endereco.onUpdate();

        assertTrue(endereco.getDataUltimaAlteracao().isAfter(antiga));
    }

    @Test
    void deveCriarComTodosOsCampos() {
        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();
        Endereco e = new Endereco(id, "Rua A", 123, "12345678", "Apto 1", "Centro", "Cidade X", agora, agora);

        assertEquals(id, e.getId());
        assertEquals("Rua A", e.getRua());
        assertEquals(123, e.getNumero());
        assertEquals("12345678", e.getCep());
        assertEquals("Apto 1", e.getComplemento());
        assertEquals("Centro", e.getBairro());
        assertEquals("Cidade X", e.getCidade());
        assertEquals(agora, e.getDataCriacao());
        assertEquals(agora, e.getDataUltimaAlteracao());
    }

    @Test
    void settersEGettersDevemFuncionar() {
        Endereco e = new Endereco();
        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        e.setId(id);
        e.setRua("Rua B");
        e.setNumero(456);
        e.setCep("87654321");
        e.setComplemento("Casa");
        e.setBairro("Bairro Y");
        e.setCidade("Cidade Z");
        e.setDataCriacao(agora);
        e.setDataUltimaAlteracao(agora);

        assertEquals(id, e.getId());
        assertEquals("Rua B", e.getRua());
        assertEquals(456, e.getNumero());
        assertEquals("87654321", e.getCep());
        assertEquals("Casa", e.getComplemento());
        assertEquals("Bairro Y", e.getBairro());
        assertEquals("Cidade Z", e.getCidade());
        assertEquals(agora, e.getDataCriacao());
        assertEquals(agora, e.getDataUltimaAlteracao());
    }
}
