package view;

import controller.UsuarioController;

import java.util.Scanner;

public class GerenciarUsuarios {

    Scanner input = new Scanner(System.in);
    UsuarioController usuarioController;

    public GerenciarUsuarios(UsuarioController usuarioController){
        this.usuarioController = usuarioController;
    }

    public void subMenuUsuario(){
        int opcao = 0;

        while(opcao != 0){
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("2 - Listar Usuario");
            System.out.println("3 - Atualizar Usuario");
            System.out.println("4 - Inativar Usuario");
            System.out.println("5 - Buscar Usuario");
            System.out.println("0 - Sair");
            opcao = input.nextInt();

            switch (opcao){
                case 1 -> usuarioController.cadastrarUsuario();
                case 2 -> usuarioController.listarUsuarios();
                case 3 -> usuarioController.atualizarUsuario();
                case 4 -> usuarioController.inativarUsuario();
                case 5 -> usuarioController.buscarUsuario();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }
}
