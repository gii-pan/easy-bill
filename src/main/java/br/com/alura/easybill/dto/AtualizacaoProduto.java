package br.com.alura.easybill.dto;

import br.com.alura.easybill.model.Produto;
import br.com.alura.easybill.repository.ProdutoRepository;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AtualizacaoProduto {
    @NotBlank
    @Size(max = 150)
    private String nome;

    @Size(max = 1000)
    private String descricao;

    @NotNull
    @NotEmpty
    @Positive
    private BigDecimal preco;

    @Positive
    private BigDecimal precoPromocional;

    @NotBlank
    @Size(min=10,max= 10)
    @Pattern(regexp = "^[\\d]{4}[.][\\d]{2}[.][\\d]{2}+$")
    private String classeFiscal;

    @NotBlank
    @Size(max = 500)
    private String urlImagem;

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

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String url) {
        this.urlImagem = url;
    }

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getById(id);

        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setUrlImagem(this.urlImagem);
        produto.setPreco(this.preco);
        produto.setPrecoPromocional(this.precoPromocional);
        produto.setClasseFiscal(this.classeFiscal);

        return produto;
    }
}
