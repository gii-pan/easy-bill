package br.com.alura.easybill.dto;

import br.com.alura.easybill.model.ItemVenda;
import br.com.alura.easybill.model.Produto;
import br.com.alura.easybill.model.Status;
import br.com.alura.easybill.model.Venda;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DevolucaoVenda {

    private Long id;
    private Status status;
    private LocalDateTime dataRealizacao;
    private Long clienteId;

    private List<ItemVenda> itens;

    public DevolucaoVenda(Optional<Venda> venda, List<ItemVenda> itemVenda) {
        this.id = venda.get().getId();
        this.status = venda.get().getStatus();
        this.dataRealizacao = venda.get().getDataRealizacao();
        this.clienteId = venda.get().getCliente().getId();
        this.itens = itemVenda;
    }

    public static DevolucaoVenda converter(Optional<Venda> venda, List<ItemVenda> itemVendas){
        return new DevolucaoVenda(venda, itemVendas);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDateTime dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}
