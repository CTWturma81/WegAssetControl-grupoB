package model.entity;

import exception.AppException;

public class Tecnico {

    private Integer id;
    private String nome;
    private String matricula;
    private String especialidade;
    private boolean ativo;

    public Tecnico(String nome, String matricula, String especialidade) {
        setNome(nome);
        setMatricula(matricula);
        setEspecialidade(especialidade);
        this.ativo = true;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        if(!nome.isBlank()) {
            this.nome = nome;
        } else {
            throw new AppException("ERRO: Nome não pode ser vazio.");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setMatricula(String matricula) {
        if(!matricula.isBlank()) {
            this.matricula = matricula;
        } else {
            throw new AppException("ERRO: Matricula não pode ser vazia.");
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setEspecialidade(String especialidade) {
        if(!especialidade.isBlank()) {
            this.especialidade = especialidade;
        } else {
            throw new AppException("ERRO: Especialidade não pode ser vazia.");
        }
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isAtivo() {
        return ativo;
    }


    @Override
    public String toString() {
        return "Tecnico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", ativo=" + ativo +
                '}';
    }

}
