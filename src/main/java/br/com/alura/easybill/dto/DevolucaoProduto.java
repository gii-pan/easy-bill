package br.com.alura.easybill.dto;

import br.com.alura.easybill.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class DevolucaoProduto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private BigDecimal precoPromocional;

    private String classeFiscal;

    private String url;

    public DevolucaoProduto(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.precoPromocional = produto.getPrecoPromocional();
        this.classeFiscal = produto.getClasseFiscal();
        this.url = produto.getUrl();;
    }

    public static List<DevolucaoProduto> converter(List<Produto> produtos){
        return produtos.stream().map(DevolucaoProduto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public String getUrl() {return url; }
}
