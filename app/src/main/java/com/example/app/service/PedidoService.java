package com.example.app.service;

import com.example.app.analise.AnaliseCredito;
import com.example.app.analise.AnaliseCreditoAte25Anos;
import com.example.app.analise.AnaliseCreditoAte40Anos;
import com.example.app.analise.AnaliseCreditoMaisDe40Anos;
import com.example.app.entity.Pedido;
import com.example.app.entity.Pessoa;
import com.example.app.entity.Situacao;

public class PedidoService {

    public AnaliseCredito getAnaliseCredito(Pessoa requerente) {
        if (requerente.getIdade() < 25) {
            return new AnaliseCreditoAte25Anos();
        }

        if (requerente.getIdade() <  40) {
            return new AnaliseCreditoAte40Anos();
        }

        return new AnaliseCreditoMaisDe40Anos();
    }

    public Pedido realizaPedido(Pessoa pessoa, String valorRequerido) {
        AnaliseCredito analise = getAnaliseCredito(pessoa);
        Pedido pedido = new Pedido(pessoa.getId(), Double.valueOf(valorRequerido));
        pedido.setSituacao(analise.analisaCredito(pessoa.getRendaMensal()));

        if (!Situacao.REPROVADO.getCodigo().equals(pedido.getSituacao().getCodigo())) {
            pedido.setValorJuros(analise.calculaValorCredito(pedido.getValorRequerido()));
        }

        return pedido;
    }
}
