package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.ProdutoProvider;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CreatePedidoUsecase {

	//private final ClienteRepositoryPort clientePort;

	//private final ProdutoRepositoryPort produtoPort;

	private final ProdutoProvider produtoPort;

	private final PedidoRepositoryPort pedidoPort;

	public Pedido create(Pedido pedido) {
		pedido.setCriacao(LocalDateTime.now());
		pedido.setStatus(StatusEnum.CRIADO);
		pedido.setStatusPagamento(StatusEnum.PENDING);
		pedido.setPreco(BigDecimal.ZERO);
		//Cliente cliente = Optional.ofNullable(pedido).map(Pedido::getCliente).map(Cliente::getCpf)
			//	.map(cpf -> clientePort.findByCpf(cpf)).orElse(null);
		//if (cliente != null) {
		//	pedido.getCliente().setId(cliente.getId());
	//		pedido.setCliente(cliente);
	//	}
		Produto produto = null;
		for (Item item : pedido.getItens()) {
			produto = produtoPort.get(item.getProduto().getId());
			if (Objects.isNull(produto))
				throw new EntityNotFoundException("Produto nao encontrado para o id :: " + item.getProduto().getId());
			item.setPreco(produto.getPreco());
			item.setPedido(pedido);
			item.setProduto(produto);
			pedido.setPreco(pedido.getPreco().add(item.getPreco()));
		}
		return pedidoPort.save(pedido);
	}

}
