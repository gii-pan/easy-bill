package br.com.alura.easybill.controller;

import br.com.alura.easybill.dto.AtualizacaoProduto;
import br.com.alura.easybill.dto.DevolucaoProduto;
import br.com.alura.easybill.dto.RequisicaoProduto;
import br.com.alura.easybill.model.Produto;
import br.com.alura.easybill.repository.ProdutoRepository;
import br.com.alura.easybill.validator.PrecoPromocionalValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
public class ProdutoAPIController {

    private final ProdutoRepository produtoRepository;
    private final PrecoPromocionalValidator precoPromocionalValidator;

    public ProdutoAPIController(ProdutoRepository produtoRepository, PrecoPromocionalValidator precoPromocionalValidator){
        this.produtoRepository = produtoRepository;
        this.precoPromocionalValidator = precoPromocionalValidator;
    }

    @GetMapping("/produtos")
    public List<DevolucaoProduto> listagemDeProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return DevolucaoProduto.converter(produtos);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<DevolucaoProduto> produtoPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()) return ResponseEntity.ok(new DevolucaoProduto(produto.get()));
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/admin/produtos")
    public ResponseEntity<RequisicaoProduto> criacaoDeProduto(@RequestBody @Valid RequisicaoProduto requisicaoProduto, UriComponentsBuilder uriBuilder, BindingResult result) {
        precoPromocionalValidator.validacaoPrecoPromocional(requisicaoProduto, result);
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(new RequisicaoProduto());
        }
        Produto produto = requisicaoProduto.toProduto();
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/api/admin/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new RequisicaoProduto(produto));
    }

    @PutMapping("/admin/produtos/{id}")
    @Transactional
    public ResponseEntity<RequisicaoProduto> atualizarProdutoPorId(@PathVariable Long id, @RequestBody @Valid AtualizacaoProduto atualizacaoProduto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            Produto produto = atualizacaoProduto.atualizar(id, produtoRepository);
            return ResponseEntity.ok(new RequisicaoProduto(produto));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/admin/produtos/{id}")
    public ResponseEntity<?> removerPorId(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
