package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FindPedidoByStatusUsecaseTest {

    @InjectMocks
    private FindPedidoByStatusUsecase findPedidoByStatusUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByStatus_HappyPath() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        List<Pedido> expected = Arrays.asList(pedido);

        when(pedidoPort.findByStatus(any(List.class))).thenReturn(expected);

        // When
        List<Pedido> result = findPedidoByStatusUsecase.findByStatus(Arrays.asList("CREATED"));

        // Then
        assertEquals(expected, result);
    }

    @Test
    public void findByStatus_NoStatusProvided() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        List<Pedido> expected = Arrays.asList(pedido);

        when(pedidoPort.findAll()).thenReturn(expected);

        // When
        List<Pedido> result = findPedidoByStatusUsecase.findByStatus(null);

        // Then
        assertEquals(expected, result);
    }

    @Test
    public void findByStatus_EmptyStatusList() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        List<Pedido> expected = new ArrayList<>();

        when(pedidoPort.findAll()).thenReturn(expected);

        // When
        List<Pedido> result = findPedidoByStatusUsecase.findByStatus(Collections.emptyList());

        // Then
        assertEquals(expected, result);
    }
}
