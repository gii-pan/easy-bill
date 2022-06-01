package br.com.alura.easybill.repository;

import br.com.alura.easybill.model.ItemVenda;
import br.com.alura.easybill.projection.VendasPorProdutoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findAllByVenda_Id(Long id);

    @Query("SELECT P.nome AS nomeProduto, SUM(I.quantidade) AS quantidade  FROM ItemVenda I  JOIN I.produto P GROUP BY (P.nome)")
    List<VendasPorProdutoProjection> relatorioVendaPorProduto();
}
