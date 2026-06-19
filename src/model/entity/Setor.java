package model.entity;

import exception.AppException;

public class Setor {

    private Integer id;
    private String nome;
    private String descricao;

    public Setor(String nome, String descricao) {
        setNome(nome);
        setDescricao(descricao);
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

    public String toString() {
        return "Setor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}