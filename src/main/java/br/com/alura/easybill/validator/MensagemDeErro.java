package br.com.alura.easybill.validator;

public class MensagemDeErro {

    private String campo;
    private String erro;

    public MensagemDeErro(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
