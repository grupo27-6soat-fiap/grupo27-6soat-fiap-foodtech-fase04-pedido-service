package br.com.fiap.lanchonete.pedidoservicefase4.app.dto;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto {
	private Long id;
	private ClienteDto cliente;
	@NotNull
	private List<ItemDto> itens;
	private BigDecimal preco;
	private StatusEnum status;
	private String qrData;
	private Long externalReference;
	private Long paymentId;
	private StatusEnum statusPagamento;
	private LocalDateTime criacao;
}
