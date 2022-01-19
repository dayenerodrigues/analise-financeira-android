package com.example.app.entity;

public enum Situacao {
    APROVADO("A", "APROVADO"),
    EM_ANALISE("E", "EM ANÁLISE"),
    REPROVADO("R", "REPROVADO");

    String codigo;
    String descricao;

    Situacao(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
