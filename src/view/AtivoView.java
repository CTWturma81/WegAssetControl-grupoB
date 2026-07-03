package view;

import controller.AtivoController;
import controller.SetorController;
import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Setor;

import java.util.Collection;
import java.util.Scanner;

public class AtivoView {

    Scanner input = new Scanner(System.in);
    private AtivoController ativoController;
    private SetorController setorController;

    public AtivoView(AtivoController ativoController, SetorController setorController) {
        this.setorController = setorController;
        this.ativoController = ativoController;
    }

    public void menuAtivo(){
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

            switch(opcao){
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
                case 6 -> {
                    listarStatus();
                    aguardarEnter();
                }
                case 7 -> {
                    listarSetor();
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
        System.out.println(azul + negrito + "  ║ " + branco + "        GERENCIAR ATIVOS          " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Cadastrar Ativo               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Listar Ativos                 " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Buscar Ativo                  " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Editar Ativo                  " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Inativar Ativo                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "6 - Listar por Status             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "7 - Listar por Setor              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Voltar                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    public void cadastrar(){
        System.out.print("Digite o nome do ativo: ");
        String nome = input.nextLine();

        System.out.print("Digite o código patrimonial do ativo: ");
        String codigoPatrimonial = input.nextLine();

        System.out.print("Digite o tipo do ativo: ");
        String tipo = input.nextLine();

        System.out.print("Digite a ciclagem do ativo: ");
        String ciclagem = input.nextLine();

        System.out.print("Digite o modelo do ativo: ");
        String modelo = input.nextLine();

        Setor setor = lerSetor();

        boolean sucesso = ativoController.cadastrarAtivoIndustrial(
                new AtivoIndustrial(nome, codigoPatrimonial, tipo, ciclagem, modelo, setor));

        if(sucesso) {
            mensagemSucesso("Ativo cadastrado com sucesso!");
        }
    }
    public void listar(){
        Collection<AtivoIndustrial> ativos = ativoController.listarAtivoIndustrial();
        if (ativos == null || ativos.isEmpty()) {
            mensagemErro("Nenhum ativo cadastrado.");
        } else {
            ativos.forEach(System.out::println);
        }
    }

    public void buscar(){
        System.out.print("Digite o ID do ativo que deseja buscar: ");
        Integer id = input.nextInt();
        input.nextLine();

        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(id);

        if(ativoIndustrial != null) {
            mensagemSucesso("Ativo encontrado!");
            System.out.println(ativoIndustrial);
        } else {
            mensagemErro("Ativo não encontrado.");
        }
    }

    public void editar() {
        System.out.print("Digite o ID do ativo que deseja editar: ");
        Integer idAtivo = input.nextInt();
        input.nextLine();

        System.out.print("Digite o nome do ativo: ");
        String nome = input.nextLine();

        System.out.print("Digite o código patrimonial do ativo: ");
        String codigoPatrimonial = input.nextLine();

        System.out.print("Digite o tipo do ativo: ");
        String tipo = input.nextLine();

        System.out.print("Digite a ciclagem do ativo: ");
        String ciclagem = input.nextLine();

        System.out.print("Digite o modelo do ativo: ");
        String modelo = input.nextLine();

        Setor setor = lerSetor();

        AtivoIndustrial ativoIndustrial = new AtivoIndustrial(nome, codigoPatrimonial, tipo, ciclagem, modelo, setor);
        ativoIndustrial.setId(idAtivo);

        boolean sucesso = ativoController.editarAtivoIndustrial(ativoIndustrial);

        if(sucesso) {
            mensagemSucesso("Ativo editado com sucesso!");
        }
    }

    public void inativar(){
        System.out.print("Digite o ID do ativo que deseja inativar: ");
        Integer id = input.nextInt();
        input.nextLine();

        boolean sucesso = ativoController.inativarAtivoIndustrial(id);

        if(sucesso) {
            mensagemSucesso("Ativo inativado com sucesso!");
        }
    }

    public void listarStatus(){
        StatusAtivo statusAtivo = lerStatus();

        Collection<AtivoIndustrial> ativos = ativoController.listarAtivoIndustrialStatus(statusAtivo);
        if (ativos != null) {
            ativos.forEach(System.out::println);
        }
    }

    public void listarSetor(){
        System.out.print("Digite o ID do setor do ativo: ");
        Integer id = input.nextInt();
        input.nextLine();

        Collection<AtivoIndustrial> ativos = ativoController.listarAtivoIndustrialSetor(id);
        if (ativos != null) {
            ativos.forEach(System.out::println);
        }
    }

    private Setor lerSetor(){
        System.out.print("Digite o ID do setor do ativo: ");
        Integer id = input.nextInt();
        input.nextLine();

        return setorController.buscarSetor(id);
    }

    private StatusAtivo lerStatus(){
        while(true){
            System.out.println("\nStatus do ativo:");
            System.out.println("1 - NORMAL");
            System.out.println("2 - ATENÇÃO");
            System.out.println("3 - CRÍTICO");
            System.out.println("4 - EM MANUTENÇÃO");
            System.out.println("5 - INATIVO");
            System.out.print("Escolha: ");
            String opcao = input.nextLine().trim();

            switch (opcao) {
                case "1": return StatusAtivo.NORMAL;
                case "2": return StatusAtivo.ATENCAO;
                case "3": return StatusAtivo.CRITICO;
                case "4": return StatusAtivo.EM_MANUTENCAO;
                case "5": return StatusAtivo.INATIVO;
                default: mensagemErro("Opção inválida, tente novamente.");
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