package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.repository;

import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemEntity, Long> {
}
