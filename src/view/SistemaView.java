package view;

import controller.AuthController;
import model.entity.Usuario;

import java.util.Scanner;

public class SistemaView {

    private final Scanner scanner;
    private final AuthController authController;
    private final MenuAdministrador menuAdministrador;
    private final MenuSupervisor menuSupervisor;
    private final MenuTecnico menuTecnico;
    private final MenuOperador menuOperador;

    public SistemaView(Scanner scanner, AuthController authController, MenuAdministrador menuAdministrador, MenuSupervisor menuSupervisor, MenuTecnico menuTecnico, MenuOperador menuOperador){
        this.scanner = scanner;
        this.authController = authController;
        this.menuAdministrador = menuAdministrador;
        this.menuSupervisor = menuSupervisor;
        this.menuTecnico = menuTecnico;
        this.menuOperador = menuOperador;
    }

    public void iniciar(){
        boolean sistemaAtivo = true;

        while(sistemaAtivo){
            try {
                Usuario usuario = telaLogin();

                if(usuario == null){
                    sistemaAtivo = perguntarTentarNovamente();
                    continue;
                }

                sistemaAtivo = direcionarMenu(usuario);

            } catch (RuntimeException e){
                System.out.println("Erro inesperado no sistema: " + e.getMessage());
            }
        }

        System.out.println("Sistema encerrado. Ate logo!");
    }

    private boolean direcionarMenu(Usuario usuario){
        return switch (usuario.getPerfil()){
            case ADMINISTRADOR -> menuAdministrador.menuAdm();
            case SUPERVISOR -> menuSupervisor.menuSupervisor();
            case TECNICO -> menuTecnico.menuTecnico();
            case OPERADOR -> menuOperador.menuOperador();
        };
    }

    public Usuario telaLogin(){
        System.out.println("=== LOGIN ===");
        System.out.print("Digite o seu login: ");
        String login = scanner.nextLine();

        System.out.print("Digite a sua senha: ");
        String senha = scanner.nextLine();

        return authController.login(login, senha);
    }

    public boolean perguntarTentarNovamente(){
        System.out.print("Login ou senha invalidos. Deseja tentar novamente? (S/N): ");
        String resposta = scanner.nextLine().trim();

        while(!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")){
            System.out.print("Opcao invalida. Digite S para tentar novamente ou N para encerrar: ");
            resposta = scanner.nextLine().trim();
        }

        return resposta.equalsIgnoreCase("S");
    }

}