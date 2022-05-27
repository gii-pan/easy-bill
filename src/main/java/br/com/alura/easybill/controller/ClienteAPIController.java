package br.com.alura.easybill.controller;

import br.com.alura.easybill.dto.DevolucaoCliente;
import br.com.alura.easybill.dto.RequisicaoCliente;
import br.com.alura.easybill.model.Cliente;
import br.com.alura.easybill.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
public class ClienteAPIController {

    private final ClienteRepository clienteRepository;

    public ClienteAPIController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/clientes")
    public ResponseEntity<RequisicaoCliente> criacaoDeCliente(@RequestBody @Valid RequisicaoCliente requisicaoCliente, UriComponentsBuilder uriBuilder, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Cliente cliente = requisicaoCliente.toCliente();
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/api/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new RequisicaoCliente(cliente));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<DevolucaoCliente> retornaClientePorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) return ResponseEntity.ok(new DevolucaoCliente(cliente.get()));
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/admin/clientes")
    public ResponseEntity<List<DevolucaoCliente>> retornaClientePorEstado(@RequestParam(required = false) String estado){
        if(estado == null) {
            List<Cliente> cliente = clienteRepository.findAll();
            return ResponseEntity.ok(DevolucaoCliente.converter(cliente));
        }
        List<Cliente> clientePorEstado = clienteRepository.findByEstado(estado);
        return ResponseEntity.ok(DevolucaoCliente.converter(clientePorEstado));
    }
}
