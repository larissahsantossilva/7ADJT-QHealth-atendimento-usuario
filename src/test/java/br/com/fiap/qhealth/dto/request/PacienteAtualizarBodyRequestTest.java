package br.com.fiap.qhealth.dto.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteAtualizarBodyRequestTest {

    @Test
    void deveSetarTodosOsCamposCorretamente() {
        EnderecoAtualizarRequest endereco = new EnderecoAtualizarRequest();
        endereco.setRua("Rua das Flores");
        endereco.setNumero(100);
        endereco.setCep("12345678");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setComplemento("Apto 1");

        PacienteAtualizarBodyRequest req = new PacienteAtualizarBodyRequest();
        req.setNome("João");
        req.setEmail("joao@email.com");
        req.setLogin("joao123");
        req.setSenha("senha123");
        req.setCpf("12345678901");
        req.setGenero("M");
        req.setTelefone("11999999999");
        req.setDataNascimento(LocalDate.of(1990, 1, 1));
        req.setEndereco(endereco);

        assertEquals("João", req.getNome());
        assertEquals("joao@email.com", req.getEmail());
        assertEquals("joao123", req.getLogin());
        assertEquals("senha123", req.getSenha());
        assertEquals("12345678901", req.getCpf());
        assertEquals("M", req.getGenero());
        assertEquals("11999999999", req.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), req.getDataNascimento());
        assertEquals(endereco, req.getEndereco());
    }

    @Test
    void deveTestarConstrutoresGettersSettersToStringEqualsHashCode() {
        LocalDate nascimento = LocalDate.of(1995, 5, 15);
        EnderecoAtualizarRequest endereco = new EnderecoAtualizarRequest();
        endereco.setRua("Rua Y");
        endereco.setNumero(456);
        endereco.setCep("87654321");
        endereco.setComplemento("Apto");
        endereco.setBairro("Bairro Legal");
        endereco.setCidade("Rio de Janeiro");

        // Construtor completo
        PacienteAtualizarBodyRequest req1 = new PacienteAtualizarBodyRequest(
                "Beltrano", "beltrano@email.com", "beltranoLogin",
                "senha456", "10987654321", "F", "21988888888",
                nascimento, endereco
        );

        // Testa getters
        assertEquals("Beltrano", req1.getNome());
        assertEquals("beltrano@email.com", req1.getEmail());
        assertEquals("beltranoLogin", req1.getLogin());
        assertEquals("senha456", req1.getSenha());
        assertEquals("10987654321", req1.getCpf());
        assertEquals("F", req1.getGenero());
        assertEquals("21988888888", req1.getTelefone());
        assertEquals(nascimento, req1.getDataNascimento());
        assertEquals(endereco, req1.getEndereco());

        // Testa setters
        PacienteAtualizarBodyRequest req2 = new PacienteAtualizarBodyRequest();
        req2.setNome("Beltrano");
        req2.setEmail("beltrano@email.com");
        req2.setLogin("beltranoLogin");
        req2.setSenha("senha456");
        req2.setCpf("10987654321");
        req2.setGenero("F");
        req2.setTelefone("21988888888");
        req2.setDataNascimento(nascimento);
        req2.setEndereco(endereco);

        // equals / hashCode / canEqual
        assertEquals(req1, req2);
        assertEquals(req1.hashCode(), req2.hashCode());
        assertTrue(req1.canEqual(req2));
        assertNotEquals(req1, new Object());
        assertNotEquals(null, req1);

        // toString
        assertNotNull(req1.toString());
    }
}
