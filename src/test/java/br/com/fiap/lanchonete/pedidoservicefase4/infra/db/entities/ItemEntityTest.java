package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemEntityTest {

    @Mock
    private PedidoEntity mockPedido;

    private ItemEntity itemEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        itemEntity = new ItemEntity(1L, mockPedido, 2L, new BigDecimal("10.00"));
    }

    @Test
    public void shouldReturnCorrectId() {
        assertEquals(1L, itemEntity.getId());
    }

    @Test
    public void shouldReturnCorrectPedido() {
        assertEquals(mockPedido, itemEntity.getPedido());
    }

    @Test
    public void shouldReturnCorrectProduto() {
        assertEquals(2L, itemEntity.getProduto());
    }

    @Test
    public void shouldReturnCorrectPreco() {
        assertEquals(new BigDecimal("10.00"), itemEntity.getPreco());
    }

    @Test
    public void toStringShouldReturnCorrectFormat() {
        String expectedString = "ItemData [id=1, pedido=" + mockPedido + ", produto=2, preco=10.00]";
        assertEquals(expectedString, itemEntity.toString());
    }
}
