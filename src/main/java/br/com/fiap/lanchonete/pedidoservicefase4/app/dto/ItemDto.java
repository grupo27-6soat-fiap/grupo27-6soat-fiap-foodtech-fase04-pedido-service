package br.com.fiap.lanchonete.pedidoservicefase4.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {
    private Long id;
    private ProdutoDto produto;
    private BigDecimal preco;
}
