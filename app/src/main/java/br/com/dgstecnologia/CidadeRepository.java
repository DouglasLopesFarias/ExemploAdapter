package br.com.dgstecnologia;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CidadeRepository implements IPadraoRepository {

    private static final String[] FLD_BAIRRO = {"id", "descricao"};
    public SQLiteDatabase db;
    public static CidadeRepository instance = new CidadeRepository();

    public static CidadeRepository getInstance(Context context){
        if(instance.db == null || instance.db.isOpen()){
            instance.db = new DB(context).getWritableDatabase();
        }
        return  instance;
    }


    @Override
    public void inserir(Object o) {
        Cidade cidade = (Cidade)o;
        long status = -1;
        db.beginTransaction();
        try{
            ContentValues cv = new ContentValues();
            cv.put("id", UUID.randomUUID().toString());
            cv.put("nome", cidade.getNome());
            cv.put("uf", cidade.getUf());
            status = db.insert(DB.TBL_CIDADE, null, cv);
            db.setTransactionSuccessful();
        }finally {
            db.endTransaction();
        }
    }

    @Override
    public void alterar(Object o) {
        Cidade cidade = (Cidade)o;
        db.beginTransaction();
        try{
            ContentValues cv = new ContentValues();
            cv.put("id", cidade.getCodigo());
            cv.put("nome", cidade.getNome());
            cv.put("uf", cidade.getUf());
            db.update(DB.TBL_CIDADE, cv, "id=?", new String[]{cidade.getCodigo()});
            db.setTransactionSuccessful();
        }catch(Exception e){
            Log.e("Erro: ", e.getMessage());
        }
        finally {
            db.endTransaction();
        }
    }

    @Override
    public void remover(Object o) {
        Cidade cidade = (Cidade)o;
        db.beginTransaction();
        try{
            db.delete(DB.TBL_CIDADE, "id=?", new String[] {String.valueOf(cidade.getCodigo())});
            db.setTransactionSuccessful();;
        }finally {
            db.endTransaction();
        }

    }

    private Cidade carregar(Cursor c){
        @SuppressLint("Range") String codigo = c.getString(c.getColumnIndex("id"));
        @SuppressLint("Range") String nome = c.getString(c.getColumnIndex("nome"));
        @SuppressLint("Range") String uf = c.getString(c.getColumnIndex("uf"));
        Cidade cidade = new Cidade(codigo, nome,uf);
        return cidade;
    }


    @Override
    public ArrayList getAll() {
        List<Cidade> lista = new ArrayList<Cidade>();
        Cursor c = db.query(DB.TBL_CIDADE, null,null,null,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            Cidade cidade = carregar(c);
            lista.add(cidade);
            c.moveToNext();
        }
        return  new ArrayList(lista);
    }

    @Override
    public Object getById(int id) {
        return null;
    }
}
