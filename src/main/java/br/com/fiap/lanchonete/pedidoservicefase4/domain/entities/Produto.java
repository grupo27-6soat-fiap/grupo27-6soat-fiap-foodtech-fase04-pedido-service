package br.com.fiap.lanchonete.pedidoservicefase4.domain.entities;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.CategoriaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {
	private Long id;
	private String nome;
	private CategoriaEnum categoria;
	private BigDecimal preco;
	private String descricao;
	private List<String> imagens;
}
