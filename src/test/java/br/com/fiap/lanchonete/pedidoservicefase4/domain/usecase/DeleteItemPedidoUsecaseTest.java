package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.ItemPedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DeleteItemPedidoUsecaseTest {

    @InjectMocks
    private DeleteItemPedidoUsecase deleteItemPedidoUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @Mock
    private ItemPedidoRepositoryPort itemPedidoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteItemPedido_HappyPath() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CRIADO);
        pedido.setPreco(new BigDecimal("10.00"));

        Item item = new Item();
        item.setId(1L);
        item.setPreco(new BigDecimal("5.00"));

        List<Item> itens = new ArrayList<>();
        itens.add(item);
        itens.add(item);
        pedido.setItens(itens);

        when(pedidoPort.get(anyLong())).thenReturn(pedido);

        // When
        deleteItemPedidoUsecase.deleteItemPedido(1L, 1L);

        // Then
        verify(itemPedidoPort, times(1)).deleteItemPedido(anyLong());
        verify(pedidoPort, times(1)).save(any(Pedido.class));
    }

    @Test
    public void deleteItemPedido_PedidoNotFound() {
        // Given
        when(pedidoPort.get(anyLong())).thenReturn(null);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> deleteItemPedidoUsecase.deleteItemPedido(1L, 1L));
    }

    @Test
    public void deleteItemPedido_ItemNotFound() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CRIADO);

        when(pedidoPort.get(anyLong())).thenReturn(pedido);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> deleteItemPedidoUsecase.deleteItemPedido(1L, 1L));
    }

    @Test
    public void deleteItemPedido_PedidoNotInCriadoStatus() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.PENDING);

        when(pedidoPort.get(anyLong())).thenReturn(pedido);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> deleteItemPedidoUsecase.deleteItemPedido(1L, 1L));
    }
}
