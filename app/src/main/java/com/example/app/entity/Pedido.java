package com.example.app.entity;

public class Pedido {
    private Long id;
    private Long pessoa_id;
    private Double valorRequerido;
    private Situacao situacao;
    private Double valorJuros;

    public Pedido(Long pessoa_id, Double valorRequerido) {
        this.pessoa_id = pessoa_id;
        this.valorRequerido = valorRequerido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPessoa_id() {
        return pessoa_id;
    }

    public void setPessoa_id(Long pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    public Double getValorRequerido() {
        return valorRequerido;
    }

    public void setValorRequerido(Double valorRequerido) {
        this.valorRequerido = valorRequerido;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Double getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(Double valorJuros) {
        this.valorJuros = valorJuros;
    }
}
