package br.com.alura.easybill.repository;

import br.com.alura.easybill.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findAllByVenda_Id(Long id);
}
