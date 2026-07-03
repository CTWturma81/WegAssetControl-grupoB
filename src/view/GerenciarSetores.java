package view;

import controller.SetorController;
import model.entity.Setor;

import java.util.Collection;
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
            System.out.print("Opção: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> editar();
                case 5 -> inativar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public void cadastrar() {
        Setor setor = lerDadosSetor();
        boolean sucesso = setorController.cadastrarSetor(setor);
        if (sucesso) {
            System.out.println("Setor cadastrado com sucesso!");
        }
    }

    public void listar() {
        Collection<Setor> setores = setorController.listarSetor();
        if (setores != null) {
            setores.forEach(System.out::println);
        }
    }

    public void buscar() {
        Integer id = lerId();
        Setor setorBusca = setorController.buscarSetor(id);
        if (setorBusca != null) {
            System.out.println("Setor Encontrado!");
            System.out.println(setorBusca);
        }
    }

    public void editar() {
        Integer id = lerId();
        Setor setorEditar = lerDadosSetor();
        setorEditar.setId(id);
        boolean sucesso = setorController.editarSetor(setorEditar);
        if (sucesso) {
            System.out.println("Setor editado com sucesso!");
        }
    }

    public void inativar() {
        Integer idInativar = lerId();
        boolean sucesso = setorController.inativarSetor(idInativar);
        if (sucesso) {
            System.out.println("Setor inativado com sucesso!");
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
        while(!input.hasNextInt()){
            System.out.println("Digite um número válido.");
            input.next();
        }
        Integer id = input.nextInt();
        input.nextLine();
        return id;
    }
}
