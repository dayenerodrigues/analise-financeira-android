package com.example.app.analise;

public class AnaliseCreditoAte25Anos extends AnaliseCredito{

    @Override
    protected Integer getReferenciaRendaMinima() {
        return 2;
    }

    @Override
    protected Integer getReferenciaRendaAprovada() {
        return 5;
    }

    @Override
    protected Integer getTaxaJuros() {
        return 25;
    }
}
