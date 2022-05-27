package br.com.alura.easybill.validator;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    private MessageSource messageSource;

    public ErroDeValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<MensagemDeErro> handle(MethodArgumentNotValidException exception){
        List<MensagemDeErro> mensagemDeErros = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            MensagemDeErro error = new MensagemDeErro(e.getField(), mensagem);
            mensagemDeErros.add(error);
        });

        return mensagemDeErros;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public List<MensagemDeErro> handlerNotFound (NotFoundException exception){
        List<MensagemDeErro> mensagens = new ArrayList<>();
        mensagens.add(new MensagemDeErro("Cliente", exception.getMessage()));
        return mensagens;
    }
}
