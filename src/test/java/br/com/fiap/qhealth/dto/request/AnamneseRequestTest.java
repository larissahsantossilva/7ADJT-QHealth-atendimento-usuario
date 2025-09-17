package br.com.fiap.qhealth.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnamneseRequestTest {

    @Test
    void deveCriarComValoresEDepoisAlterar() {
        // cria com construtor vazio
        AnamneseRequest request = new AnamneseRequest();

        // seta valores via setters
        request.setFumante(true);
        request.setGravida(false);
        request.setDiabetico(true);
        request.setHipertenso(false);

        // valida getters
        assertTrue(request.isFumante());
        assertFalse(request.isGravida());
        assertTrue(request.isDiabetico());
        assertFalse(request.isHipertenso());

        // altera novamente
        request.setFumante(false);
        request.setGravida(true);
        assertFalse(request.isFumante());
        assertTrue(request.isGravida());
    }
}
