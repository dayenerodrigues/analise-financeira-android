package com.example.app.analise;

public class AnaliseCreditoAte40Anos extends AnaliseCredito {
    @Override
    protected Integer getReferenciaRendaMinima() {
        return 4;
    }

    @Override
    protected Integer getReferenciaRendaAprovada() {
        return 8;
    }

    @Override
    protected Integer getTaxaJuros() {
        return 20;
    }
}
