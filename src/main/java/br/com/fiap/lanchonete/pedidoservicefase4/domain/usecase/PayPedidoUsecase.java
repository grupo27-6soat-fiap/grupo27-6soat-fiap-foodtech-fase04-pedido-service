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
public class PayPedidoUsecase {

	private final PedidoRepositoryPort pedidoRepositoryPort;
	
	//private final PaymentGatewayPort paymentGatewayPort;
	
	//private final GatewayPaymentConfiguration gatewayPayment;

    private final PaymentProvider paymentProvider;

    public Pedido payPedido(Pedido pedido) {
        if (Objects.isNull(pedido) || Objects.isNull(pedido.getPaymentId()))
            throw new EntityNotFoundException("Payment nao encontrado para o id nulo :: " );

       // OrdemPedido ordemPedido = paymentGatewayPort.getPayment(pedido.getPaymentId(), gatewayPayment.getToken());

        OrdemPedido ordemPedido = paymentProvider.getOrderFromPayment(pedido);
        
        return pedidoRepositoryPort.pay(pedido, ordemPedido);
    }

}
