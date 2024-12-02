package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.mappers;



import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemDataMapper {

	private final ModelMapper modelMapper;

	public ItemDataMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public Item toDomain(ItemEntity data) {
		return modelMapper.map(data, Item.class);
	}

	public ItemEntity toData(Item item) {
		return modelMapper.map(item, ItemEntity.class);
	}

	public List<Item> toDomain(List<ItemEntity> datas) {
		return datas.stream().map(this::toDomain).toList();
	}

	public List<ItemEntity> toData(List<Item> items) {
		return items.stream().map(this::toData).toList();
	}
	
}
