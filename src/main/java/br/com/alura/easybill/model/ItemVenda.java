package br.com.alura.easybill.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantidade;

    @Column(length = 500)
    private String observacao;

    @Column(nullable = false, name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "preco_unitario_promocional")
    private BigDecimal getPrecoUnitarioPromocional;

    @ManyToOne
    private Venda venda;

    @ManyToOne
    @Column(name = "produto_id")
    private Produto produto;


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getGetPrecoUnitarioPromocional() {
        return getPrecoUnitarioPromocional;
    }

    public void setGetPrecoUnitarioPromocional(BigDecimal getPrecoUnitarioPromocional) {
        this.getPrecoUnitarioPromocional = getPrecoUnitarioPromocional;
    }

}
