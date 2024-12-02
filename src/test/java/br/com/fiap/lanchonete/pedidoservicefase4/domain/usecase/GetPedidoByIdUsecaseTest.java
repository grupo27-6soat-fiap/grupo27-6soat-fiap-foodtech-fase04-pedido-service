package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class GetPedidoByIdUsecaseTest {

    @InjectMocks
    private GetPedidoByIdUsecase getPedidoByIdUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPedidoById_HappyPath() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        when(pedidoPort.get(anyLong())).thenReturn(pedido);

        // When
        Pedido result = getPedidoByIdUsecase.get(1L);

        // Then
        assertEquals(pedido, result);
    }

    @Test
    public void getPedidoById_PedidoNotFound() {
        // Given
        when(pedidoPort.get(anyLong())).thenReturn(null);

        // When
        Pedido result = getPedidoByIdUsecase.get(1L);

        // Then
        assertNull(result);
    }
}