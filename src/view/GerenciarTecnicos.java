package view;

import controller.TecnicoController;
import model.entity.Tecnico;

import java.util.Scanner;

public class GerenciarTecnicos {

    Scanner input = new Scanner(System.in);
    TecnicoController tecnicoController;

    public GerenciarTecnicos(TecnicoController tecnicoController){
        this.tecnicoController = tecnicoController;
    }

    public void subMenuTecnico(){
        int opcao = 0;

        while(opcao != 0){
            System.out.println("1 - Cadastrar técnicos");
            System.out.println("2 - Listar técnicos");
            System.out.println("3 - Editar técnicos");
            System.out.println("4 - Inativar técnicos");
            System.out.println("0 - sair");
            opcao = input.nextInt();

            switch (opcao){
              case 1 -> tecnicoController.cadastrarTecnico();
              case 2 -> tecnicoController.listarTecnico();
              case 3 -> tecnicoController.editarTecnico();
              case 4 -> tecnicoController.inativarTecnicos();
              case 0 -> System.out.println("saindo...");
              default -> System.out.println("Opção invalida");
            }
        }
    }

    public Tecnico lerDadosTecnicos(){
        System.out.println();
        String nome = input.nextLine();

        System.out.println();
        String matricula = input.nextLine();

        System.out.println();
        String especialidade = input.nextLine();

        return new Tecnico(nome, matricula, especialidade);
    }

    public Integer lerId(){
        System.out.println("digite seu id");
        return input.nextInt();
    }
}
