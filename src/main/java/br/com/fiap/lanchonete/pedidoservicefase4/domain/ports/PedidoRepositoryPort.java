package br.com.fiap.lanchonete.pedidoservicefase4.domain.ports;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;

import java.util.List;


public interface PedidoRepositoryPort {
	Pedido checkout(Pedido pedido);

	Pedido confirm(Pedido pedido, OrdemPedido ordemPedido);

	List<Pedido> findAll();

	List<Pedido> findByStatus(List<String> statuss);

	Pedido get(Long id);

	Pedido pay(Pedido pedido, OrdemPedido ordemPedido);

	Pedido save(Pedido pedido);
}
