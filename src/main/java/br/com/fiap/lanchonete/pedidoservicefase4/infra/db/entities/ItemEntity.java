package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="item")
public class ItemEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @NotNull
    private PedidoEntity pedido;

    @JoinColumn(name = "produto_id")
    @NotNull
    private Long produto;

    @Column(name="preco")
    @NotNull
    private BigDecimal preco;

    @Override
    public String toString() {
        return "ItemData [id=" + id + ", pedido=" + pedido + ", produto=" + produto + ", preco=" + preco + "]";
    }
}
