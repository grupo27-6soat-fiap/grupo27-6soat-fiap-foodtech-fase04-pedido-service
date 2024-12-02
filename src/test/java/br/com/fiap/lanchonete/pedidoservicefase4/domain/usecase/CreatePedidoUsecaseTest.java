package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;



import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.ProdutoProvider;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreatePedidoUsecaseTest {

    @InjectMocks
    private CreatePedidoUsecase createPedidoUsecase;

    @Mock
    private PedidoRepositoryPort pedidoPort;

    @Mock
    private ProdutoProvider produtoPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createPedido_HappyPath() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        Produto produto = new Produto();
        produto.setId(1L);
        produto.setPreco(new BigDecimal("10.00"));

        Item item = new Item();
        item.setProduto(produto);

        when(produtoPort.get(any(Long.class))).thenReturn(produto);
        when(pedidoPort.save(any(Pedido.class))).thenReturn(pedido);

        // When
        Pedido result = createPedidoUsecase.create(pedido);

        // Then
        assertEquals(pedido, result);
    }

    @Test
    public void createPedido_ProdutoNotFound() {
        // Given
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        Item item = new Item();
        item.setProduto(new Produto());

        pedido.setItens(Arrays.asList(item));

        when(produtoPort.get(any(Long.class))).thenReturn(null);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> createPedidoUsecase.create(pedido));
    }
}
