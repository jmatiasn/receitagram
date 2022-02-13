package br.com.ufrn.receitagram.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.ufrn.receitagram.modelo.Receita;
import br.com.ufrn.receitagram.utils.DBHelper;

public class ReceitaDAO {
    private final SQLiteDatabase escreve;
    private final SQLiteDatabase le;

    public ReceitaDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
        dbHelper.onCreate(escreve);
    }

    public boolean salvar(Receita receita){

        //1. definir o conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("nome", receita.getNome());
        cv.put("rendimentoEmPorcoes", receita.getRendimentoEmPorcoes());
        cv.put("ingredientes", receita.getIngredientes());
        cv.put("modoDePreparo", receita.getModoDePreparo());
        cv.put("autorNomeESobrenome", receita.getAutorNomeESobrenome());
        cv.put("dataCriacao", receita.getDataCriacao().toString());

        try{
            escreve.insert(DBHelper.TABELA_RECEITAS,null,cv);
            Log.i("INFO","Registro salvo com sucesso!");
        }catch(Exception e){
            Log.i("INFO","Erro ao salvar registro: "+e.getMessage());
            return false;
        }
        return true;
    }

    public List<Receita> listar() {

        List<Receita> notas = new ArrayList<>();

        //1. string sql de consulta
        String sql = "SELECT * FROM "+DBHelper.TABELA_RECEITAS+ ";";

        //2. Cursor para acesso aos dados
        Cursor c = le.rawQuery(sql,null);

        //3. percorrer o cursor
        c.moveToFirst();
        while(c.moveToNext()){

            Receita receita = new Receita();

            //Long id = c.getLong( 0 );
            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String nome = c.getString(c.getColumnIndexOrThrow("nome"));
            int rendimentoEmPorcoes = c.getInt(c.getColumnIndexOrThrow("rendimentoEmPorcoes"));
            String ingredientes = c.getString(c.getColumnIndexOrThrow("ingredientes"));
            String modoDePreparo = c.getString(c.getColumnIndexOrThrow("modoDePreparo"));
            String autorNomeESobrenome = c.getString(c.getColumnIndexOrThrow("autorNomeESobrenome"));
            String dataCriacao = c.getString(c.getColumnIndexOrThrow("dataCriacao"));

            //"Tue Jan 25 21:15:40 GMT-03:00 2022"
            SimpleDateFormat formato = new SimpleDateFormat("E MMM dd HH:mm:ss 'GMT'Z yyyy");
            Date dataCriacaoFormatada = null;

            try {
                dataCriacaoFormatada = formato.parse(dataCriacao);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            receita.setId(id);
            receita.setNome(nome);
            receita.setRendimentoEmPorcoes(rendimentoEmPorcoes);
            receita.setIngredientes(ingredientes);
            receita.setModoDePreparo(modoDePreparo);
            receita.setAutorNomeESobrenome(autorNomeESobrenome);
            receita.setDataCriacao(dataCriacaoFormatada);

            notas.add(receita);
        }
        c.close();
        return notas;
    }

    public boolean atualizar(Receita receita){

        //1. definir conteudo a ser salvo
        ContentValues cv = new ContentValues();
        cv.put("nome", receita.getNome());
        cv.put("rendimentoEmPorcoes", receita.getRendimentoEmPorcoes());
        cv.put("ingredientes", receita.getIngredientes());
        cv.put("modoDePreparo", receita.getModoDePreparo());
        cv.put("autorNomeESobrenome", receita.getAutorNomeESobrenome());
        cv.put("dataCriacao", receita.getDataCriacao().toString());

        //2. atualizar valor no banco
        try{
            String[] args = {receita.getId().toString()};
            //2.1 update(nome da tabela, conteudo para atualizar, clausula de atualização (where)
            // o argumento da condição --> ?)
            escreve.update(DBHelper.TABELA_RECEITAS,cv,"id=?",args);
            Log.i("INFO","Registro atualizado com sucesso!");
        }catch(Exception e){
            Log.i("INFO","Erro ao atualizar registro!" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deletar(Receita receita){

        //1. deletar um registro de nota na tabela tarefas

        try{
            //id do registro que será deletado
            String[] args = {receita.getId().toString()};
            escreve.delete(DBHelper.TABELA_RECEITAS,"id=?",args);
            Log.i("INFO","Registro apagado com sucesso!");
        }catch(Exception e){
            Log.i("INFO","Erro ao apagar registro!"+e.getMessage());
            return false;
        }
        return true;

    }
}
