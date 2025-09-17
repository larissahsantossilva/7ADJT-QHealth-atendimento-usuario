package br.com.fiap.qhealth.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnamneseResponseTest {

    @Test
    void deveSetarEObterTodosOsCampos() {
        UUID id = UUID.randomUUID();
        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataUltimaAlteracao = LocalDateTime.now();

        AnamneseResponse response = new AnamneseResponse();
        response.setId(id);
        response.setFumante(true);
        response.setGravida(false);
        response.setDiabetico(true);
        response.setHipertenso(false);
        response.setDataCriacao(dataCriacao);
        response.setDataUltimaAlteracao(dataUltimaAlteracao);

        assertEquals(id, response.getId());
        assertTrue(response.isFumante());
        assertFalse(response.isGravida());
        assertTrue(response.isDiabetico());
        assertFalse(response.isHipertenso());
        assertEquals(dataCriacao, response.getDataCriacao());
        assertEquals(dataUltimaAlteracao, response.getDataUltimaAlteracao());
    }

    @Test
    void deveTestarToStringEqualsHashCodeBasicos() {
        UUID id = UUID.randomUUID();
        AnamneseResponse r1 = new AnamneseResponse();
        r1.setId(id);
        r1.setFumante(true);

        AnamneseResponse r2 = new AnamneseResponse();
        r2.setId(id);
        r2.setFumante(true);

        // Mesmo sem @EqualsAndHashCode, garantimos que os objetos não sejam null
        assertNotNull(r1.toString());

        // equals padrão de Object: apenas a mesma instância é igual
        assertNotEquals(r1, r2);
        assertEquals(r1, r1);

        // hashCode padrão de Object: apenas a mesma instância bate
        assertNotEquals(r1.hashCode(), r2.hashCode());
    }
}
