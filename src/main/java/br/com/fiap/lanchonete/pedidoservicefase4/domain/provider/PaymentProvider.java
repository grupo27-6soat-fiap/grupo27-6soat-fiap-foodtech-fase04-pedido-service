package br.com.fiap.lanchonete.pedidoservicefase4.domain.provider;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.order.Item;

import java.util.ArrayList;

public interface PaymentProvider {

    Pedido createOrderAndSetQrData(Pedido pedido, ArrayList<Item> itens);

    OrdemPedido getOrderFromPayment(Pedido pedido);

    OrdemPedido getMerchantOrder(Pedido pedido);

    String getSku();

}
