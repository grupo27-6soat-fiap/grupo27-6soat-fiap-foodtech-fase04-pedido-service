package br.com.fiap.lanchonete.pedidoservicefase4.domain.entities;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Pedido {
    private Long id;
    private Cliente cliente;
    private List<Item> itens;
    private BigDecimal preco;
    private StatusEnum status;
    private LocalDateTime criacao;
    private String qrData;
    private Long externalReference;
    private Long paymentId;
    private StatusEnum statusPagamento;

    @JsonIgnore
    private Long collector;
    @JsonIgnore
    private String pos;
    @JsonIgnore
    private Long orderId;

    public Pedido(){
        this.itens = new ArrayList<>();
        this.preco = BigDecimal.ZERO;
    }

    @Override
    public String toString(){
        return "Pedido: " + id + " Cliente: " + cliente.getNome() + " Status: " + status;
    }
}
