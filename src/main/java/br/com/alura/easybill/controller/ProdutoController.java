package br.com.alura.easybill.controller;

import br.com.alura.easybill.dto.RequisicaoProduto;
import br.com.alura.easybill.model.Produto;
import br.com.alura.easybill.repository.ProdutoRepository;
import br.com.alura.easybill.validator.PrecoPromocionalValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final PrecoPromocionalValidator precoPromocionalValidator;
    private final ProdutoAPIController produtoAPIController;

    public ProdutoController(ProdutoRepository produtoRepository, PrecoPromocionalValidator precoPromocionalValidator, ProdutoAPIController produtoAPIController){
        this.produtoRepository = produtoRepository;
        this.precoPromocionalValidator = precoPromocionalValidator;
        this.produtoAPIController = produtoAPIController;
    }
    @GetMapping("/produtos/formulario")
    public String formulario(RequisicaoProduto requisicaoProduto) {
        return "admin/produtos/formulario.html";
    }

    @GetMapping("/produto")
    public ModelAndView listaProdutos(){
        ModelAndView modelAndView = new ModelAndView("admin/produto");
        modelAndView.addObject("produto", produtoAPIController.listagemDeProdutosFormulario());
        return modelAndView;
    }

    @PostMapping("/produtos/novo")
    public String novo(@Valid RequisicaoProduto requisicaoProduto, BindingResult result) {
        precoPromocionalValidator.validacaoPrecoPromocional(requisicaoProduto,result);
        if(result.hasErrors()){
            return "admin/produtos/formulario.html";
        }
       Produto produto = requisicaoProduto.toProduto();
       produtoRepository.save(produto);

       return "redirect:/admin/produtos/formulario";
    }
}
