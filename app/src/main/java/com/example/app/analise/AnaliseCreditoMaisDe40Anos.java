package com.example.app.analise;

public class AnaliseCreditoMaisDe40Anos extends AnaliseCredito {
    @Override
    protected Integer getReferenciaRendaMinima() {
        return 6;
    }

    @Override
    protected Integer getReferenciaRendaAprovada() {
        return 10;
    }

    @Override
    protected Integer getTaxaJuros() {
        return 15;
    }
}
