package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;



import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.PaymentProvider;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ConfirmPedidoUsecaseTest {

    @InjectMocks
    private ConfirmPedidoUsecase confirmPedidoUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @Mock
    private PaymentProvider paymentProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void confirmPedido_HappyPath() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setOrderId(1L);

        OrdemPedido ordemPedido = new OrdemPedido(pedido.getId());


        when(paymentProvider.getMerchantOrder(any(Pedido.class))).thenReturn(ordemPedido);
        when(pedidoPort.confirm(any(Pedido.class), any(OrdemPedido.class))).thenReturn(pedido);

        // When
        Pedido result = confirmPedidoUsecase.confirmPedido(pedido);

        // Then
        assertEquals(pedido, result);
    }

    @Test
    public void confirmPedido_PedidoIsNull() {
        // Given
        Pedido pedido = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> confirmPedidoUsecase.confirmPedido(pedido));
    }

    @Test
    public void confirmPedido_OrderIdIsNull() {
        // Given
        Pedido pedido = new Pedido();

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> confirmPedidoUsecase.confirmPedido(pedido));
    }
}

