package br.com.fiap.lanchonete.pedidoservicefase4.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrdemPedidoTest {

    @Test
    public void shouldCreateOrdemPedidoWithIdExterno() {
        OrdemPedido ordemPedido = new OrdemPedido(123L);
        assertEquals(123L, ordemPedido.getIdExterno());
        assertNull(ordemPedido.getStatus());
    }

    @Test
    public void shouldCreateOrdemPedidoWithIdExternoAndStatus() {
        OrdemPedido ordemPedido = new OrdemPedido(123L, "PROCESSING");
        assertEquals(123L, ordemPedido.getIdExterno());
        assertEquals("PROCESSING", ordemPedido.getStatus());
    }

    @Test
    public void shouldSetAndGetIdExterno() {
        OrdemPedido ordemPedido = new OrdemPedido(1L);
        ordemPedido.setIdExterno(123L);
        assertEquals(123L, ordemPedido.getIdExterno());
    }

    @Test
    public void shouldSetAndGetStatus() {
        OrdemPedido ordemPedido = new OrdemPedido(1L);
        ordemPedido.setStatus("PROCESSING");
        assertEquals("PROCESSING", ordemPedido.getStatus());
    }
}
