package br.com.alura.easybill.dto;

import br.com.alura.easybill.model.Produto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class RequisicaoNovoProduto {
    @NotBlank
    @Size(max = 150)
    private String nome;
    @Size(max = 1000)
    private String descricao;
    @NotNull
    @DecimalMin("0.01")
    private BigDecimal preco;
    @DecimalMin("0.01")
    private BigDecimal precoPromocional;
    @NotBlank
    @Size(min=10,max= 10)
    @Pattern(regexp = "^[0-9]{4}[.][0-9]{2}[.][0-9]{2}+$")
    private String classeFiscal;
    @NotBlank
    @Size(max = 500)
    private String url;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public String getClasseFiscal() {
        return classeFiscal;
    }

    public void setClasseFiscal(String classeFiscal) {
        this.classeFiscal = classeFiscal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setUrl(url);
        produto.setPreco(preco);
        produto.setPrecoPromocional(precoPromocional);
        produto.setClasseFiscal(classeFiscal);
        return produto;
    }
}
