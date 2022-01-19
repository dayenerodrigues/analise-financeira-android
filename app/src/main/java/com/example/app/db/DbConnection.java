package com.example.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;
import java.util.List;

public class DbConnection extends SQLiteOpenHelper {
    private static final String DB = "banco.db";

    public static final String PESSOA = "pessoa";
    public static final String PESSOA_ID = "id";
    public static final String PESSOA_NOME = "nome";
    public static final String PESSOA_CPF = "cpf";
    public static final String PESSOA_IDADE = "dataNascimento";
    public static final String PESSOA_RENDA_MENSAL = "rendaMensal";

    public static final String PEDIDO = "pedido";
    public static final String PEDIDO_ID = "id";
    public static final String PEDIDO_PESSOA_ID = "pessoa_id";
    public static final String PEDIDO_VALOR_REQUERIDO = "valorRequerido";
    public static final String PEDIDO_VALOR_JUROS = "valorJuros";
    public static final String PEDIDO_SITUACAO = "situacao";

    private static final List<String> TABLES = Arrays.asList(PESSOA, PEDIDO);

    private static final int VERSION = 1;

    public DbConnection(Context context){
        super(context, DB,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        criaTabelaPessoa(db);
        criaTabelaPedido(db);
    }

    private void criaTabelaPessoa(SQLiteDatabase db) {
        StringBuilder ddl = new StringBuilder();
        ddl.append("CREATE TABLE ").append(PESSOA).append("( ");
        ddl.append(PESSOA_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        ddl.append(PESSOA_NOME).append(" TEXT,");
        ddl.append(PESSOA_CPF).append(" TEXT,");
        ddl.append(PESSOA_IDADE).append(" INTEGER,");
        ddl.append(PESSOA_RENDA_MENSAL).append(" REAL");
        ddl.append(");");
        db.execSQL(ddl.toString());
    }

    private void criaTabelaPedido(SQLiteDatabase db) {
        StringBuilder ddl = new StringBuilder();
        ddl.append("CREATE TABLE ").append(PEDIDO).append(" ( ");
        ddl.append(PEDIDO_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        ddl.append(PEDIDO_PESSOA_ID).append(" INTEGER,");
        ddl.append(PEDIDO_VALOR_REQUERIDO).append(" REAL,");
        ddl.append(PEDIDO_SITUACAO).append(" TEXT,");
        ddl.append(PEDIDO_VALOR_JUROS).append(" REAL,");
        ddl.append("FOREIGN KEY(").append(PEDIDO_PESSOA_ID).append(") REFERENCES ")
                    .append(PESSOA).append("(").append(PESSOA_ID).append(")");
        ddl.append(");");

        db.execSQL(ddl.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String table : TABLES) {
            db.execSQL(dropTableCommand(table));
        }
        onCreate(db);
    }

    private static String dropTableCommand(String table) {
        return "DROP TABLE IF EXISTS ".concat(table).concat(";");
    }

}
