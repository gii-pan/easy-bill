package br.com.alura.easybill.validator;

import br.com.alura.easybill.dto.RequisicaoProduto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static java.util.Objects.isNull;
@Component
public class PrecoPromocionalValidator {

    public void validacaoPrecoPromocional(RequisicaoProduto requisicaoProduto, BindingResult result){
        if(isNull(requisicaoProduto.getPreco()) || isNull(requisicaoProduto.getPrecoPromocional())){
            return;
        }
        Integer compare = requisicaoProduto.getPreco().compareTo(requisicaoProduto.getPrecoPromocional());
        if(compare.equals(1)){
            return;
        }
        result.rejectValue("precoPromocional", "", "Preço promocional deve ser menor que o preço do produto");
    }
}
