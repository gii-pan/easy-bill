package br.com.alura.easybill.validator;

import br.com.alura.easybill.dto.RequisicaoNovoProduto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static java.util.Objects.isNull;
@Component
public class PrecoPromocionalValidator {

    public void validacaoPrecoPromocional(RequisicaoNovoProduto requisicaoNovoProduto, BindingResult result){
        if(isNull(requisicaoNovoProduto.getPreco()) || isNull(requisicaoNovoProduto.getPrecoPromocional())){
            return;
        }
        Integer compare = requisicaoNovoProduto.getPreco().compareTo(requisicaoNovoProduto.getPrecoPromocional());
        if(compare.equals(1)){
            return;
        }
        result.rejectValue("precoPromocional", "", "Preço promocional deve ser menor que o preço do produto");
    }
}
