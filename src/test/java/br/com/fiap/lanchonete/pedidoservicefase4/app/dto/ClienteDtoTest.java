package br.com.fiap.lanchonete.pedidoservicefase4.app.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteDtoTest {

    @Test
    public void shouldCreateClienteDtoWithValidData() {
        ClienteDto clienteDto = new ClienteDto("John Doe", "123.456.789-00", "11 98765-4321", "123 Main St", "john.doe@example.com");
        assertEquals("John Doe", clienteDto.getNome());
        assertEquals("123.456.789-00", clienteDto.getCpf());
        assertEquals("11 98765-4321", clienteDto.getTelefone());
        assertEquals("123 Main St", clienteDto.getEndereco());
        assertEquals("john.doe@example.com", clienteDto.getEmail());
    }

    @Test
    public void shouldSetAndGetId() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1L);
        assertEquals(1L, clienteDto.getId());
    }

    @Test
    public void shouldSetAndGetName() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNome("John Doe");
        assertEquals("John Doe", clienteDto.getNome());
    }

    @Test
    public void shouldSetAndGetCpf() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setCpf("123.456.789-00");
        assertEquals("123.456.789-00", clienteDto.getCpf());
    }

    @Test
    public void shouldSetAndGetTelefone() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setTelefone("11 98765-4321");
        assertEquals("11 98765-4321", clienteDto.getTelefone());
    }

    @Test
    public void shouldSetAndGetEndereco() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setEndereco("123 Main St");
        assertEquals("123 Main St", clienteDto.getEndereco());
    }

    @Test
    public void shouldSetAndGetEmail() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", clienteDto.getEmail());
    }
}
