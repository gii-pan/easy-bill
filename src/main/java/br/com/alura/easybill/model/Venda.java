package br.com.alura.easybill.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_realizacao")
    private LocalDateTime dataRealizacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDateTime dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setId(long l) {
    }
}
