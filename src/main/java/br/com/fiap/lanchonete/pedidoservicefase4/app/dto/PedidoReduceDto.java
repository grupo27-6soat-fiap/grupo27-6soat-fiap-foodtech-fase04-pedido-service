package br.com.fiap.lanchonete.pedidoservicefase4.app.dto;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PedidoReduceDto {
    private Long id;
    private StatusEnum status;
    private LocalDateTime criacao;
    private String tempo;
}
