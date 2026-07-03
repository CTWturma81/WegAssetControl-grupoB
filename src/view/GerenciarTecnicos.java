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
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);

            opcao = lerOpcao();

            switch (opcao){
                case 1 -> {
                    cadastrar();
                    aguardarEnter();
                }
                case 2 -> {
                    listar();
                    aguardarEnter();
                }
                case 3 -> {
                    editar();
                    aguardarEnter();
                }
                case 4 -> {
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
        System.out.println(azul + negrito + "  ║ " + branco + "      GERENCIAR TÉCNICOS          " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Cadastrar Técnico             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Listar Técnicos               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Editar Técnico                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Inativar Técnico              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Voltar                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    public void cadastrar() {
        Tecnico tecnico = lerDadosTecnicos();
        boolean sucesso = tecnicoController.cadastrarTecnico(tecnico);
        if (sucesso) {
            mensagemSucesso("Técnico cadastrado com sucesso!");
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
            mensagemSucesso("Técnico editado com sucesso!");
        }
    }

    public void inativar() {
        Integer id = lerId();
        boolean sucesso = tecnicoController.inativarTecnico(id);
        if (sucesso) {
            mensagemSucesso("Técnico inativado com sucesso!");
        }
    }

    public Tecnico lerDadosTecnicos(){
        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Matrícula: ");
        String matricula = input.nextLine();

        System.out.print("Especialidade: ");
        String especialidade = input.nextLine();

        return new Tecnico(nome, matricula, especialidade);
    }

    public Integer lerId(){
        System.out.print("Digite o ID: ");
        while(!input.hasNextInt()){
            mensagemErro("Digite um número válido.");
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
                mensagemErro("Opção inválida. Digite um número.");
                System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            }
        }
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