package br.com.fiap.lanchonete.pedidoservicefase4.infra.pagamentoAdapter.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {
    public CashOut cashOut;
    public String description;
    public String externalReference;
    public ArrayList<Item> items;
    public String notificationUrl;
    public Sponsor sponsor;
    public String title;
    public BigDecimal totalAmount;
}
