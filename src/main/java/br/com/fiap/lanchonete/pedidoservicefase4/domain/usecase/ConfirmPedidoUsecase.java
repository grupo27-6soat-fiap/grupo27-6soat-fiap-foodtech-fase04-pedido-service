package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.PaymentProvider;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ConfirmPedidoUsecase {

	private final PedidoRepositoryPort pedidoPort;
	
	//private final PaymentGatewayPort paymentGatewayPort;

	//private final GatewayPaymentConfiguration gatewayPayment;

    private final PaymentProvider paymentProvider;

    public Pedido confirmPedido(Pedido pedido) {
        if (Objects.isNull(pedido) || Objects.isNull(pedido.getOrderId()))
            throw new EntityNotFoundException("Order nao encontrado para o id :: " + pedido.getOrderId());
        
       /* OrdemPedido ordemPedido = paymentGatewayPort.getMerchantOrder(pedido.getOrderId(),
				gatewayPayment.getToken());

        */
        OrdemPedido ordemPedido = paymentProvider.getMerchantOrder(pedido);
        
        return pedidoPort.confirm(pedido, ordemPedido);
    }

}
