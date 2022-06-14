package br.com.alura.easybill.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;


public class RequisicaoVenda {
    @Positive
    private Long clienteId;

    @NotEmpty
    private List<RequisicaoItemVenda> itensVenda = new ArrayList<>();

    public RequisicaoVenda() {}

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<RequisicaoItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<RequisicaoItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public void adicionaItem(RequisicaoItemVenda requisicaoItemVenda) {
        itensVenda.add(requisicaoItemVenda);
    }
}
