package br.com.alura.easybill.dto;

import br.com.alura.easybill.model.ItemVenda;
import br.com.alura.easybill.model.Venda;
import br.com.alura.easybill.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Component
public class RequisicaoItemVenda {
    @NotNull @NotEmpty @Positive
    private int quantidade;
    @Size(max=200)
    private String observacao;
    @NotNull @NotEmpty @Positive
    private int produtoId;

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

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    private BigDecimal retornaValorDoPrecoUnitario(BigDecimal precoProduto){
        BigDecimal precoUnitario = BigDecimal.ZERO;
        precoUnitario = precoProduto;
        return precoUnitario;
    }

    public ItemVenda toItemVenda(ProdutoRepository produtoRepository, Venda venda) {
        ItemVenda item = new ItemVenda();
        item.setObservacao(observacao);
        item.setQuantidade(quantidade);
        item.setVenda(venda);
        item.setProduto(produtoRepository.findById((long) produtoId).get());
        item.setPrecoUnitario(retornaValorDoPrecoUnitario(produtoRepository.findById((long) produtoId).get().getPreco()));
        item.setPrecoUnitarioPromocional(produtoRepository.findById((long) produtoId).get().getPrecoPromocional());

        return item;
    }

}
