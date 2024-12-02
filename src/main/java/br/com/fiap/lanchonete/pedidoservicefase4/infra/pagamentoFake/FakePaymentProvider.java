package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoFake;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.PaymentProvider;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.order.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FakePaymentProvider implements PaymentProvider {
    @Override
    public Pedido createOrderAndSetQrData(Pedido pedido, ArrayList<Item> itens) {
        // Here you can set the properties of the returned Pedido object as per your requirements
        Pedido pedidoCriado = new Pedido();
        pedidoCriado.setId(pedido.getId());
        pedidoCriado.setStatus(pedido.getStatus());
        pedidoCriado.setQrData("fakeQrData");
        pedidoCriado.setPaymentId(10L);
        return pedidoCriado;
    }

    @Override
    public OrdemPedido getOrderFromPayment(Pedido pedido) {
        // Here you can set the properties of the returned OrdemPedido object as per your requirements
        OrdemPedido ordemPedido = new OrdemPedido(pedido.getId(), "PENDING");
        return ordemPedido;
    }

    @Override
    public OrdemPedido getMerchantOrder(Pedido pedido) {
        // Here you can set the properties of the returned OrdemPedido object as per your requirements
        OrdemPedido ordemPedido = new OrdemPedido(pedido.getId(), "PENDING");
        return ordemPedido;
    }

    @Override
    public String getSku() {
        return "fakeSku";
    }
}
