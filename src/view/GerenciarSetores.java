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
        int opcao = 1;

        while(opcao != 0){
            System.out.println("1 - Cadastrar Setor");
            System.out.println("2 - Lista Setor");
            System.out.println("3 - Buscar Setor");
            System.out.println("4 - Editar Setor");
            System.out.println("5 - Inativar Setor");
            System.out.println("0 - Sair");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){
                case 1:
                    Setor setor = lerDadosSetor();
                    setorController.cadastrarSetor(setor);
                    break;
                case 2:
                    setorController.listarSetor();
                    break;
                case 3:
                    Integer id = lerId();
                    setorController.buscarSetor(id);
                    break;
                case 4:
                    Setor setorEditar = lerDadosSetor();
                    setorController.editarSetor(setorEditar);
                    break;
                case 5:
                    Integer idInativar = lerId();
                    setorController.inativarSetor(idInativar);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção invalida");
                    break;
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
        Integer id = input.nextInt();
        input.nextLine();

        return id;
    }
}
