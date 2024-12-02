package br.com.fiap.lanchonete.pedidoservicefase4.infra.db.entities;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="pedido")
public class PedidoEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long cliente;

    @OneToMany(mappedBy="pedido", cascade = {CascadeType.PERSIST})
    private List<ItemEntity> itens;

    @Column(name="preco")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum steps;

    @Column(name="qr_Data")
    private String qrData;

    @Column(name="external_reference")
    private Long externalReference;

    @Column(name="payment_id")
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusPagamento;

    @CreationTimestamp
    private LocalDateTime criacao;

    @UpdateTimestamp
    private LocalDateTime alteracao;

    @Override
    public String toString() {
        return "PedidoData [id=" + id + ", cliente=" + cliente + ", itens=" + (itens != null ? itens.size() + " itens" : "null") + ", preco=" + preco + ", status="
                + status + ", criacao=" + criacao + ", alteracao=" + alteracao + "]";
    }
}
