package br.com.fiap.lanchonete.pedidoservicefase4.app.controllers;



import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.ItemDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.PedidoDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.PedidoReduceDto;
import br.com.fiap.lanchonete.pedidoservicefase4.app.mappers.ItemMapper;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Item;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Pedido;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.usecase.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final GetPedidoByIdUsecase getPedidoByIdUsecase;

    private final FindPedidoByStatusUsecase findPedidoByStatusUsecase;

    private final CreatePedidoUsecase createPedidoUsecase;

    private final UpdatePedidoUsecase updatePedidoUsecase;

    private final AddItemPedidoUsecase addItemPedidoUsecase;

    private final DeleteItemPedidoUsecase deleteItemPedidoUsecase;

    private final CheckoutPedidoUsecase checkoutPedidoUsecase;

    private final ConfirmPedidoUsecase confirmPedidoUsecase;

    private final PayPedidoUsecase payPedidoUsecase;


    public final ModelMapper modelMapper;


    public final ItemMapper itemMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDto> get(@PathVariable(value = "id") Long id) {
        Pedido pedido = Optional.ofNullable(getPedidoByIdUsecase.get(id))
                .orElseThrow(() -> new EntityNotFoundException("Pedido nao encontrado para o id :: " + id));
        return ResponseEntity.ok().body(modelMapper.map(pedido, PedidoDto.class));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoDto> put(@PathVariable Long id, @RequestBody PedidoDto PedidoDto) {
        Pedido pedido = updatePedidoUsecase.update(id, modelMapper.map(PedidoDto, Pedido.class));
        if (Objects.nonNull(pedido)) {
            return ResponseEntity.ok(modelMapper.map(pedido, PedidoDto.class));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PedidoReduceDto>> search(@RequestParam(name = "status", required = false) List<String> status) {
        List<PedidoDto> pedidos = findPedidoByStatusUsecase.findByStatus(status).stream()
                .map(pedido -> modelMapper.map(pedido, PedidoDto.class))
                .toList();
        List<PedidoReduceDto> pp = pedidos.stream()
                .map(p -> PedidoReduceDto.builder()
                        .id(p.getId())
                        .status(p.getStatus())
                        .criacao(p.getCriacao())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(pp);
    }

    @PostMapping
    public ResponseEntity<PedidoDto> post(@Validated @RequestBody PedidoDto PedidoDto) {
        Pedido pedido = createPedidoUsecase.create(modelMapper.map(PedidoDto, Pedido.class));
        return new ResponseEntity<PedidoDto>(modelMapper.map(pedido, PedidoDto.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/add-items")
    public ResponseEntity<PedidoDto> addItemsToPedido(@PathVariable Long id, @RequestBody List<ItemDto> itens) {
        Pedido pedido = getPedidoByIdUsecase.get(id);
        if (Objects.nonNull(pedido)) {
            List<Item> itensDomain = itemMapper.toDomain(itens);
            pedido = addItemPedidoUsecase.addItemPedido(id, itensDomain);
            return ResponseEntity.ok(modelMapper.map(pedido, PedidoDto.class));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}/delete-item/{idItem}")
    public ResponseEntity<PedidoDto> deleteItemFromPedido(@PathVariable Long id, @PathVariable Long idItem) {
        Pedido pedido = getPedidoByIdUsecase.get(id);
        if (Objects.nonNull(pedido)) {
        	deleteItemPedidoUsecase.deleteItemPedido(id, idItem);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/{id}/checkout")
    public ResponseEntity<PedidoDto> checkoutPedido(@PathVariable(name = "id") Long id, 
                                                    @RequestParam(name = "collector",required = true) Long collector,
                                                    @RequestParam(name = "pos",required = true) String pos) {
                                                        
        Pedido pedido = Pedido.builder().id(id).collector(collector).pos(pos).build();
        Pedido ped = checkoutPedidoUsecase.checkoutPedido(pedido);
        if (Objects.nonNull(ped)) {
            return ResponseEntity.ok(modelMapper.map(ped, PedidoDto.class));
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/notifications")
    public ResponseEntity<PedidoDto> notification(@RequestParam(name = "id") Long id, @RequestParam(name = "topic") String topic) {
        Pedido pedido = Pedido.builder().orderId(id).paymentId(id).build();
        if (("merchant_order").equals(topic) && Objects.nonNull(id)) {
            pedido = confirmPedidoUsecase.confirmPedido(pedido);
        }
        if (("payment").equals(topic) && Objects.nonNull(id)) {
            pedido = payPedidoUsecase.payPedido(pedido);
        }
        return ResponseEntity.ok(modelMapper.map(pedido, PedidoDto.class));
    }
}
