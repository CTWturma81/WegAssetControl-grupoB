package view;

import controller.TecnicoController;
import model.entity.Tecnico;

import java.util.Collection;
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
            System.out.print("Opção: ");
            opcao = lerOpcao();

            switch (opcao){
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> editar();
                case 4 -> inativar();
                case 0 -> System.out.println("saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public void cadastrar() {
        Tecnico tecnico = lerDadosTecnicos();
        boolean sucesso = tecnicoController.cadastrarTecnico(tecnico);
        if (sucesso) {
            System.out.println("Tecnico cadastrado com sucesso!");
        }
    }

    public void listar() {
        Collection<Tecnico> tecnicos = tecnicoController.listarTecnico();
        if (tecnicos != null) {
            tecnicos.forEach(System.out::println);
        }
    }

    public void editar() {
        Integer id = lerId();
        Tecnico editarTecnico = lerDadosTecnicos();
        editarTecnico.setId(id);
        boolean sucesso = tecnicoController.editarTecnico(editarTecnico);
        if (sucesso) {
            System.out.println("Tecnico editado com sucesso!");
        }
    }

    public void inativar() {
        Integer id = lerId();
        boolean sucesso = tecnicoController.inativarTecnico(id);
        if (sucesso) {
            System.out.println("Tecnico inativado com sucesso!");
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
        System.out.println("Digite o ID: ");
        while(!input.hasNextInt()){
            System.out.println("Digite um número válido.");
            input.next();
        }
        Integer id = input.nextInt();
        input.nextLine();
        return id;
    }

    private int lerOpcao(){
        while (true){
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Opção inválida. Digite um número.");
                System.out.print("Opção: ");
            }
        }
    }
}