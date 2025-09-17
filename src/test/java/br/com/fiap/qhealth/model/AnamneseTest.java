package br.com.fiap.qhealth.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnamneseTest {

    @Test
    void deveSetarDatasNoOnCreate() {
        Anamnese anamnese = new Anamnese();
        assertNull(anamnese.getDataCriacao());
        assertNull(anamnese.getDataUltimaAlteracao());

        anamnese.onCreate();

        assertNotNull(anamnese.getDataCriacao());
        assertNotNull(anamnese.getDataUltimaAlteracao());
        assertFalse(anamnese.getDataCriacao().isAfter(LocalDateTime.now()));
    }

    @Test
    void deveAtualizarDataUltimaAlteracaoNoOnUpdate() throws InterruptedException {
        Anamnese anamnese = new Anamnese();
        anamnese.onCreate();

        LocalDateTime antiga = anamnese.getDataUltimaAlteracao();
        Thread.sleep(10); // garante diferença de tempo

        anamnese.onUpdate();

        assertTrue(anamnese.getDataUltimaAlteracao().isAfter(antiga));
    }

    @Test
    void deveCriarComTodosCampos() {
        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();
        Anamnese a = new Anamnese(id, true, false, true, false, agora, agora);

        assertEquals(id, a.getId());
        assertTrue(a.isFumante());
        assertFalse(a.isGravida());
        assertTrue(a.isDiabetico());
        assertFalse(a.isHipertenso());
        assertEquals(agora, a.getDataCriacao());
        assertEquals(agora, a.getDataUltimaAlteracao());
    }

    @Test
    void settersAndGettersDevemFuncionar() {
        Anamnese anamnese = new Anamnese();
        UUID id = UUID.randomUUID();
        LocalDateTime agora = LocalDateTime.now();

        anamnese.setId(id);
        anamnese.setFumante(true);
        anamnese.setGravida(false);
        anamnese.setDiabetico(true);
        anamnese.setHipertenso(false);
        anamnese.setDataCriacao(agora);
        anamnese.setDataUltimaAlteracao(agora);

        assertEquals(id, anamnese.getId());
        assertTrue(anamnese.isFumante());
        assertFalse(anamnese.isGravida());
        assertTrue(anamnese.isDiabetico());
        assertFalse(anamnese.isHipertenso());
        assertEquals(agora, anamnese.getDataCriacao());
        assertEquals(agora, anamnese.getDataUltimaAlteracao());
    }
}
