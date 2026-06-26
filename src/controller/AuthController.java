package controller;

import exception.AppException;
import model.entity.Usuario;
import model.service.AuthService;
import view.LoginView;

public class AuthController {

    private LoginView loginView;
    private AuthService authService;

    public AuthController(LoginView loginView, AuthService authService){
        this.loginView = loginView;
        this.authService = authService;
    }

    public Usuario login(){
        try{
            String login = loginView.lerLogin();
            String senha = loginView.lerSenha();

            return authService.login(login, senha);
        } catch (AppException e){
            loginView.exibirMensagem(e.getMessage());
            return null;
        }
    }

    public void logout(){
        authService.logout();
        loginView.exibirMensagem("Logout realizado com sucesso!");
    }
}
