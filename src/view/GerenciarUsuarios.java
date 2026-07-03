package view;

import controller.UsuarioController;
import enums.PerfilAcesso;
import model.entity.Usuario;

import java.util.Collection;
import java.util.Scanner;

public class GerenciarUsuarios {

    Scanner input = new Scanner(System.in);
    UsuarioController usuarioController;

    public GerenciarUsuarios(UsuarioController usuarioController){
        this.usuarioController = usuarioController;
    }

    public void subMenuUsuario(){
        int opcao = 1;

        while(opcao != 0){
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);

            while(!input.hasNextInt()){
                mensagemErro("Digite um número válido.");
                input.next();
                System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            }

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){
                case 1 -> {
                    Usuario usuario = lerDadosUsuario();
                    boolean sucesso = usuarioController.cadastrarUsuario(usuario);
                    if (sucesso) {
                        mensagemSucesso("Usuário cadastrado com sucesso!");
                    }
                    aguardarEnter();
                }

                case 2 -> {
                    Collection<Usuario> usuarios = usuarioController.listarUsuarios();
                    if (usuarios != null) {
                        usuarios.forEach(System.out::println);
                    }
                    aguardarEnter();
                }

                case 3 -> {
                    Integer id = lerId();
                    Usuario novoUsuario = lerDadosUsuario();
                    novoUsuario.setId(id);
                    boolean sucesso = usuarioController.atualizarUsuario(novoUsuario);
                    if (sucesso) {
                        mensagemSucesso("Usuário atualizado com sucesso!");
                    }
                    aguardarEnter();
                }

                case 4 -> {
                    Integer idInativarUsuario = lerId();
                    boolean sucesso = usuarioController.inativarUsuario(idInativarUsuario);
                    if (sucesso) {
                        mensagemSucesso("Usuário inativado com sucesso!");
                    }
                    aguardarEnter();
                }

                case 5 -> {
                    Integer idBuscar = lerId();
                    Usuario usuarioBusca = usuarioController.buscarUsuario(idBuscar);
                    if (usuarioBusca != null) {
                        mensagemSucesso("Usuário encontrado!");
                        System.out.println(usuarioBusca);
                    }
                    aguardarEnter();
                }

                case 0 -> mensagemSucesso("Saindo...");

                default -> {
                    mensagemErro("Opção inválida.");
                    aguardarEnter();
                }
            }
        }
    }

    private void exibirMenu(){
        String azul = ConsoleUtils.AZUL_BRILHANTE;
        String branco = ConsoleUtils.BRANCO;
        String reset = ConsoleUtils.RESET;
        String negrito = ConsoleUtils.NEGRITO;

        System.out.println(azul + negrito + "  ╔═══════════════════════════════════╗" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "       GERENCIAR USUÁRIOS         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Cadastrar Usuário             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Listar Usuários               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Atualizar Usuário             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Inativar Usuário              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Buscar Usuário                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Voltar                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    public Usuario lerDadosUsuario(){
        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Login: ");
        String login = input.nextLine();

        System.out.print("Senha: ");
        String senha = input.nextLine();

        PerfilAcesso perfil = lerPerfil();

        return new Usuario(nome, login, senha, perfil);
    }

    public PerfilAcesso lerPerfil(){
        while(true){
            System.out.println("\nPerfil de acesso:");
            System.out.println("1 - ADMINISTRADOR");
            System.out.println("2 - SUPERVISOR");
            System.out.println("3 - TECNICO");
            System.out.println("4 - OPERADOR");
            System.out.print("Escolha: ");
            String opcao = input.nextLine().trim();

            switch (opcao){
                case "1": return PerfilAcesso.ADMINISTRADOR;
                case "2": return PerfilAcesso.SUPERVISOR;
                case "3": return PerfilAcesso.TECNICO;
                case "4": return PerfilAcesso.OPERADOR;
                default: mensagemErro("Opção inválida, tente novamente.");
            }
        }
    }

    public Integer lerId(){
        System.out.print("Digite o ID: ");
        while(!input.hasNextInt()){
            mensagemErro("Digite um número válido.");
            input.next();
        }
        Integer id = input.nextInt();
        input.nextLine();

        return id;
    }

    private void mensagemSucesso(String texto){
        System.out.println("\u001B[92m  ✔ " + texto + ConsoleUtils.RESET);
    }

    private void mensagemErro(String texto){
        System.out.println("\u001B[91m  ✘ " + texto + ConsoleUtils.RESET);
    }

    private void aguardarEnter(){
        System.out.print(ConsoleUtils.BRANCO + "\n  Pressione ENTER para continuar..." + ConsoleUtils.RESET);
        input.nextLine();
    }
}