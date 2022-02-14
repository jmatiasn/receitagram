package br.com.ufrn.receitagram;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.ufrn.receitagram.dao.ReceitaDAO;
import br.com.ufrn.receitagram.modelo.Receita;

public class AddReceitaActivity extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();

    private EditText etNome;
    private EditText etRendimentoEmPorcoes;
    private EditText etIngredientes;
    private EditText etModoDePreparo;
    private EditText etDataCriacao;
    private String autor = "Usuário";
    private Receita receitaAtual;
    private Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receita);

        etNome = findViewById(R.id.etNome);
        etRendimentoEmPorcoes = findViewById(R.id.etRendimentoEmPorcoes);
        etIngredientes = findViewById(R.id.etIngredientes);
        etModoDePreparo = findViewById(R.id.etModoDePreparo);
        etDataCriacao = findViewById(R.id.etDataCriacao);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        etDataCriacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddReceitaActivity.this,
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH)
                        ,myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        Intent it = getIntent();

        try{
            //edita
            receitaAtual = (Receita) it.getExtras().getSerializable("receita");
        }catch(Exception e){
            //criar um nova
            receitaAtual = null;
        }

        if(receitaAtual != null){
            etNome.setText(receitaAtual.getNome());
            etRendimentoEmPorcoes.setText(String.valueOf(receitaAtual.getRendimentoEmPorcoes()));
            etIngredientes.setText(receitaAtual.getIngredientes());
            etModoDePreparo.setText(receitaAtual.getModoDePreparo());
            etDataCriacao.setText(obterStringDaDate(receitaAtual.getDataCriacao()));
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReceitaDAO receitaDAO = new ReceitaDAO(getApplicationContext());

                if(receitaAtual != null){//editar
                    String nome = etNome.getText().toString();
                    String rendimentoEmPorcoes = etRendimentoEmPorcoes.getText().toString();
                    String ingredientes = etIngredientes.getText().toString();
                    String modoDePreparo = etModoDePreparo.getText().toString();
                    String dataCriacao = etDataCriacao.getText().toString();
                    if(!nome.isEmpty() && !rendimentoEmPorcoes.isEmpty()
                            && !ingredientes.isEmpty() && !modoDePreparo.isEmpty()
                            && !dataCriacao.isEmpty()){
                        receitaAtual.setNome(nome);
                        receitaAtual.setRendimentoEmPorcoes(Integer.valueOf(rendimentoEmPorcoes));
                        receitaAtual.setIngredientes(ingredientes);
                        receitaAtual.setModoDePreparo(modoDePreparo);
                        receitaAtual.setDataCriacao(obterDateDaString(dataCriacao));
                        if(receitaDAO.atualizar(receitaAtual)){
                            Toast.makeText(getApplicationContext(), "Receita atualizada", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else { //adicionar
                    String nome = etNome.getText().toString();
                    String rendimentoEmPorcoes = etRendimentoEmPorcoes.getText().toString();
                    String ingredientes = etIngredientes.getText().toString();
                    String modoDePreparo = etModoDePreparo.getText().toString();
                    Date dataCriacao = obterDateDaString(etDataCriacao.getText().toString());
                    if(!nome.isEmpty() && !rendimentoEmPorcoes.isEmpty()
                            && !ingredientes.isEmpty() && !modoDePreparo.isEmpty()
                            && dataCriacao != null){
                        Receita receita = new Receita();
                        receita.setNome(nome);
                        receita.setRendimentoEmPorcoes(Integer.valueOf(rendimentoEmPorcoes));
                        receita.setIngredientes(ingredientes);
                        receita.setModoDePreparo(modoDePreparo);
                        receita.setAutorNomeESobrenome(autor);
                        receita.setDataCriacao(dataCriacao);
                        if(receitaDAO.salvar(receita)){
                            Toast.makeText(getApplicationContext(), "Receita cadastrada", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao cadastrar receita", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        etDataCriacao.setText(dateFormat.format(myCalendar.getTime()));
    }

    private Date obterDateDaString(String dataRecebida) {
        Date dataFormatada = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataFormatada = formato.parse(dataRecebida);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Erro ao obter data da receita", Toast.LENGTH_SHORT).show();
        }
        return dataFormatada;
    }

    private String obterStringDaDate(Date dataRecebida) {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        return dateFormat.format(dataRecebida);
    }
}
