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

            switch (opcao) {
                case 1 -> {
                    cadastrar();
                    aguardarEnter();
                }
                case 2 -> {
                    listar();
                    aguardarEnter();
                }
                case 3 -> {
                    buscar();
                    aguardarEnter();
                }
                case 4 -> {
                    editar();
                    aguardarEnter();
                }
                case 5 -> {
                    inativar();
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
        System.out.println(azul + negrito + "  ║ " + branco + "        GERENCIAR SETORES         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Cadastrar Setor               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Listar Setores                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Buscar Setor                  " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Editar Setor                  " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Inativar Setor                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Voltar                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    public void cadastrar() {
        Setor setor = lerDadosSetor();
        boolean sucesso = setorController.cadastrarSetor(setor);
        if (sucesso) {
            mensagemSucesso("Setor cadastrado com sucesso!");
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
            mensagemSucesso("Setor encontrado!");
            System.out.println(setorBusca);
        }
    }

    public void editar() {
        Integer id = lerId();
        Setor setorEditar = lerDadosSetor();
        setorEditar.setId(id);
        boolean sucesso = setorController.editarSetor(setorEditar);
        if (sucesso) {
            mensagemSucesso("Setor editado com sucesso!");
        }
    }

    public void inativar() {
        Integer idInativar = lerId();
        boolean sucesso = setorController.inativarSetor(idInativar);
        if (sucesso) {
            mensagemSucesso("Setor inativado com sucesso!");
        }
    }

    public Setor lerDadosSetor(){
        System.out.print("Digite o nome do setor: ");
        String nome = input.nextLine();

        System.out.print("Digite a descrição do setor: ");
        String descricao = input.nextLine();

        return new Setor(nome, descricao);
    }

    public Integer lerId(){
        System.out.print("Digite o ID do setor: ");
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