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
        int opcao = 1;

        while(opcao != 0){
            System.out.println("1 - Cadastrar técnicos");
            System.out.println("2 - Listar técnicos");
            System.out.println("3 - Editar técnicos");
            System.out.println("4 - Inativar técnicos");
            System.out.println("0 - sair");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){

              case 1 -> {
                  Tecnico tecnico = lerDadosTecnicos();
                  tecnicoController.cadastrarTecnico(tecnico);
              }

              case 2 -> tecnicoController.listarTecnico();

              case 3 -> {
                  Integer id = lerId();
                  Tecnico editarTecnico = lerDadosTecnicos();
                  tecnicoController.editarTecnico(id, editarTecnico);
              }

              case 4 -> {
                  Integer id = lerId();
                  tecnicoController.inativarTecnicos(id);
              }
              case 0 -> System.out.println("saindo...");
              default -> System.out.println("Opção invalida");
            }
        }
    }

    public Tecnico lerDadosTecnicos(){
        System.out.println("Nome: ");
        String nome = input.nextLine();

        System.out.println("Matricula: ");
        String matricula = input.nextLine();

        System.out.println("Especialidade: ");
        String especialidade = input.nextLine();

        return new Tecnico(nome, matricula, especialidade);
    }

    public Integer lerId(){
        System.out.println("digite seu id");
        Integer id = input.nextInt();
        input.nextLine();
        return id;
    }
}
