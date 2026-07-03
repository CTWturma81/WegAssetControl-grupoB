package view;

import controller.AuthController;
import model.entity.Usuario;

import java.util.Scanner;

public class LoginView {

    private Scanner scanner;
    private AuthController authController;

    public LoginView(AuthController authController){
        scanner = new Scanner(System.in);
        this.authController = authController;
    }

    public Usuario telaLogin(){
        System.out.println("Digite o seu login: ");
        String login = scanner.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = authController.login(login, senha);

        if (usuario != null){
            System.out.println("Login realizado com sucesso!");
        }

        return usuario;
    }

    public void telaLogout(){
        authController.logout();
        System.out.println("Logout realizado com sucesso!");
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }

}
