package br.com.dgstecnologia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "loja";
    public static final String TBL_CIDADE = "cidade";

    public DB(Context context){
        super(context, NOME_BANCO, null, 1);
    }

    public static final String SCRIPT_TBL_CIDADE  = " create table cidade("+
            " id string not null primary key, "+
            " nome text, "+
            " uf text not null);";

    public static final String SCRIPT_POPULA_BASE_CIDADE = new StringBuilder().append(" ").
            append(" insert into cidade(id, nome, uf) ").
            append(" values( '010102', 'maringa', 'pr' ); ").toString();

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_TBL_CIDADE);
        sqLiteDatabase.execSQL(SCRIPT_POPULA_BASE_CIDADE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
