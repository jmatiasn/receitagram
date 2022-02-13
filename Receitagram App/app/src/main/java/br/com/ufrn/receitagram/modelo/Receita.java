package br.com.ufrn.receitagram.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Receita implements Serializable {

    private Long id;
    private Long idPessoaAutor;
    private String nome;
    private int rendimentoEmPorcoes;
    private String[] ingredientes;
    private String[] modoDePreparo;
    private Date dataCriacao;

    public Receita(Long id,
                   Long idPessoaAutor,
                   String nome,
                   int rendimentoEmPorcoes,
                   String[] ingredientes,
                   String[] modoDePreparo,
                   Date dataCriacao){
        this.id = id;
        this.idPessoaAutor = idPessoaAutor;
        this.nome = nome;
        this.rendimentoEmPorcoes = rendimentoEmPorcoes;
        this.ingredientes = ingredientes;
        this.modoDePreparo = modoDePreparo;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPessoaAutor() {
        return idPessoaAutor;
    }

    public void setIdPessoaAutor(Long idPessoaAutor) {
        this.idPessoaAutor = idPessoaAutor;
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

    public String[] getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String[] ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String[] getModoDePreparo() {
        return modoDePreparo;
    }

    public void setModoDePreparo(String[] modoDePreparo) {
        this.modoDePreparo = modoDePreparo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}

