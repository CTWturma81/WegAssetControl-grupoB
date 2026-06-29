package view;

import controller.SetorController;
import model.entity.Setor;

import java.util.Scanner;

public class GerenciarSetores {

    Scanner input = new Scanner(System.in);
    SetorController setorController;

    public GerenciarSetores(SetorController setorController){
        this.setorController = setorController;
    }

    public void menuSetor(){
        int opcao = 0;

        while(opcao != 0){
            System.out.println("1 - Cadastrar Setor");
            System.out.println("2 - Lista Setor");
            System.out.println("3 - Buscar Setor");
            System.out.println("4 - Editar Setor");
            System.out.println("5 - Inativar Setor");
            System.out.println("0 - Sair");
            opcao = input.nextInt();

            switch (opcao){
                case 1 -> setorController.cadastrarSetor();
                case 2 -> setorController.listarSetor();
                case 3 -> setorController.buscarSetor();
                case 4 -> setorController.editarSetor();
                case 5 -> setorController.inativarSetor();
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public Setor lerDadosSetor(){
        System.out.println("Digite o nome do setor: ");
        String nome = input.nextLine();

        System.out.println("Digite o descricao do setor: ");
        String descricao = input.nextLine();

        return new Setor(nome, descricao);
    }

    public Integer lerId(){
        System.out.println("Digite o id do setor: ");
        return input.nextInt();
    }
}
