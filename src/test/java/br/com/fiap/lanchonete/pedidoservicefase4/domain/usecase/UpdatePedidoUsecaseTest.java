package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UpdatePedidoUsecaseTest {

    @InjectMocks
    private UpdatePedidoUsecase updatePedidoUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void update_HappyPath() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CRIADO);

        Pedido updatedPedido = new Pedido();
        updatedPedido.setId(1L);
        updatedPedido.setStatus(StatusEnum.PAGO);

        when(pedidoPort.get(anyLong())).thenReturn(pedido);
        when(pedidoPort.save(any(Pedido.class))).thenReturn(updatedPedido);

        Pedido result = updatePedidoUsecase.update(1L, updatedPedido);

        assertEquals(updatedPedido, result);
    }

    @Test
    public void update_PedidoNotFound() {
        Pedido updatedPedido = new Pedido();
        updatedPedido.setId(1L);
        updatedPedido.setStatus(StatusEnum.PAGO);

        when(pedidoPort.get(anyLong())).thenReturn(null);

        Pedido result = updatePedidoUsecase.update(1L, updatedPedido);

        assertNull(result);
    }

    @Test
    public void update_NullPedido() {
        Pedido pedido = new Pedido();
        when(pedidoPort.get(anyLong())).thenReturn(null);
        Pedido result = updatePedidoUsecase.update(1L, pedido);

        assertNull(result);
    }
}