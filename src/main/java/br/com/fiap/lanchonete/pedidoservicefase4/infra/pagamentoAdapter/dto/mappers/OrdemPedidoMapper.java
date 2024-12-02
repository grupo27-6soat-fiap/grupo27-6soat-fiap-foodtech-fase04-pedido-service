package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.mappers;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.merchantOrder.MerchantOrderResponse;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.payment.PaymentsResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrdemPedidoMapper {

	public OrdemPedido toDomain(MerchantOrderResponse merchantOrderResponse) {
		Long externalReference = Optional.ofNullable(merchantOrderResponse).map(MerchantOrderResponse::getExternalReference)
				.map(Long::parseLong).orElse(null);
		return new OrdemPedido(externalReference);
	}

	public OrdemPedido toDomain(PaymentsResponse paymentsResponse) {
		Long externalReference = Optional.ofNullable(paymentsResponse).map(PaymentsResponse::getExternalReference)
				.map(Long::parseLong).orElse(null);
		return new OrdemPedido(externalReference, paymentsResponse.getStatus());
	}

}
