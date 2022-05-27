package br.com.alura.easybill.service;

import br.com.alura.easybill.dto.RequisicaoVenda;
import br.com.alura.easybill.model.Cliente;
import br.com.alura.easybill.model.ItemVenda;
import br.com.alura.easybill.model.Venda;
import br.com.alura.easybill.repository.ClienteRepository;
import br.com.alura.easybill.repository.ItemVendaRepository;
import br.com.alura.easybill.repository.ProdutoRepository;
import br.com.alura.easybill.repository.VendaRepository;
import br.com.alura.easybill.validator.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.alura.easybill.model.Status.REALIZADA;

@Service
public class VendaService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;

    public VendaService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository, VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    public Venda registraVenda(RequisicaoVenda requisicaoVenda)
    {
        Cliente cliente = clienteRepository.findById(requisicaoVenda.getClienteId())
                .orElseThrow(() -> new NotFoundException("Não encontrado cliente: " + requisicaoVenda.getClienteId()));
        Venda venda = criaVenda(cliente);
        vendaRepository.save(venda);

        List<ItemVenda> itemVendas = criaItemVenda(requisicaoVenda, venda);
        itemVendaRepository.saveAll(itemVendas);

        return venda;
    }

    private Venda criaVenda(Cliente cliente) {
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataRealizacao(LocalDateTime.now());
        venda.setStatus(REALIZADA);
        return venda;
    }

    private List<ItemVenda> criaItemVenda(RequisicaoVenda requisicaoVenda, Venda venda) {
        List<ItemVenda> itens = new ArrayList<>();
        requisicaoVenda.getItensVenda().forEach(item ->
                itens.add(item.toItemVenda(produtoRepository, venda))

        );
        return itens;
    }
}
