package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.PaymentProvider;
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

public class PayPedidoUsecaseTest {

    @InjectMocks
    private PayPedidoUsecase payPedidoUsecase;

    @Mock
    private PedidoRepositoryPort pedidoRepositoryPort;

    @Mock
    private PaymentProvider paymentProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void payPedido_HappyPath() {
        Pedido pedido = new Pedido();
        pedido.setPaymentId(1L);
        pedido.setId(1L);

        OrdemPedido ordemPedido = new OrdemPedido(pedido.getId());

        when(paymentProvider.getOrderFromPayment(any(Pedido.class))).thenReturn(ordemPedido);
        when(pedidoRepositoryPort.pay(any(Pedido.class), any(OrdemPedido.class))).thenReturn(pedido);

        Pedido result = payPedidoUsecase.payPedido(pedido);

        assertEquals(pedido, result);
    }

    @Test
    public void payPedido_PaymentIdNotFound() {
        Pedido pedido = new Pedido();

        assertThrows(EntityNotFoundException.class, () -> {
            payPedidoUsecase.payPedido(pedido);
        });
    }

    @Test
    public void payPedido_PedidoIsNull() {
        assertThrows(EntityNotFoundException.class, () -> {
            payPedidoUsecase.payPedido(null);
        });
    }
}
