package br.com.fiap.lanchonete.pedidoservicefase4.app.dto;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.CategoriaEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDto {
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private CategoriaEnum categoria;
	@NotNull
	private BigDecimal preco;
	private String descricao;
	private List<String> imagens;
}
