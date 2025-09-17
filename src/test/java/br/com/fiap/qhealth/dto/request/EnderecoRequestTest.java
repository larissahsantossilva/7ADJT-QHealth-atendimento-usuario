package br.com.fiap.qhealth.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoRequestTest {

    @Test
    void deveSetarTodosOsCamposCorretamente() {
        EnderecoRequest req = new EnderecoRequest();

        req.setRua("Rua A");
        req.setNumero(99);
        req.setCep("12345678");
        req.setComplemento("Apto 10");
        req.setBairro("Centro");
        req.setCidade("São Paulo");

        assertEquals("Rua A", req.getRua());
        assertEquals(99, req.getNumero());
        assertEquals("12345678", req.getCep());
        assertEquals("Apto 10", req.getComplemento());
        assertEquals("Centro", req.getBairro());
        assertEquals("São Paulo", req.getCidade());
    }
}
