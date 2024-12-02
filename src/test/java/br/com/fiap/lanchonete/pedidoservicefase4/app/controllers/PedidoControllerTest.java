package br.com.fiap.lanchonete.pedidoservicefase4.app.controllers;


import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.ItemDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.PedidoDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.PedidoReduceDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.mappers.ItemMapper;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class PedidoControllerTest {


    private PedidoController pedidoController;

    @Mock

    private  GetPedidoByIdUsecase getPedidoByIdUsecase;

    @Mock
    private  FindPedidoByStatusUsecase findPedidoByStatusUsecase;

    @Mock

    private  CreatePedidoUsecase createPedidoUsecase;

    @Mock
    private  UpdatePedidoUsecase updatePedidoUsecase;

    @Mock
    private  AddItemPedidoUsecase addItemPedidoUsecase;

    @Mock

    private  DeleteItemPedidoUsecase deleteItemPedidoUsecase;

    @Mock

    private  CheckoutPedidoUsecase checkoutPedidoUsecase;

    @Mock

    private  ConfirmPedidoUsecase confirmPedidoUsecase;

    @Mock

    private  PayPedidoUsecase payPedidoUsecase;


    private ModelMapper modelMapper;

    @Mock

    private ItemMapper itemMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();

        pedidoController = new PedidoController(getPedidoByIdUsecase, findPedidoByStatusUsecase, createPedidoUsecase, updatePedidoUsecase, addItemPedidoUsecase, deleteItemPedidoUsecase, checkoutPedidoUsecase, confirmPedidoUsecase, payPedidoUsecase, modelMapper, itemMapper);
    }

    @Test
    public void get_HappyPath() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(1L);

        when(getPedidoByIdUsecase.get(anyLong())).thenReturn(pedido);
       // when(modelMapper.map(any(Pedido.class), any())).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> result = pedidoController.get(1L);

        assertEquals(pedidoDto.getId(), result.getBody().getId());
    }

    @Test
    public void put_HappyPath() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);


        List<Item> itens = List.of(new Item());
        pedido.setItens(itens);

        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(1L);

        List<ItemDto> itensDto = List.of(new ItemDto());
        pedidoDto.setItens(itensDto);

        when(updatePedidoUsecase.update(anyLong(), any(Pedido.class))).thenReturn(pedido);
      //  when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> result = pedidoController.put(1L, pedidoDto);

        assertEquals(pedidoDto.getId(), result.getBody().getId());
    }

    /*
    @Test
    public void get_ReturnsNotFound_WhenPedidoDoesNotExist() {
        when(getPedidoByIdUsecase.get(anyLong())).thenReturn(null);

        ResponseEntity<PedidoDto> result = pedidoController.get(1L);

        Ass
        assertions.asserThrows(EntityNotFoundException.class, () -> {
            throw new EntityNotFoundException("Pedido nao encontrado para o id :: " + 1L);
        });

    }

     */

    @Test
    public void put_ReturnsNotFound_WhenUpdateFails() {
        when(updatePedidoUsecase.update(anyLong(), any(Pedido.class))).thenReturn(null);

        ResponseEntity<PedidoDto> result = pedidoController.put(1L, new PedidoDto());

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void addItemsToPedido_ReturnsNotFound_WhenPedidoDoesNotExist() {
        when(getPedidoByIdUsecase.get(anyLong())).thenReturn(null);

        ResponseEntity<PedidoDto> result = pedidoController.addItemsToPedido(1L, List.of(new ItemDto()));

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void deleteItemFromPedido_ReturnsNotFound_WhenPedidoDoesNotExist() {
        when(getPedidoByIdUsecase.get(anyLong())).thenReturn(null);

        ResponseEntity<PedidoDto> result = pedidoController.deleteItemFromPedido(1L, 1L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void checkoutPedido_ReturnsNoContent_WhenCheckoutFails() {
        when(checkoutPedidoUsecase.checkoutPedido(any(Pedido.class))).thenReturn(null);

        ResponseEntity<PedidoDto> result = pedidoController.checkoutPedido(1L, 1L, "pos");

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    public void notification_ReturnsOk_WhenTopicIsMerchantOrder() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(1L);

        when(confirmPedidoUsecase.confirmPedido(any(Pedido.class))).thenReturn(pedido);
        //when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> result = pedidoController.notification(1L, "merchant_order");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pedidoDto.getId(), result.getBody().getId());
    }

    @Test
    public void notification_ReturnsOk_WhenTopicIsPayment() {
        Pedido pedido = new Pedido();
        pedido.setId(1L);

        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(1L);

        when(payPedidoUsecase.payPedido(any(Pedido.class))).thenReturn(pedido);
       // when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> result = pedidoController.notification(1L, "payment");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pedidoDto.getId(), result.getBody().getId());
    }

    @Test
    public void search_ReturnsOk_WhenStatusListIsProvided() {
        List<String> statusList = List.of("status1", "status2");
        List<Pedido> pedidos = List.of(new Pedido(), new Pedido());

        when(findPedidoByStatusUsecase.findByStatus(statusList)).thenReturn(pedidos);

        ResponseEntity<List<PedidoReduceDto>> result = pedidoController.search(statusList);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pedidos.size(), Objects.requireNonNull(result.getBody()).size());
    }

    @Test
    public void post_ReturnsCreated_WhenValidPedidoDtoIsProvided() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setId(1L);

        Pedido pedido = new Pedido();
        pedido.setId(1L);

        when(createPedidoUsecase.create(any(Pedido.class))).thenReturn(pedido);
       // when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(pedidoDto);

        ResponseEntity<PedidoDto> result = pedidoController.post(pedidoDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(pedidoDto.getId(), Objects.requireNonNull(result.getBody()).getId());
    }

    @Test
    public void addItemsToPedido_ReturnsOk_WhenValidIdAndItemsAreProvided() {
        Long id = 1L;
        List<ItemDto> itemDtos = List.of(new ItemDto(), new ItemDto());

        Pedido pedido = new Pedido();
        pedido.setId(id);

        List<Item> items = itemDtos.stream()
                .map(itemDto -> itemMapper.toDomain(itemDto))
                .collect(Collectors.toList());

        when(getPedidoByIdUsecase.get(id)).thenReturn(pedido);
        when(addItemPedidoUsecase.addItemPedido(eq(id), anyList())).thenReturn(pedido);
       // when(modelMapper.map(pedido, PedidoDto.class)).thenReturn(new PedidoDto());

        ResponseEntity<PedidoDto> result = pedidoController.addItemsToPedido(id, itemDtos);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
