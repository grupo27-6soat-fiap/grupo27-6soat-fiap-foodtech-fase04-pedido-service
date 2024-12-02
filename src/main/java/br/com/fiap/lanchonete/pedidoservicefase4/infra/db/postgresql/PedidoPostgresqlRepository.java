package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.postgresql;



import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.OrdemPedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.ports.PedidoRepositoryPort;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.config.ModelMapperConfiguration;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.ItemEntity;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.PedidoEntity;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.mappers.PedidoDataMapper;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.repository.ItemPedidoRepository;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.repository.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PedidoPostgresqlRepository implements PedidoRepositoryPort {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	public ModelMapper modelMapper;

	@Autowired
	public PedidoDataMapper pedidoDataMapper;

	@Override
	public Pedido get(Long id) {
		// PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElse(null);
		// if (pedidoEntity != null) {
		// 	System.out.println(pedidoEntity.toString());
		// } else {
		// 	System.out.println("PedidoEntity não encontrado para o ID: " + id);
		// }
		// for (ItemEntity item : pedidoEntity.getItens()) {
		// 	System.out.println("- Item ID: " + item.getId() + ", Preço: " + item.getPreco() +  ", Produto: "+ item.getProduto());
		// }
		// return pedidoRepository.findById(id).map(pedidoData -> modelMapper.map(pedidoData, Pedido.class)).orElse(null);


		PedidoEntity pedidoEntity = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

		pedidoEntity.getItens().forEach(item -> {
		System.out.println("Item ID: " + item.getId());
		System.out.println("Produto: " + item.getProduto());
		});

		Pedido pedido = modelMapper.map(pedidoEntity, Pedido.class);
		return pedido;
	}

	
	@Override
	public List<Pedido> findAll() {
		return pedidoRepository.findAll().stream().map(pedidoData -> modelMapper.map(pedidoData, Pedido.class))
				.toList();
	}

	@Override
	public List<Pedido> findByStatus(List<String> statuss) {
		List<StatusEnum> enums = statuss.stream().map(ss -> StatusEnum.valueOf(ss)).collect(Collectors.toList());
		List<PedidoEntity> list = pedidoRepository.findByStatusIn(enums);
		if (!list.isEmpty()) {
			Collections.sort(list,
					Comparator.comparing(PedidoEntity::getSteps).reversed().thenComparing(PedidoEntity::getCriacao));
			return list.stream().map(pedidoSchema -> modelMapper.map(pedidoSchema, Pedido.class)).toList();
		}
		return new ArrayList<>();
	}

	@Override
	public Pedido save(Pedido pedido) {
		PedidoEntity pedidoSchema = pedidoDataMapper.toData(pedido);
		pedidoSchema.setSteps(pedidoSchema.getStatus());
		return modelMapper.map(pedidoRepository.save(pedidoSchema), Pedido.class);
	}

	@Override
	public Pedido checkout(Pedido pedido) {
		PedidoEntity pedidoSchema = modelMapper.map(pedido, PedidoEntity.class);
		pedidoSchema.setSteps(pedidoSchema.getStatus());
		pedidoRepository.save(pedidoSchema);
		return pedido;
	}

	@Override
	public Pedido confirm(Pedido pedido, OrdemPedido ordemPedido) {
		if (Objects.nonNull(ordemPedido)) {
			Pedido ped = pedidoRepository.findById(ordemPedido.getIdExterno())
					.map(pedidoData -> modelMapper.map(pedidoData, Pedido.class)).orElse(new Pedido());
			ped.setExternalReference(pedido.getOrderId());
			ped.setStatus(StatusEnum.CONFIRMADO);
			PedidoEntity pedidoSchema = modelMapper.map(ped, PedidoEntity.class);
			pedidoSchema.setSteps(pedidoSchema.getStatus());
			return modelMapper.map(pedidoRepository.save(pedidoSchema), Pedido.class);
		}
		return null;
	}

	@Override
	public Pedido pay(Pedido pedido, OrdemPedido ordemPedido) {
		if (Objects.nonNull(ordemPedido)) {
			Pedido ped = pedidoRepository.findById(ordemPedido.getIdExterno())
					.map(pedidoData -> modelMapper.map(pedidoData, Pedido.class)).orElse(new Pedido());

			ped.setPaymentId(pedido.getPaymentId());
			if ("approved".equals(ordemPedido.getStatus())) {
				ped.setStatus(StatusEnum.RECEBIDO);
				ped.setStatusPagamento(StatusEnum.PAGO);
			} else {
				ped.setStatusPagamento(StatusEnum.REJEITADO);
			}
			PedidoEntity pedidoSchema = modelMapper.map(ped, PedidoEntity.class);
			pedidoSchema.setSteps(pedidoSchema.getStatus());
			return modelMapper.map(pedidoRepository.save(pedidoSchema), Pedido.class);
		}
		return null;
	}

}