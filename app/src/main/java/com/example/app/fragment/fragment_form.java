package com.example.app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.db.DB;
import com.example.app.entity.Pedido;
import com.example.app.entity.Pessoa;
import com.example.app.service.PedidoService;

public class fragment_form extends Fragment {
    private static final Integer IDADE_MINIMA = 18;

    private String nome;
    private String cpf;
    private String idade;
    private String rendaMensal;
    private String valorRequerido;

    private PedidoService pedidoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Button botao = (Button)view.findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtNome = (EditText)view.findViewById(R.id.txtNome);
                EditText txtCpf = (EditText)view.findViewById((R.id.txtCPF));
                EditText txtIdade = (EditText)view.findViewById(R.id.txtIdade);
                EditText txtRendaMensal = (EditText)view.findViewById(R.id.txtRendaMensal);
                EditText txtValorRequerido = (EditText)view.findViewById(R.id.txtValorRequerido);

                nome = txtNome.getText().toString();
                cpf = txtCpf.getText().toString();
                idade = txtIdade.getText().toString();
                rendaMensal = txtRendaMensal.getText().toString();
                valorRequerido = txtValorRequerido.getText().toString();

                if (isFormValid()) {
                    Pessoa pessoa = new Pessoa(nome, cpf, Integer.valueOf(idade), Double.valueOf(rendaMensal));
                    Pedido pedido = getPedidoService().realizaPedido(pessoa, valorRequerido);

                    long idPessoa = DB.INSTANCE.addPessoa(pessoa);
                    pedido.setPessoa_id(idPessoa);
                    DB.INSTANCE.addPedido(pedido);

                    Toast.makeText(getActivity().getApplicationContext(), String.format("O status do seu pedido é: %s", pedido.getSituacao().getDescricao()), Toast.LENGTH_LONG).show();

                    clearFields(txtNome, txtCpf, txtIdade, txtRendaMensal, txtValorRequerido);
                }
            }
        });
    }

    private void clearFields(EditText txtNome, EditText txtCpf, EditText txtIdade, EditText txtRendaMensal, EditText txtValorRequerido) {
        txtNome.setText("");
        txtCpf.setText("");
        txtIdade.setText("");
        txtRendaMensal.setText("");
        txtValorRequerido.setText("");
    }

    private boolean isFormValid() {
        if (null == nome || "".equals(nome)) {
            Toast.makeText(getActivity().getApplicationContext(), "Campo NOME obrigatório!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (null == cpf || "".equals(cpf)) {
            Toast.makeText(getActivity().getApplicationContext(), "Campo CPF obrigatório!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (null == idade || "".equals(idade)) {
            Toast.makeText(getActivity().getApplicationContext(), "Campo IDADE obrigatório!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (IDADE_MINIMA.compareTo(Integer.valueOf(idade)) >= 0) {
            Toast.makeText(getActivity().getApplicationContext(), "Somente permitido para maiores de 18 anos!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (null == rendaMensal || "".equals(rendaMensal)) {
            Toast.makeText(getActivity().getApplicationContext(), "Campo RENDA MENSAL obrigatório!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (null == valorRequerido || "".equals(valorRequerido)) {
            Toast.makeText(getActivity().getApplicationContext(), "Campo VALOR REQUERIDO obrigatório!", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private PedidoService getPedidoService() {
        if (this.pedidoService == null) {
            this.pedidoService = new PedidoService();
        }

        return this.pedidoService;
    }
}