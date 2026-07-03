package model.entity;

import exception.AppException;

public class Setor {

    private Integer id;
    private String nome;
    private String descricao;
    private boolean ativo;

    public Setor(String nome, String descricao) {
        setNome(nome);
        setDescricao(descricao);
        this.ativo = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(!nome.isBlank()) {
            this.nome = nome;
        }
        else {
            throw new AppException("ERRO: Nome não pode ser vazio");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(!descricao.isBlank()) {
            this.descricao = descricao;
        }
        else{
            throw new AppException("ERRO: Descrição não pode ser vazia");
        }
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        if (ativo != null) {
            this.ativo = ativo;
        }

        else {
            throw new AppException("ERRO: Ativo não pode ser vazio");
        }

    }

    @Override
    public String toString() {
        return "-----------------------------\n" +
                "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Descrição: " + descricao + "\n" +
                "Status: " + (ativo ? "Ativo" : "Inativo") + "\n" +
                "-----------------------------";
    }
}