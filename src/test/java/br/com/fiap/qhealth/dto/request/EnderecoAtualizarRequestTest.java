package br.com.fiap.qhealth.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoAtualizarRequestTest {

    @Test
    void deveSetarTodosOsCamposCorretamente() {
        EnderecoAtualizarRequest req = new EnderecoAtualizarRequest();

        req.setRua("Rua das Flores");
        req.setNumero(123);
        req.setCep("12345678");
        req.setComplemento("Apto 202");
        req.setBairro("Centro");
        req.setCidade("São Paulo");

        assertEquals("Rua das Flores", req.getRua());
        assertEquals(123, req.getNumero());
        assertEquals("12345678", req.getCep());
        assertEquals("Apto 202", req.getComplemento());
        assertEquals("Centro", req.getBairro());
        assertEquals("São Paulo", req.getCidade());
    }
}
