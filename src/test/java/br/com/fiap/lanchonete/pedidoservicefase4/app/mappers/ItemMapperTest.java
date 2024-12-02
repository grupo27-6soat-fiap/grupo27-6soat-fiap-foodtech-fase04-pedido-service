package br.com.fiap.lanchonete.pedidoservicefase4.app.mappers;

import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.ItemDto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemMapperTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ItemMapper itemMapper;

    @Test
    public void shouldMapToDomain() {
        ItemDto itemDto = new ItemDto();
        Item item = new Item();

        when(modelMapper.map(itemDto, Item.class)).thenReturn(item);

        Item result = itemMapper.toDomain(itemDto);

        assertEquals(item, result);
    }

    @Test
    public void shouldMapToDTO() {
        Item item = new Item();
        ItemDto itemDto = new ItemDto();

        when(modelMapper.map(item, ItemDto.class)).thenReturn(itemDto);

        ItemDto result = itemMapper.toDTO(item);

        assertEquals(itemDto, result);
    }

    @Test
    public void shouldMapListToDomain() {
        List<ItemDto> itemDtos = List.of(new ItemDto(), new ItemDto());
        List<Item> items = List.of(new Item(), new Item());

        when(modelMapper.map(itemDtos.get(0), Item.class)).thenReturn(items.get(0));
        when(modelMapper.map(itemDtos.get(1), Item.class)).thenReturn(items.get(1));

        List<Item> result = itemMapper.toDomain(itemDtos);

        assertEquals(items, result);
    }

    @Test
    public void shouldMapListToDTO() {
        List<Item> items = List.of(new Item(), new Item());
        List<ItemDto> itemDtos = List.of(new ItemDto(), new ItemDto());

        when(modelMapper.map(items.get(0), ItemDto.class)).thenReturn(itemDtos.get(0));
        when(modelMapper.map(items.get(1), ItemDto.class)).thenReturn(itemDtos.get(1));

        List<ItemDto> result = itemMapper.toDTO(items);

        assertEquals(itemDtos, result);
    }
}
