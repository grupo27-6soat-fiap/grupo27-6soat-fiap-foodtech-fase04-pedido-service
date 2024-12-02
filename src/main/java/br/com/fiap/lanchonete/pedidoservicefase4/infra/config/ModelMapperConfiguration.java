package br.com.fiap.lanchonete.pedidoservicefase4.infra.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.ItemEntity;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.PedidoEntity;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    ModelMapper modelMapper() {
    
        ModelMapper modelMapper = new ModelMapper();
         // Configuração para mapear o id do produto
        modelMapper.typeMap(ItemEntity.class, Item.class).addMappings(mapper -> {
            mapper.map(src -> src.getProduto(), Item::setProduto);
        });
        // Configuração para mapear o PedidoEntity e suas listas
        modelMapper.typeMap(PedidoEntity.class, Pedido.class).addMappings(mapper -> {
            mapper.map(PedidoEntity::getItens, Pedido::setItens);
        });

        modelMapper.typeMap(Item.class, ItemEntity.class).addMappings(mapper -> {
			mapper.map(src -> src.getProduto().getId(), ItemEntity::setProduto);
		});

        // // Mapeamento de ItemEntity para Item, extraindo o ID do produto
        // modelMapper.typeMap(ItemEntity.class, Item.class).addMappings(mapper -> {
        //     mapper.map(src -> src.getProduto(), Item::setProduto);
        // });

        modelMapper.typeMap(ItemEntity.class, Item.class).addMappings(mapper -> {
            mapper.map(ItemEntity::getProduto, Item::setProduto);
        });

        // Mapeamento de PedidoEntity para Pedido
        modelMapper.typeMap(PedidoEntity.class, Pedido.class).addMappings(mapper -> {
            mapper.map(PedidoEntity::getItens, Pedido::setItens);
        });

        return modelMapper;

        }
}
