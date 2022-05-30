package br.com.alura.easybill.repository;

import br.com.alura.easybill.model.ItemVenda;
import br.com.alura.easybill.projection.VendasPorProdutoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findAllByVenda_Id(Long id);
    //@Query("SELECT P.nome , SUM(I.quantidade)  FROM ItemVenda I  JOIN Produto P ON P.id=I.produtoId GROUP BY (I.produtoId)")
    @Query(value = "SELECT P.NOME AS \"NOMEPRODUTO\", SUM(QUANTIDADE) AS \"QUANTIDADE\" FROM ITENS_VENDA I JOIN PRODUTOS P ON P.ID=I.PRODUTO_ID GROUP BY (PRODUTO_ID)", nativeQuery = true)
    List<VendasPorProdutoProjection> relatorioVendaPorProduto();
}
