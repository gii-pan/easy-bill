package br.com.alura.oobj.dto;

import br.com.alura.oobj.model.Produto;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class RequisicaoNovoProduto {
    @NotBlank
    @Size(max = 150)
    private String nomeProduto;
    @Size(max = 1000)
    private String descricaoProduto;
    private BigDecimal precoProduto;
    private BigDecimal precoPromocionalProduto;
    @NotBlank
    @Size(max= 10)
    private String classeFiscalProduto;
    @NotBlank
    @Size(max = 500)
    private String urlProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }

    public BigDecimal getPrecoPromocionalProduto() {
        return precoPromocionalProduto;
    }

    public void setPrecoPromocionalProduto(BigDecimal precoPromocionalProduto) {
        this.precoPromocionalProduto = precoPromocionalProduto;
    }

    public String getClasseFiscalProduto() {
        return classeFiscalProduto;
    }

    public void setClasseFiscalProduto(String classeFiscalProduto) {
        this.classeFiscalProduto = classeFiscalProduto;
    }

    public String getUrlProduto() {
        return urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNomeProduto(nomeProduto);
        produto.setDescricaoProduto(descricaoProduto);
        produto.setUrlProduto(urlProduto);
        produto.setPrecoProduto(precoProduto);
        produto.setPrecoPromocionalProduto(precoPromocionalProduto);
        produto.setClasseFiscalProduto(classeFiscalProduto);
        return produto;
    }
}
