package br.com.fiap.lanchonete.pedidoservicefase4.domain.provider;

import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;

public interface ProdutoProvider {

    Produto get(Long id);

}
