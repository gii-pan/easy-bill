package br.com.alura.oobj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @GetMapping("formulario")
    public String formulario() {
        return "produto/formulario";
    }

//    @PostMapping("novo")
//    public String novo() {
//        return "";
//    }
}
