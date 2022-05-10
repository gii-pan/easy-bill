package br.com.alura.oobj.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nomeProduto;

    @Column(length = 500, nullable = false)
    private String urlProduto;

    @Column(length = 1000)
    private String descricaoProduto;

    private BigDecimal precoProduto;
    private BigDecimal precoPromocionalProduto;

    @Column(length = 10)
    private String classeFiscalProduto;

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }

    public void setPrecoPromocionalProduto(BigDecimal precoPromocionalProduto) {
        this.precoPromocionalProduto = precoPromocionalProduto;
    }

    public void setClasseFiscalProduto(String classeFiscalProduto) {
        this.classeFiscalProduto = classeFiscalProduto;
    }
}
