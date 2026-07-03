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
                ConsoleUtils.telaPadrao();
                exibirMenuInicial();
                System.out.print(ConsoleUtils.BRANCO + "  Escolha uma opção: " + ConsoleUtils.RESET);
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
                    default -> mensagemErro("Opção inválida.");
                }

            } catch (RuntimeException e){
                mensagemErro("Erro inesperado no sistema: " + e.getMessage());
                aguardarEnter();
            }
        }

        ConsoleUtils.telaPadrao();
        System.out.println(ConsoleUtils.AZUL_BRILHANTE + ConsoleUtils.NEGRITO +
                "                     Sistema encerrado. Até logo!\n" + ConsoleUtils.RESET);
    }

    private void exibirMenuInicial(){
        String azul = ConsoleUtils.AZUL_BRILHANTE;
        String branco = ConsoleUtils.BRANCO;
        String reset = ConsoleUtils.RESET;
        String negrito = ConsoleUtils.NEGRITO;

        System.out.println(azul + negrito + "  ╔═══════════════════════════════════╗" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "           BEM-VINDO              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Login                         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Cadastrar usuário             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Sair                          " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    private void cadastrarUsuario(){
        ConsoleUtils.telaPadrao();
        System.out.println(ConsoleUtils.AZUL_BRILHANTE + ConsoleUtils.NEGRITO + "  === CADASTRO DE USUÁRIO ===\n" + ConsoleUtils.RESET);

        System.out.print(ConsoleUtils.BRANCO + "  Nome: " + ConsoleUtils.RESET);
        String nome = scanner.nextLine();

        System.out.print(ConsoleUtils.BRANCO + "  Login: " + ConsoleUtils.RESET);
        String login = scanner.nextLine();

        System.out.print(ConsoleUtils.BRANCO + "  Senha: " + ConsoleUtils.RESET);
        String senha = scanner.nextLine();

        PerfilAcesso perfil = lerPerfil();

        try {
            usuarioController.cadastrarUsuario(new Usuario(nome, login, senha, perfil));
            mensagemSucesso("Usuário cadastrado com sucesso! Faça login para continuar.");
        } catch (AppException e){
            mensagemErro("Erro ao cadastrar: " + e.getMessage());
        }
        aguardarEnter();
    }

    private PerfilAcesso lerPerfil(){
        while(true){
            System.out.println(ConsoleUtils.BRANCO + "\n  Perfil:" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BRANCO + "  1 - ADMINISTRADOR" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BRANCO + "  2 - SUPERVISOR" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BRANCO + "  3 - TECNICO" + ConsoleUtils.RESET);
            System.out.println(ConsoleUtils.BRANCO + "  4 - OPERADOR" + ConsoleUtils.RESET);
            System.out.print(ConsoleUtils.BRANCO + "  Escolha: " + ConsoleUtils.RESET);
            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return PerfilAcesso.ADMINISTRADOR;
                case "2": return PerfilAcesso.SUPERVISOR;
                case "3": return PerfilAcesso.TECNICO;
                case "4": return PerfilAcesso.OPERADOR;
                default: mensagemErro("Opção inválida, tente novamente.");
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
        ConsoleUtils.telaPadrao();
        System.out.println(ConsoleUtils.AZUL_BRILHANTE + ConsoleUtils.NEGRITO + "  === LOGIN ===\n" + ConsoleUtils.RESET);

        System.out.print(ConsoleUtils.BRANCO + "  Digite o seu login: " + ConsoleUtils.RESET);
        String login = scanner.nextLine();

        System.out.print(ConsoleUtils.BRANCO + "  Digite a sua senha: " + ConsoleUtils.RESET);
        String senha = scanner.nextLine();

        return authController.login(login, senha);
    }

    public boolean perguntarTentarNovamente(){
        mensagemErro("Login ou senha inválidos.");
        System.out.print(ConsoleUtils.BRANCO + "  Deseja tentar novamente? (S/N): " + ConsoleUtils.RESET);
        String resposta = scanner.nextLine().trim();

        while(!resposta.equalsIgnoreCase("S") && !resposta.equalsIgnoreCase("N")){
            System.out.print(ConsoleUtils.BRANCO + "  Opção inválida. Digite S para tentar novamente ou N para encerrar: " + ConsoleUtils.RESET);
            resposta = scanner.nextLine().trim();
        }

        return resposta.equalsIgnoreCase("S");
    }

    private void mensagemSucesso(String texto){
        System.out.println("\u001B[92m  ✔ " + texto + ConsoleUtils.RESET);
    }

    private void mensagemErro(String texto){
        System.out.println("\u001B[91m  ✘ " + texto + ConsoleUtils.RESET);
    }

    private void aguardarEnter(){
        System.out.print(ConsoleUtils.BRANCO + "\n  Pressione ENTER para continuar..." + ConsoleUtils.RESET);
        scanner.nextLine();
    }

}