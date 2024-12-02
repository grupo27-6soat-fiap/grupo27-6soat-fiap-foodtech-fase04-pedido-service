package br.com.fiap.lanchonete.pedidoservicefase4.infra.produtoAdapter.dto;

import br.com.fiap.lanchonete.pedidoservicefase4.app.dto.ProdutoDto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.entities.Produto;
import br.com.fiap.lanchonete.pedidoservicefase4.domain.provider.ProdutoProvider;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ProdutoAdapter implements ProdutoProvider {

    private WebClient webClient;
    private final WebClient.Builder webClientBuilder;


    @Value("${product.service.url}")
    private String productServiceUrl;


    public ProdutoAdapter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder; // Inicializando a variável final
        this.webClient = webClientBuilder.baseUrl(productServiceUrl).build();
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder.baseUrl(productServiceUrl).build();
    }

    @Override
    public Produto get(Long id) {

        //Realizar chamada para o serviço de produtos para buscar o produto pelo id
        //Retornar o produto encontrado
        //Utilizar ProdutoDTO para mapear o retorno do serviço de produtos
        //utilizar o método fromDTO para converter o ProdutoDTO para Produto
        //Caso não encontre o produto, retornar null
        //Utilizar Webclient para realizar a chamada para o serviço de produtos

                ProdutoDto produtoDto = webClient.get()
                .uri("/produto/{id}", id)
                .retrieve()
                .bodyToMono(ProdutoDto.class)
                .block();

        if (produtoDto == null) {
            return null;
        }



        return fromDTO(produtoDto);
    }

    private Produto fromDTO(ProdutoDto produtoDto) {
        return Produto.builder()
                .id(produtoDto.getId())
                .nome(produtoDto.getNome())
                .descricao(produtoDto.getDescricao())
                .preco(produtoDto.getPreco())
                .categoria(produtoDto.getCategoria())
                .build();
    }
}
