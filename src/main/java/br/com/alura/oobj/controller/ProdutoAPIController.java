package br.com.alura.oobj.controller;

import br.com.alura.oobj.dto.DevolucaoProduto;
import br.com.alura.oobj.model.Produto;
import br.com.alura.oobj.repository.ProdutoRepository;
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
    public List<DevolucaoProduto> produtos(){
        List<Produto> produtos = produtoRepository.findAll();
        return DevolucaoProduto.converter(produtos);
    }
}
