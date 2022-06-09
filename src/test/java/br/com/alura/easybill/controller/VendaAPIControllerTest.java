package br.com.alura.easybill.controller;

import br.com.alura.easybill.model.Cliente;
import br.com.alura.easybill.model.Endereco;
import br.com.alura.easybill.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
@AutoConfigureMockMvc
//@ActiveProfiles("test")
class VendaAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void deveriaDevolver200CasoDadosDeCriacaoDeVendaEstejamCorretos() throws Exception {
        URI uri = new URI("/api/vendas");
//        Cliente cliente = criaCliente();
//        clienteRepository.save(cliente);
        String json = "{\n" +
                "  \"clienteId\": 1,\n" +
                "  \"itensVenda\": [\n" +
                "    {\"quantidade\": 3, \"observacao\": \"Rosa\", \"produtoId\": 2},\n" +
                "    {\"quantidade\": 1, \"produtoId\": 1}\n" +
                "  ]\n" +
                "}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(201));

    }

    @Test
    public void deveriaDevolver400CasoDadosDeCriacaoDeVendaEstejamIncorretos() throws Exception {
        URI uri = new URI("/api/vendas");
        String json = "{\n" +
                "  \"clienteId\": 1,\n" +
                "  \"itensVenda\": [\n" +
                "    {\"quantidade\": , \"observacao\": \"Rosa\", \"produtoId\": 2},\n" +
                "    {\"quantidade\": 1, \"produtoId\": 1}\n" +
                "  ]\n" +
                "}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(400));

    }

    public Cliente criaCliente(){
        Cliente cliente = new Cliente();
        Endereco endereco = criaEnderaco();
//        cliente.setId(1l);
        cliente.setEmail("giulia.borges@gmail.com");
        cliente.setTelefone("62981247596");
        cliente.setNome("Giulia Borges");
        cliente.setCpf("78965412300");
        cliente.setEndereco(endereco);


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