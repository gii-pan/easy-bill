package br.com.alura.easybill.controller;

import br.com.alura.easybill.dto.DevolucaoProduto;
import br.com.alura.easybill.model.Produto;
import br.com.alura.easybill.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoAPIController {

    private ProdutoRepository produtoRepository;

    public ProdutoAPIController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/api/produtos")
    public List<DevolucaoProduto> listagemDeProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return DevolucaoProduto.converter(produtos);
    }
}
