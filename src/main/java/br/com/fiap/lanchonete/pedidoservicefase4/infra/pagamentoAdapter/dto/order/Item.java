package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Item {
    public String skuNumber;
    public String category;
    public String title;
    public String description;
    public BigDecimal unitPrice;
    public Integer quantity;
    public String unitMeasure;
    public BigDecimal totalAmount;
}
