package br.com.alura.easybill.repository;

import br.com.alura.easybill.model.*;
import br.com.alura.easybill.projection.VendasPorProdutoProjection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ItemVendaRepositoryTest {

    @Autowired
    private ItemVendaRepository itemVendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private VendaRepository vendaRepository;


    @Test
    public void deveriaRetornarUmRelatorioDeVendas() {
        List<ItemVenda> itemVendas = criaItensVenda();
        itemVendaRepository.saveAll(itemVendas);

        List<VendasPorProdutoProjection> listaProjection = itemVendaRepository.relatorioVendaPorProduto();

        assertThat(listaProjection.get(0).getNomeProduto()).isEqualTo("Maçã");
        assertThat(listaProjection.get(0).getQuantidade()).isEqualTo(14);
    }


    public List<ItemVenda> criaItensVenda() {
        ItemVenda itemVenda1 = new ItemVenda();
        ItemVenda itemVenda2 = new ItemVenda();
        List<ItemVenda> listaItemVenda = new ArrayList<>();

        Venda venda = criaVenda();
        Produto produto = criaProduto();

        itemVenda1.setQuantidade(10);
        itemVenda1.setPrecoUnitario(produto.getPreco());
        itemVenda1.setProduto(produto);
        itemVenda1.setPrecoUnitarioPromocional(produto.getPrecoPromocional());
        itemVenda1.setVenda(venda);
        itemVenda1.setObservacao("Encaminhar por caixas de 5 unidades");

        itemVenda2.setQuantidade(4);
        itemVenda2.setPrecoUnitario(produto.getPreco());
        itemVenda2.setProduto(produto);
        itemVenda2.setPrecoUnitarioPromocional(produto.getPrecoPromocional());
        itemVenda2.setVenda(venda);
        itemVenda2.setObservacao("Encaminhar por caixas de 2 unidades");

        listaItemVenda.add(itemVenda1);
        listaItemVenda.add(itemVenda2);

        return listaItemVenda;
    }

    public Produto criaProduto() {
        Produto produto = new Produto();
        produto.setNome("Maçã");
        produto.setDescricao("Fruta");
        produto.setUrlImagem("urlImagem");
        produto.setPreco(new BigDecimal("10.99"));
        produto.setPrecoPromocional(new BigDecimal("8.99"));
        produto.setClasseFiscal("2017.37.89");

        produtoRepository.save(produto);

        return produto;
    }

    public Venda criaVenda() {
        Venda venda = new Venda();
        Cliente cliente = criaCliente();
        venda.setDataRealizacao(LocalDateTime.now());
        venda.setStatus(Status.REALIZADA);
        venda.setCliente(cliente);
        venda.setId(1l);

        vendaRepository.save(venda);

        return venda;
    }

    public Cliente criaCliente(){
        Cliente cliente = new Cliente();
        Endereco endereco = criaEnderaco();
        cliente.setEmail("giulia.borges@gmail.com");
        cliente.setTelefone("62981247596");
        cliente.setNome("Giulia Borges");
        cliente.setCpf("78965412300");
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        return cliente;
    }

    public Endereco criaEnderaco() {
        Endereco endereco = new Endereco();
        endereco.setBairro("Jardim");
        endereco.setCidade("Goiânia");
        endereco.setEstado("GO");
        endereco.setComplemento("Em frente ao shopping ZZ");
        endereco.setNumero("958");
        endereco.setRua("São João");

        return endereco;
    }
}