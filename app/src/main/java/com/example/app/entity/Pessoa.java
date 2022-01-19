package com.example.app.entity;

import java.util.Date;

public class Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
    private Double rendaMensal;

    public Pessoa(String nome, String cpf, Integer idade, Double rendaMensal) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.rendaMensal = rendaMensal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }
}
