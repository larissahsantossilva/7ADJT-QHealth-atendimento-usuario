package br.com.fiap.qhealth.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoBodyResponseTest {

    @Test
    void deveSetarEObterTodosOsCampos() {
        UUID id = UUID.randomUUID();
        LocalDateTime criacao = LocalDateTime.now();
        LocalDateTime ultimaAlteracao = LocalDateTime.now();

        EnderecoBodyResponse response = new EnderecoBodyResponse();
        response.setId(id);
        response.setRua("Rua Teste");
        response.setNumero(123);
        response.setCep("12345678");
        response.setComplemento("Bloco A");
        response.setBairro("Centro");
        response.setCidade("São Paulo");
        response.setDataCriacao(criacao);
        response.setDataUltimaAlteracao(ultimaAlteracao);

        assertEquals(id, response.getId());
        assertEquals("Rua Teste", response.getRua());
        assertEquals(123, response.getNumero());
        assertEquals("12345678", response.getCep());
        assertEquals("Bloco A", response.getComplemento());
        assertEquals("Centro", response.getBairro());
        assertEquals("São Paulo", response.getCidade());
        assertEquals(criacao, response.getDataCriacao());
        assertEquals(ultimaAlteracao, response.getDataUltimaAlteracao());
    }

    @Test
    void deveTestarEqualsHashCodeEToString() {
        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        EnderecoBodyResponse e1 = new EnderecoBodyResponse(id, "Rua X", 10,
                "87654321", "Casa", "Bairro Y", "Cidade Z", agora, agora);

        EnderecoBodyResponse e2 = new EnderecoBodyResponse(id, "Rua X", 10,
                "87654321", "Casa", "Bairro Y", "Cidade Z", agora, agora);

        // toString não deve ser nulo
        assertNotNull(e1.toString());

        // equals e hashCode devem considerar todos os campos (Lombok @Data/@EqualsAndHashCode não foi usado, então default é Object)
        assertNotEquals(e1, e2); // objetos diferentes mesmo com dados iguais
        assertEquals(e1, e1);    // o mesmo objeto é igual a si mesmo
        assertNotEquals(e1.hashCode(), e2.hashCode()); // hashCodes diferentes para instâncias diferentes
    }
}
