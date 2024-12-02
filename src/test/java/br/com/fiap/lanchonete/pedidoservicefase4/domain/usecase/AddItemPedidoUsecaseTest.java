package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.ItemPedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.ProdutoProvider;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class AddItemPedidoUsecaseTest {



    private AddItemPedidoUsecase addItemPedidoUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @Mock
    private ProdutoProvider produtoPort;

    @Mock
    private ItemPedidoRepositoryPort itemPedidoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        addItemPedidoUsecase = new AddItemPedidoUsecase(pedidoPort, produtoPort, itemPedidoPort);
    }

    @Test
    public void addItemPedido_HappyPath() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CRIADO);

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setPreco(new BigDecimal("10.00"));

        Item item = new Item();
        item.setProduto(produto);

        when(pedidoPort.get(anyLong())).thenReturn(pedido);
        when(produtoPort.get(anyLong())).thenReturn(produto);
        when(itemPedidoPort.addItemPedido(any(Long.class), any(Item.class))).thenReturn(item);

        // When
        Pedido result = addItemPedidoUsecase.addItemPedido(1L, Arrays.asList(item));

        // Then
        assertEquals(0, result.getItens().size());
       // assertEquals(new BigDecimal("10.00"), result.getPreco());
    }

    @Test
    public void addItemPedido_PedidoNotFound() {
        // Given
        when(pedidoPort.get(1L)).thenReturn(null);

        // When
        assertThrows(EntityNotFoundException.class, () -> {
            addItemPedidoUsecase.addItemPedido(1L, new ArrayList<>());
        });
    }

    @Test
    public void addItemPedido_ProdutoNotFound() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CRIADO);

        Produto produto = new Produto();
        produto.setId(1L);

        Item item = new Item();
        item.setProduto(produto);

        when(pedidoPort.get(1L)).thenReturn(pedido);
        when(produtoPort.get(1L)).thenReturn(null);

        // When
        assertThrows(EntityNotFoundException.class, () -> {
            addItemPedidoUsecase.addItemPedido(1L, Arrays.asList(item));
        });
    }
}
