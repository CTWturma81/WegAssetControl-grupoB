package view;

import controller.UsuarioController;
import enums.PerfilAcesso;
import model.entity.Usuario;

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
            opcao = input.nextInt();

            switch (opcao){
                case 1 -> {
                    Usuario usuario = lerDadosUsuario();
                    usuarioController.cadastrarUsuario(usuario);
                }

                case 2 -> {
                    usuarioController.listarUsuarios();
                }

                case 3 -> {
                    Integer idAtualizarUsuario = lerId();
                    Usuario novoUsuario = lerDadosUsuario();
                    usuarioController.atualizarUsuario(idAtualizarUsuario, novoUsuario);
                }

                case 4 -> {
                    Integer idInativarUsuario = lerId();
                    usuarioController.inativarUsuario(idInativarUsuario);
                }

                case 5 -> {
                    Integer idBuscar = lerId();
                    usuarioController.buscarUsuario(idBuscar);
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public Usuario lerDadosUsuario(){
        System.out.println("Nome: ");
        String nome = input.next();

        System.out.println("Login: ");
        String login = input.next();

        System.out.println("Senha: ");
        String senha = input.next();

        System.out.println("Perfil de acesso (ADMINISTRADOR, SUPERVISOR, TECNICO, OPERADOR): ");
        PerfilAcesso perfil = PerfilAcesso.valueOf(input.next().toUpperCase());

        return new Usuario(nome, login, senha, perfil);
    }

    public Integer lerId(){
        System.out.println("Digite o ID: ");
        return input.nextInt();
    }
}
