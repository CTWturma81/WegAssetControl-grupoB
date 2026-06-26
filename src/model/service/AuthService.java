package model.service;

import exception.AppException;
import model.entity.Usuario;
import model.repository.UsuarioRepository;

public class AuthService {

    private UsuarioRepository repository;
    private Usuario usuarioLogado;

    public AuthService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario login(String login, String senha) {
        Usuario usuario = repository.buscarPorLogin(login);

        if (!usuario.getSenha().equals(senha)) {
            throw new AppException("ERRO: Senha incorreta.");
        }

        if (!usuario.getAtivo()) {
            throw new AppException("ERRO: Usuário inativo.");
        }

        usuarioLogado = usuario;
        return usuarioLogado;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void logout() {
        usuarioLogado = null;
    }
}