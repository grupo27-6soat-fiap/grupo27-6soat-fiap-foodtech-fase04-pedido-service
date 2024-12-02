package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.mappers;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.merchantOrder.MerchantOrderResponse;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.payment.PaymentsResponse;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.mappers.OrdemPedidoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrdemPedidoMapperTest {

    private OrdemPedidoMapper ordemPedidoMapper;

    @BeforeEach
    public void setup() {
        ordemPedidoMapper = new OrdemPedidoMapper();
    }

    @Test
    public void toDomainFromMerchantOrderResponse() {
        MerchantOrderResponse merchantOrderResponse = new MerchantOrderResponse();
        merchantOrderResponse.setExternalReference("123");

        OrdemPedido result = ordemPedidoMapper.toDomain(merchantOrderResponse);

        assertNotNull(result);
        //assertEquals(Long.valueOf(123), result.getId());
    }

    @Test
    public void toDomainFromMerchantOrderResponseWithNullExternalReference() {
        MerchantOrderResponse merchantOrderResponse = new MerchantOrderResponse();

        OrdemPedido result = ordemPedidoMapper.toDomain(merchantOrderResponse);

        assertNotNull(result);
      //  assertEquals(null, result.getId());
    }

    @Test
    public void toDomainFromPaymentsResponse() {
        PaymentsResponse paymentsResponse = new PaymentsResponse();
        paymentsResponse.setExternalReference("123");
        paymentsResponse.setStatus("PENDING");

        OrdemPedido result = ordemPedidoMapper.toDomain(paymentsResponse);

        assertNotNull(result);
       // assertEquals(Long.valueOf(123), result.getId());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    public void toDomainFromPaymentsResponseWithNullExternalReference() {
        PaymentsResponse paymentsResponse = new PaymentsResponse();
        paymentsResponse.setStatus("PENDING");

        OrdemPedido result = ordemPedidoMapper.toDomain(paymentsResponse);

        assertNotNull(result);
      //  assertEquals(null, result.getId());
        assertEquals("PENDING", result.getStatus());
    }
}