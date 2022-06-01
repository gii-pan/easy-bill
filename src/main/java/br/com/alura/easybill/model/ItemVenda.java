package br.com.alura.easybill.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_venda")
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
    private BigDecimal precoUnitarioPromocional;

    @ManyToOne
    private Venda venda;

    @ManyToOne
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

    public BigDecimal getPrecoUnitarioPromocional() {
        return precoUnitarioPromocional;
    }

    public void setPrecoUnitarioPromocional(BigDecimal precoUnitarioPromocional) {
        this.precoUnitarioPromocional = precoUnitarioPromocional;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
