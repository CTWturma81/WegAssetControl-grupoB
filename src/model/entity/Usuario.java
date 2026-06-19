package model.entity;

import enums.PerfilAcesso;
import exception.AppException;

public class Usuario {

    private final int id;
    private static int idContador = 1;
    private String nome;
    private String login;
    private String senha;
    private PerfilAcesso perfilAcesso;
    private boolean ativo;

    public Usuario(String nome, String login, String senha, PerfilAcesso perfilAcesso, boolean ativo){

        setNome(nome);
        setLogin(login);
        setSenha(senha);
        setPerfil(perfilAcesso);
        this.ativo = ativo;
        this.id = idContador++;
    }

    public long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        if(!nome.isBlank()){
            this.nome = nome;
        }else{
            throw new AppException("ERRO: Nome não pode ser vazio");
        }
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        if(!login.isBlank()){
            this.login = login;
        }else{
            throw new AppException("ERRO: Login não pode ser vazio");
        }
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        if(!senha.isBlank()){
            this.senha = senha;
        }else{
            throw new AppException("ERRO: Senha não pode ser vazia");
        }
    }

    public PerfilAcesso getPerfil() {
        return perfilAcesso;
    }

    public void setPerfil(PerfilAcesso perfilAcesso){
        if(perfilAcesso != null){
            this.perfilAcesso = perfilAcesso;
        }else{
            throw new AppException("ERRO: Perfil invalido");
        }
    }

    public boolean getAtivo(){
        return ativo;
    }

    public void setAtivo(Boolean ativo){
        if(ativo != null) {
            this.ativo = ativo;
        }else{
            throw new AppException("ERRO: Ativo não pode ser nulo");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", perfilAcesso=" + perfilAcesso +
                ", ativo=" + ativo +
                '}';
    }


}
