package br.com.fiap.qhealth.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    @Test
    void deveSetarDatasNoOnCreate() {
        Paciente paciente = new Paciente();
        assertNull(paciente.getDataCriacao());
        assertNull(paciente.getDataUltimaAlteracao());

        paciente.onCreate();

        assertNotNull(paciente.getDataCriacao());
        assertNotNull(paciente.getDataUltimaAlteracao());
        assertFalse(paciente.getDataCriacao().isAfter(LocalDateTime.now()));
    }

    @Test
    void deveAtualizarDataUltimaAlteracaoNoOnUpdate() throws InterruptedException {
        Paciente paciente = new Paciente();
        paciente.onCreate();
        LocalDateTime antes = paciente.getDataUltimaAlteracao();
        Thread.sleep(10); // garante diferença perceptível

        paciente.onUpdate();

        assertTrue(paciente.getDataUltimaAlteracao().isAfter(antes));
    }

    @Test
    void deveCriarComTodosOsCampos() {
        String cpf = "12345678901";
        Endereco endereco = new Endereco();
        LocalDate nascimento = LocalDate.of(2000, 1, 1);
        LocalDateTime agora = LocalDateTime.now();
        Paciente p = new Paciente(
                cpf,
                "Nome Teste",
                "email@teste.com",
                "loginUser",
                "senha123",
                "M",
                "11999999999",
                nascimento,
                endereco,
                agora,
                agora
        );

        assertEquals(cpf, p.getCpf());
        assertEquals("Nome Teste", p.getNome());
        assertEquals("email@teste.com", p.getEmail());
        assertEquals("loginUser", p.getLogin());
        assertEquals("senha123", p.getSenha());
        assertEquals("12345678901", p.getCpf());
        assertEquals("M", p.getGenero());
        assertEquals("11999999999", p.getTelefone());
        assertEquals(nascimento, p.getDataNascimento());
        assertEquals(endereco, p.getEndereco());
        assertEquals(agora, p.getDataCriacao());
        assertEquals(agora, p.getDataUltimaAlteracao());
    }

    @Test
    void settersEGettersDevemFuncionar() {
        Paciente p = new Paciente();
        String cpf = "98765432100";
        Endereco e = new Endereco();
        LocalDate nascimento = LocalDate.of(1995, 5, 15);
        LocalDateTime agora = LocalDateTime.now();

        p.setNome("Maria");
        p.setEmail("maria@teste.com");
        p.setLogin("mariaLogin");
        p.setSenha("senha123");
        p.setCpf(cpf);
        p.setGenero("F");
        p.setTelefone("1188888888");
        p.setDataNascimento(nascimento);
        p.setEndereco(e);
        p.setDataCriacao(agora);
        p.setDataUltimaAlteracao(agora);

        assertEquals(cpf, p.getCpf());
        assertEquals("Maria", p.getNome());
        assertEquals("maria@teste.com", p.getEmail());
        assertEquals("mariaLogin", p.getLogin());
        assertEquals("senha123", p.getSenha());
        assertEquals("98765432100", p.getCpf());
        assertEquals("F", p.getGenero());
        assertEquals("1188888888", p.getTelefone());
        assertEquals(nascimento, p.getDataNascimento());
        assertEquals(e, p.getEndereco());
        assertEquals(agora, p.getDataCriacao());
        assertEquals(agora, p.getDataUltimaAlteracao());
    }
}
