package view;

import controller.AuthController;
import controller.UsuarioController;
import model.entity.Usuario;
import enums.PerfilAcesso;
import exception.AppException;

import java.util.Scanner;

public class SistemaView {

    private final Scanner scanner;
    private final AuthController authController;
    private final UsuarioController usuarioController;
    private final MenuAdministrador menuAdministrador;
    private final MenuSupervisor menuSupervisor;
    private final MenuTecnico menuTecnico;
    private final MenuOperador menuOperador;

    public SistemaView(Scanner scanner, AuthController authController, UsuarioController usuarioController, MenuAdministrador menuAdministrador, MenuSupervisor menuSupervisor, MenuTecnico menuTecnico, MenuOperador menuOperador){
        this.scanner = scanner;
        this.authController = authController;
        this.usuarioController = usuarioController;
        this.menuAdministrador = menuAdministrador;
        this.menuSupervisor = menuSupervisor;
        this.menuTecnico = menuTecnico;
        this.menuOperador = menuOperador;
    }

    public void iniciar(){
        boolean sistemaAtivo = true;

        while(sistemaAtivo){
            try {
                System.out.println("\n=== BEM-VINDO ===");
                System.out.println("1 - Login");
                System.out.println("2 - Cadastrar usuário");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine().trim();

                switch (opcao){
                    case "1" -> {
                        Usuario usuario = telaLogin();

                        if(usuario == null){
                            sistemaAtivo = perguntarTentarNovamente();
                        } else {
                            sistemaAtivo = direcionarMenu(usuario);
                        }
                    }
                    case "2" -> cadastrarUsuario();
                    case "0" -> sistemaAtivo = false;
                    default -> System.out.println("Opção inválida.");
                }

            } catch (RuntimeException e){
                System.out.println("Erro inesperado no sistema: " + e.getMessage());
            }
        }

        System.out.println("Sistema encerrado. Ate logo!");
    }

    private void cadastrarUsuario(){
        System.out.println("=== CADASTRO DE USUÁRIO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        PerfilAcesso perfil = lerPerfil();

        try {
            usuarioController.cadastrarUsuario(new Usuario(nome, login, senha, perfil));
            System.out.println("Usuário cadastrado com sucesso! Faça login para continuar.");
        } catch (AppException e){
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private PerfilAcesso lerPerfil(){
        while(true){
            System.out.println("Perfil:");
            System.out.println("1 - ADMINISTRADOR");
            System.out.println("2 - SUPERVISOR");
            System.out.println("3 - TECNICO");
            System.out.println("4 - OPERADOR");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return PerfilAcesso.ADMINISTRADOR;
                case "2": return PerfilAcesso.SUPERVISOR;
                case "3": return PerfilAcesso.TECNICO;
                case "4": return PerfilAcesso.OPERADOR;
                default: System.out.println("Opção inválida, tente novamente.");
            }
        }
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