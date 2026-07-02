package view;

import controller.AuthController;
import model.entity.Usuario;

import java.util.Scanner;

public class SistemaView {

    private AuthController authController;
    private MenuAdministrador menuAdministrador;
    private MenuSupervisor menuSupervisor;
    private MenuTecnico menuTecnico;
    private MenuOperador menuOperador;
    private Scanner scanner;


    public SistemaView(AuthController authController, MenuAdministrador menuAdministrador, MenuSupervisor menuSupervisor, MenuTecnico menuTecnico, MenuOperador menuOperador){
        this.authController = authController;
        this.menuAdministrador = menuAdministrador;
        this.menuSupervisor = menuSupervisor;
        this.menuTecnico = menuTecnico;
        this.menuOperador = menuOperador;
        scanner = new Scanner(System.in);
    }

    public void iniciar(){
        while(true){
            Usuario usuario = telaLogin();

            if(usuario == null){
                continue;
            }

            switch (usuario.getPerfil()){
                case ADMINISTRADOR -> menuAdministrador.menuAdm();
                case SUPERVISOR -> menuSupervisor.menuSupervisor();
                case TECNICO -> menuTecnico.menuTecnico();
                case OPERADOR -> menuOperador.menuOperador();
            }
        }
    }

    private Usuario telaLogin(){
        System.out.println("Digite o seu login: ");
        String nome = scanner.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = scanner.nextLine();

        return authController.login(nome, senha);
    }

}
