package br.com.ufrn.receitagram.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Receita implements Serializable {

    private Long id;
    private String nome;
    private int rendimentoEmPorcoes;
    private String ingredientes;
    private String modoDePreparo;
    private String autorNomeESobrenome;
    private Date dataCriacao;

    public Receita(Long id,
                   String nome,
                   int rendimentoEmPorcoes,
                   String ingredientes,
                   String modoDePreparo,
                   String autorNomeESobrenome,
                   Date dataCriacao){
        this.id = id;
        this.nome = nome;
        this.rendimentoEmPorcoes = rendimentoEmPorcoes;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.autorNomeESobrenome = autorNomeESobrenome;
        this.dataCriacao = dataCriacao;
    }

    public Receita() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRendimentoEmPorcoes() {
        return rendimentoEmPorcoes;
    }

    public void setRendimentoEmPorcoes(int rendimentoEmPorcoes) {
        this.rendimentoEmPorcoes = rendimentoEmPorcoes;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public String getAutorNomeESobrenome() {
        return autorNomeESobrenome;
    }

    public void setAutorNomeESobrenome(String autorNomeESobrenome) {
        this.autorNomeESobrenome = autorNomeESobrenome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}

