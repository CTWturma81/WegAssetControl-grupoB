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
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("2 - Listar Usuario");
            System.out.println("3 - Atualizar Usuario");
            System.out.println("4 - Inativar Usuario");
            System.out.println("5 - Buscar Usuario");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){
                case 1 -> {
                    Usuario usuario = lerDadosUsuario();
                    boolean sucesso = usuarioController.cadastrarUsuario(usuario);
                    if (sucesso) {
                        System.out.println("Usuário cadastrado com sucesso!");
                    }
                }

                case 2 -> {
                    Collection<Usuario> usuarios = usuarioController.listarUsuarios();
                    if (usuarios != null) {
                        usuarios.forEach(System.out::println);
                    }
                }

                case 3 -> {
                    Integer id = lerId();
                    Usuario novoUsuario = lerDadosUsuario();
                    novoUsuario.setId(id);
                    boolean sucesso = usuarioController.atualizarUsuario(novoUsuario);
                    if (sucesso) {
                        System.out.println("Usuário atualizado com sucesso!");
                    }
                }

                case 4 -> {
                    Integer idInativarUsuario = lerId();
                    boolean sucesso = usuarioController.inativarUsuario(idInativarUsuario);
                    if (sucesso) {
                        System.out.println("Usuário inativado com sucesso!");
                    }
                }

                case 5 -> {
                    Integer idBuscar = lerId();
                    Usuario usuarioBusca = usuarioController.buscarUsuario(idBuscar);
                    if (usuarioBusca != null) {
                        System.out.println("Usuário Encontrado!");
                        System.out.println(usuarioBusca);
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public Usuario lerDadosUsuario(){
        System.out.println("Nome: ");
        String nome = input.nextLine();

        System.out.println("Login: ");
        String login = input.nextLine();

        System.out.println("Senha: ");
        String senha = input.nextLine();

        PerfilAcesso perfil = lerPerfil();

        return new Usuario(nome, login, senha, perfil);
    }

    public PerfilAcesso lerPerfil(){
        while(true){
            System.out.println("Perfil de acesso:");
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
                default: System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public Integer lerId(){
        System.out.println("Digite o ID: ");
        while(!input.hasNextInt()){
            System.out.println("Digite um número válido.");
            input.next();
        }
        Integer id = input.nextInt();
        input.nextLine();

        return id;
    }
}