package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PedidoEntityTest {

    @Mock
    private ItemEntity mockItem1, mockItem2;

    private PedidoEntity pedidoEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidoEntity = new PedidoEntity(1L, 1L, Arrays.asList(mockItem1, mockItem2), new BigDecimal("20.00"), StatusEnum.PENDING, StatusEnum.PENDING, "qrData", 1L, 1L, StatusEnum.PENDING, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    public void shouldReturnCorrectId() {
        assertEquals(1L, pedidoEntity.getId());
    }

    @Test
    public void shouldReturnCorrectCliente() {
        assertEquals(1L, pedidoEntity.getCliente());
    }

    @Test
    public void shouldReturnCorrectItens() {
        assertEquals(Arrays.asList(mockItem1, mockItem2), pedidoEntity.getItens());
    }

    @Test
    public void shouldReturnCorrectPreco() {
        assertEquals(new BigDecimal("20.00"), pedidoEntity.getPreco());
    }

    @Test
    public void shouldReturnCorrectStatus() {
        assertEquals(StatusEnum.PENDING, pedidoEntity.getStatus());
    }

    @Test
    public void shouldReturnCorrectSteps() {
        assertEquals(StatusEnum.PENDING, pedidoEntity.getSteps());
    }

    @Test
    public void shouldReturnCorrectQrData() {
        assertEquals("qrData", pedidoEntity.getQrData());
    }

    @Test
    public void shouldReturnCorrectExternalReference() {
        assertEquals(1L, pedidoEntity.getExternalReference());
    }

    @Test
    public void shouldReturnCorrectPaymentId() {
        assertEquals(1L, pedidoEntity.getPaymentId());
    }

    @Test
    public void shouldReturnCorrectStatusPagamento() {
        assertEquals(StatusEnum.PENDING, pedidoEntity.getStatusPagamento());
    }

    @Test
    public void shouldReturnCorrectCriacao() {
        assertNotNull(pedidoEntity.getCriacao());
    }

    @Test
    public void shouldReturnCorrectAlteracao() {
        assertNotNull(pedidoEntity.getAlteracao());
    }

    @Test
    public void toStringShouldReturnCorrectFormat() {
        String expectedString = "PedidoData [id=1, cliente=1, itens=2 itens, preco=20.00, status=PENDING, criacao=" + pedidoEntity.getCriacao() + ", alteracao=" + pedidoEntity.getAlteracao() + "]";
        assertEquals(expectedString, pedidoEntity.toString());
    }
}