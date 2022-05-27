package br.com.alura.easybill.controller;

import br.com.alura.easybill.dto.DevolucaoVenda;
import br.com.alura.easybill.dto.RequisicaoItemVenda;
import br.com.alura.easybill.dto.RequisicaoVenda;
import br.com.alura.easybill.model.ItemVenda;
import br.com.alura.easybill.model.Venda;
import br.com.alura.easybill.repository.ItemVendaRepository;
import br.com.alura.easybill.repository.VendaRepository;
import br.com.alura.easybill.service.VendaService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class VendaAPIController {

    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final VendaService vendaService;

    public VendaAPIController(VendaRepository vendaRepository, ItemVendaRepository itemVendaRepository, VendaService vendaService) {
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.vendaService = vendaService;
    }

    @PostMapping("/vendas")
    @Transactional
    public ResponseEntity<RequisicaoItemVenda> criacaoDeVenda(@RequestBody @Valid RequisicaoVenda requisicaoVenda, UriComponentsBuilder uriBuilder, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Venda venda = vendaService.registraVenda(requisicaoVenda);

        URI uri = uriBuilder.path("/api/venda/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(new RequisicaoItemVenda());
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<DevolucaoVenda> retornaVendaPorId(@PathVariable Long id){
        Optional<Venda> venda = vendaRepository.findById(id);
        List<ItemVenda> itemVenda = itemVendaRepository.findAllByVenda_Id(id);
        if(venda.isPresent()){
            DevolucaoVenda devolucaoVenda = DevolucaoVenda.converter(venda, itemVenda);
            return ResponseEntity.ok(devolucaoVenda);
        }

        return ResponseEntity.notFound().build();
    }
}
