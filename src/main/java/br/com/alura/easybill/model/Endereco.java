package br.com.alura.easybill.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

    @Column(nullable = false, name = "rua")
    private String rua;

    @Column(nullable = false, name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(nullable = false, name = "bairro")
    private String bairro;

    @Column(nullable = false, name = "cidade")
    private String cidade;

    @Column(nullable = false, name = "estado")
    private String estado;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
