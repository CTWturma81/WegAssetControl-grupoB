package model.entity;

import exception.AppException;

public class Setor {

    private static int contadorId = 1;

    private final int id;
    private String nome;
    private String descricao;
    private boolean ativo;

    public Setor(String nome, String descricao) {

        setNome(nome);
        setDescricao(descricao);
        this.id = contadorId++;
        this.ativo = true;
    }

    public int getId() {
        return id;
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

    public String toString() {
        return "Setor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ativo=" + ativo +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}