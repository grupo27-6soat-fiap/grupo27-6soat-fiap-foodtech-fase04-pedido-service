package br.com.fiap.lanchonete.pedidoservicefase4.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void shouldCreateClienteWithValidData() {
        Cliente cliente = new Cliente("John Doe", "123.456.789-00", "11 98765-4321", "123 Main St", "john.doe@example.com");
        assertEquals("John Doe", cliente.getNome());
        assertEquals("123.456.789-00", cliente.getCpf());
        assertEquals("11 98765-4321", cliente.getTelefone());
        assertEquals("123 Main St", cliente.getEndereco());
        assertEquals("john.doe@example.com", cliente.getEmail());
    }

    @Test
    public void shouldSetAndGetId() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        assertEquals(1L, cliente.getId());
    }

    @Test
    public void shouldSetAndGetName() {
        Cliente cliente = new Cliente();
        cliente.setNome("John Doe");
        assertEquals("John Doe", cliente.getNome());
    }

    @Test
    public void shouldSetAndGetCpf() {
        Cliente cliente = new Cliente();
        cliente.setCpf("123.456.789-00");
        assertEquals("123.456.789-00", cliente.getCpf());
    }

    @Test
    public void shouldSetAndGetTelefone() {
        Cliente cliente = new Cliente();
        cliente.setTelefone("11 98765-4321");
        assertEquals("11 98765-4321", cliente.getTelefone());
    }

    @Test
    public void shouldSetAndGetEndereco() {
        Cliente cliente = new Cliente();
        cliente.setEndereco("123 Main St");
        assertEquals("123 Main St", cliente.getEndereco());
    }

    @Test
    public void shouldSetAndGetEmail() {
        Cliente cliente = new Cliente();
        cliente.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", cliente.getEmail());
    }
}