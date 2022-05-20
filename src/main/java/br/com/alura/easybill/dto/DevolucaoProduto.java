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

    private String classeFiscal;

    private String url;

    public DevolucaoProduto(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = getDescricaoResumida(produto.getDescricao());
        this.preco = produto.getPrecoPromocional() != null ? produto.getPrecoPromocional() : produto.getPreco();
        this.classeFiscal = produto.getClasseFiscal();
        this.url = produto.getUrl();;
    }

    public static List<DevolucaoProduto> converter(List<Produto> produtos){
        return produtos.stream().map(DevolucaoProduto::new).collect(Collectors.toList());
    }

    private String getDescricaoResumida(String descricao){
        if(descricao.length() <= 250){
            return descricao;
        }
        String descricaoTruncada = descricao.substring(0,247);

        return descricaoTruncada + "...";
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

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public String getUrl() {return url; }
}
