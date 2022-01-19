package com.example.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app.entity.Pedido;
import com.example.app.entity.Pessoa;

public class DB {
    public static final DB INSTANCE = new DB();

    private static SQLiteDatabase db;
    private static DbConnection banco;

    public DB(){}

    public void setContext(Context context) {
        banco = new DbConnection(context);
    }

    public static long addPessoa(Pessoa pessoa) {
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(DbConnection.PESSOA_NOME, pessoa.getNome());
        values.put(DbConnection.PESSOA_CPF, pessoa.getCpf());
        values.put(DbConnection.PESSOA_IDADE, pessoa.getIdade());
        values.put(DbConnection.PESSOA_RENDA_MENSAL, pessoa.getRendaMensal());

        resultado = db.insert(DbConnection.PESSOA, null, values);
        db.close();

        return resultado;
    }

    public static long addPedido(Pedido pedido) {
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put(DbConnection.PEDIDO_PESSOA_ID, pedido.getPessoa_id());
        values.put(DbConnection.PEDIDO_VALOR_REQUERIDO, pedido.getValorRequerido());
        values.put(DbConnection.PEDIDO_SITUACAO, pedido.getSituacao().getCodigo());
        values.put(DbConnection.PEDIDO_VALOR_JUROS, pedido.getValorJuros());

        resultado = db.insert(DbConnection.PEDIDO, null, values);
        db.close();

        return resultado;
    }

    public Cursor carregaDados(){
        Cursor cursor;
        db = banco.getReadableDatabase();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(" pd.id, nome, situacao, valorJuros");
        sql.append(" FROM pedido pd ");
        sql.append(" JOIN pessoa ps ON ps.id = pd.pessoa_id");
        cursor = db.rawQuery(sql.toString(), null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
