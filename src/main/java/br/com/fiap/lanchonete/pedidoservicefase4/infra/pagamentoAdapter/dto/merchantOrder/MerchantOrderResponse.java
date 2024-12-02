package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.merchantOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MerchantOrderResponse {
    public Long id;
    public String externalReference;
    public String status;
    public BigDecimal paidAmount;
}
