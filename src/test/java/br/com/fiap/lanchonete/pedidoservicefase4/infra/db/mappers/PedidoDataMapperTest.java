package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.mappers;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.PedidoEntity;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.mappers.PedidoDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoDataMapperTest {

    private PedidoDataMapper pedidoDataMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
        pedidoDataMapper = new PedidoDataMapper(modelMapper);
    }

    @Test
    public void shouldMapPedidoEntityToPedido() {
        PedidoEntity pedidoEntity = new PedidoEntity();
        // set properties for pedidoEntity
        pedidoEntity.setId(1L);

        Pedido pedido = pedidoDataMapper.toDomain(pedidoEntity);

        assertEquals(pedidoEntity.getId(), pedido.getId());
        // assert other properties
    }

    @Test
    public void shouldMapPedidoToPedidoEntity() {
        Pedido pedido = new Pedido();
        // set properties for pedido
        pedido.setId(1L);

        PedidoEntity pedidoEntity = pedidoDataMapper.toData(pedido);

        assertEquals(pedido.getId(), pedidoEntity.getId());
        // assert other properties
    }
}