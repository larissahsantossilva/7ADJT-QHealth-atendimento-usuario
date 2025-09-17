package br.com.fiap.qhealth.dto.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PacienteBodyRequestTest {

    @Test
    void deveTestarConstrutoresGettersSettersToStringEqualsHashCode() {
        LocalDate nascimento = LocalDate.of(1990, 1, 1);
        EnderecoRequest endereco = new EnderecoRequest();
        endereco.setRua("Rua X");
        endereco.setNumero(123);
        endereco.setCep("12345678");
        endereco.setComplemento("Casa");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");

        // Construtor completo (gerado pelo Lombok @AllArgsConstructor)
        PacienteBodyRequest req1 = new PacienteBodyRequest(
                "Fulano", "fulano@email.com", "fulanologin",
                "senha123", "12345678901", "M", "11999999999",
                nascimento, endereco
        );

        // Testa getters
        assertEquals("Fulano", req1.getNome());
        assertEquals("fulano@email.com", req1.getEmail());
        assertEquals("fulanologin", req1.getLogin());
        assertEquals("senha123", req1.getSenha());
        assertEquals("12345678901", req1.getCpf());
        assertEquals("M", req1.getGenero());
        assertEquals("11999999999", req1.getTelefone());
        assertEquals(nascimento, req1.getDataNascimento());
        assertEquals(endereco, req1.getEndereco());

        // Testa setters
        PacienteBodyRequest req2 = new PacienteBodyRequest();
        req2.setNome("Fulano");
        req2.setEmail("fulano@email.com");
        req2.setLogin("fulanologin");
        req2.setSenha("senha123");
        req2.setCpf("12345678901");
        req2.setGenero("M");
        req2.setTelefone("11999999999");
        req2.setDataNascimento(nascimento);
        req2.setEndereco(endereco);

        // equals / hashCode / canEqual
        assertEquals(req1, req2);
        assertEquals(req1.hashCode(), req2.hashCode());
        assertTrue(req1.canEqual(req2));
        assertNotEquals(req1, new Object()); // compara com tipo diferente
        assertNotEquals(null, req1);

        // toString
        assertNotNull(req1.toString());
    }
}
