package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.repository;


import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.PedidoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoEntity, Long> {
	List<PedidoEntity> findByStatusIn(List<StatusEnum> status);
    List<PedidoEntity> findAll();
}
