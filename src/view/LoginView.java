package view;

import java.util.Scanner;

public class LoginView {

    private Scanner scanner;

    public LoginView(){
        scanner = new Scanner(System.in);
    }

    public String lerLogin(){
        System.out.println("Digite o seu login: ");
        return scanner.nextLine();
    }

    public String lerSenha(){
        System.out.println("Digite a sua senha: ");
        return scanner.nextLine();
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }

}
