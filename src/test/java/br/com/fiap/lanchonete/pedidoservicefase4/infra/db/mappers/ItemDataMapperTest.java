package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.mappers;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities.ItemEntity;
import br.com.fiap.lanchonete.pedidoservicefase4.infra.db.mappers.ItemDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDataMapperTest {

    private ItemDataMapper itemDataMapper;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
        itemDataMapper = new ItemDataMapper(modelMapper);
    }

    @Test
    public void shouldMapItemEntityToItem() {
        ItemEntity itemEntity = new ItemEntity();
        // set properties for itemEntity
        itemEntity.setId(1L);

        Item item = itemDataMapper.toDomain(itemEntity);

        assertEquals(itemEntity.getId(), item.getId());
        // assert other properties
    }

    @Test
    public void shouldMapItemToItemEntity() {
        Item item = new Item();
        // set properties for item
        item.setId(1L);

        ItemEntity itemEntity = itemDataMapper.toData(item);

        assertEquals(item.getId(), itemEntity.getId());
        // assert other properties
    }

    @Test
    public void shouldMapListOfItemEntitiesToItems() {
        ItemEntity itemEntity1 = new ItemEntity();
        ItemEntity itemEntity2 = new ItemEntity();
        // set properties for itemEntity1 and itemEntity2
        itemEntity1.setId(1L);
        itemEntity2.setId(2L);

        List<ItemEntity> itemEntities = Arrays.asList(itemEntity1, itemEntity2);

        List<Item> items = itemDataMapper.toDomain(itemEntities);

        assertEquals(itemEntities.size(), items.size());
        // assert properties for each item in the list
    }

    @Test
    public void shouldMapListOfItemsToItemEntities() {
        Item item1 = new Item();
        Item item2 = new Item();
        // set properties for item1 and item2
        item1.setId(1L);
        item2.setId(2L);

        List<Item> items = Arrays.asList(item1, item2);

        List<ItemEntity> itemEntities = itemDataMapper.toData(items);

        assertEquals(items.size(), itemEntities.size());
        // assert properties for each itemEntity in the list
    }
}
