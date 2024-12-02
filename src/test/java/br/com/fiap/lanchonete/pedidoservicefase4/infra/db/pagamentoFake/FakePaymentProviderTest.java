package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.pagamentoFake;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoFake.FakePaymentProvider;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.order.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FakePaymentProviderTest {

    @InjectMocks
    private FakePaymentProvider fakePaymentProvider;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createOrderAndSetQrData() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CONFIRMADO);

        ArrayList<Item> itens = new ArrayList<>();

        Pedido result = fakePaymentProvider.createOrderAndSetQrData(pedido, itens);

        assertNotNull(result);
        assertEquals(pedido.getId(), result.getId());
        assertEquals(pedido.getStatus(), result.getStatus());
        assertEquals("fakeQrData", result.getQrData());
    }

    @Test
    public void getOrderFromPayment() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CONFIRMADO);

        OrdemPedido result = fakePaymentProvider.getOrderFromPayment(pedido);

        assertNotNull(result);
        //assertEquals(pedido.getId(), result.getId());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    public void getMerchantOrder() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus(StatusEnum.CONFIRMADO);

        OrdemPedido result = fakePaymentProvider.getMerchantOrder(pedido);

        assertNotNull(result);
        //assertEquals(pedido.getId(), result.getId());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    public void getSku() {
        String result = fakePaymentProvider.getSku();

        assertNotNull(result);
        assertEquals("fakeSku", result);
    }
}

