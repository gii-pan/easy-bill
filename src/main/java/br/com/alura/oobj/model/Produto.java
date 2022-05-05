package br.com.alura.oobj.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nomeProduto;

    @Column(length = 500, nullable = false)
    private String urlProduto;

    @Column(length = 1000)
    private String descricaoProduto;

    private BigDecimal precoProduto;
    private BigDecimal precoPromocionalProduto;

    @Column(length = 10)
    private String classeFiscalProduto;
}
