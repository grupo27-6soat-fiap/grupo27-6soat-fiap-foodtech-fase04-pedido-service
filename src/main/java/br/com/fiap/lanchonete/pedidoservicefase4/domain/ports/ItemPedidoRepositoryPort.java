package br.com.fiap.lanchonete.pedidoservicefase4.domain.ports;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;

public interface ItemPedidoRepositoryPort {
	Item addItemPedido(Long id, Item itens);

	void deleteItemPedido(Long idItem);
}
