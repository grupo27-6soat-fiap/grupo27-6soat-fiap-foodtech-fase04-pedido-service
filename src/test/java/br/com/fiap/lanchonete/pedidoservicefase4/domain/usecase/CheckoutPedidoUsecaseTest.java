package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.PaymentProvider;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CheckoutPedidoUsecaseTest {

    @InjectMocks
    private CheckoutPedidoUsecase checkoutPedidoUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @Mock
    private PaymentProvider paymentProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkoutPedido_HappyPath() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CRIADO);

        when(pedidoPort.get(any(Long.class))).thenReturn(pedido);
        when(paymentProvider.createOrderAndSetQrData(any(Pedido.class), any(ArrayList.class))).thenReturn(pedido);

        // When
        Pedido result = checkoutPedidoUsecase.checkoutPedido(pedido);

        // Then
       // assertEquals(StatusEnum.PENDING, result.getStatus());
        assertNull(result.getCollector());
    }

    @Test
    public void checkoutPedido_PedidoNotFound() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CRIADO);

        when(pedidoPort.get(any(Long.class))).thenReturn(null);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> checkoutPedidoUsecase.checkoutPedido(pedido));
    }

    @Test
    public void checkoutPedido_PedidoNotInCriadoStatus() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.PENDING);

        when(pedidoPort.get(any(Long.class))).thenReturn(pedido);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> checkoutPedidoUsecase.checkoutPedido(pedido));
    }
}
