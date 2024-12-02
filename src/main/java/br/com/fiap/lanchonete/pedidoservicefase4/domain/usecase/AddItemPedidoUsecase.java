package br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.ItemPedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.ProdutoProvider;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AddItemPedidoUsecase {

	private final PedidoRepositoryPort pedidoPort;
	

	private final ProdutoProvider produtoPort;

	private final ItemPedidoRepositoryPort itemPedidoPort;

	public Pedido addItemPedido(Long id, List<Item> itens) {
		Pedido pedido = pedidoPort.get(id);
		if (Objects.isNull(pedido))
			throw new EntityNotFoundException("Pedido nao encontrado para o id :: " + id);

		if (!pedido.getStatus().equals(StatusEnum.CRIADO))
			throw new EntityNotFoundException("Pedido já encaminhado nao pode ser mais alterado :: " + id);

		Produto produto = null;
		for (Item item : itens) {
			produto = produtoPort.get(item.getProduto().getId());

			if (Objects.isNull(produto))
				throw new EntityNotFoundException("Produto nao encontrado para o id :: " + item.getProduto().getId());

			item.setPreco(produto.getPreco());

			item.setPedido(pedido);
			item.setProduto(produto);

			item = itemPedidoPort.addItemPedido(id, item);
			pedido.getItens().add(item);
			pedido.setPreco(pedido.getPreco().add(produto.getPreco()));
		}

		Pedido savedPedido = pedidoPort.save(pedido);
		return savedPedido != null ? savedPedido : new Pedido();
	}

}
