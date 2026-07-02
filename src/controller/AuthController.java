package controller;

import exception.AppException;
import model.entity.Usuario;
import model.service.AuthService;
import view.LoginView;

public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    public Usuario login(String login, String senha){
        try{
            return authService.login(login, senha);
        } catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void logout(){
        try{
            authService.logout();
        } catch (AppException e){
            System.out.println(e.getMessage());
        }

    }
}
