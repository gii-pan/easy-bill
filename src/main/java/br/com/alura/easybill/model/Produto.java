package br.com.alura.easybill.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false, name = "nome")
    private String nome;

    @Column(length = 500, nullable = false, name = "url_imagem")
    private String urlImagem;

    @Column(length = 1000, name = "descricao")
    private String descricao;

    @Column(nullable = false, name = "preco")
    private BigDecimal preco;

    @Column(name = "preco_promocional")
    private BigDecimal precoPromocional;

    @Column(length = 10, nullable = false, name = "classe_fiscal")
    private String classeFiscal;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String url) {
        this.urlImagem = url;
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

    public void setId(Long id) { this.id = id; }
}
