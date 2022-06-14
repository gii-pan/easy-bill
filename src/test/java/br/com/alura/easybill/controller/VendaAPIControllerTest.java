package br.com.alura.easybill.controller;

import br.com.alura.easybill.dto.RequisicaoItemVenda;
import br.com.alura.easybill.dto.RequisicaoVenda;
import br.com.alura.easybill.model.Cliente;
import br.com.alura.easybill.model.Endereco;
import br.com.alura.easybill.model.Produto;
import br.com.alura.easybill.repository.ClienteRepository;
import br.com.alura.easybill.repository.ItemVendaRepository;
import br.com.alura.easybill.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class VendaAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void deveriaDevolver201CasoDadosDeCriacaoDeVendaEstejamCorretos() throws Exception {
        URI uri = new URI("/api/vendas");

        Endereco endereco = criaEnderaco("Pedro Ludovico","Goiânia","GO",
                "Perto do Shopping Gyn","456","58");

        Cliente cliente = criaCliente(endereco, "giuliana@gmail.com", "6284556974",
                "Giuliana", "12305678412");

        RequisicaoVenda requisicaoVenda = criaVenda(cliente);
        String requisicaoVendaJson = objectMapper.writeValueAsString(requisicaoVenda);


        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(requisicaoVendaJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.header().exists("Location"));

    }

    @Test
    public void deveriaDevolver400CasoDadosDeCriacaoDeVendaEstejamIncorretos() throws Exception {
        URI uri = new URI("/api/vendas");
        String json = """
                {
                  "clienteId": 1,
                  "itensVenda": [
                    {"quantidade": , "observacao": "Rosa", "produtoId": 2},
                    {"quantidade": 1, "produtoId": 1}
                  ]
                }""";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(400));

    }

    private RequisicaoVenda criaVenda(Cliente cliente) {
        RequisicaoVenda requisicaoVenda = new RequisicaoVenda();
        requisicaoVenda.setClienteId(cliente.getId());

        RequisicaoItemVenda itemVenda = new RequisicaoItemVenda();
        itemVenda.setObservacao("Encaminhar separado");
        itemVenda.setQuantidade(4);

        Produto produto = criaProduto("Melancia", "1775.21.59", "",
                new BigDecimal("11.99"),new BigDecimal("9.99"), "urlpera");
        itemVenda.setProdutoId(produto.getId());

        requisicaoVenda.adicionaItem(itemVenda);
        return requisicaoVenda;
    }

    private Produto criaProduto(String nome, String classeFiscal,
                                String descricao, BigDecimal preco,
                                BigDecimal precoPromocional, String urlImagem) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setClasseFiscal(classeFiscal);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setPrecoPromocional(precoPromocional);
        produto.setUrlImagem(urlImagem);

        produtoRepository.save(produto);

        return produto;
    }

    public Cliente criaCliente(Endereco endereco, String email,
                               String telefone, String nome, String cpf){
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        return cliente;
    }

    public Endereco criaEnderaco(String bairro, String cidade, String estado, String complemento, String numero, String rua ) {
        Endereco endereco = new Endereco();
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setComplemento(complemento);
        endereco.setNumero(numero);
        endereco.setRua(rua);

        return endereco;
    }
}