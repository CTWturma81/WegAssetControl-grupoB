package view;

import controller.AtivoController;
import controller.ManutencaoController;
import controller.TecnicoController;
import model.entity.AtivoIndustrial;
import model.entity.Manutencao;
import model.entity.Tecnico;

import java.util.Collection;
import java.util.Scanner;

public class ManutencaoView {

    Scanner input = new Scanner(System.in);
    private ManutencaoController manutencaoController;
    private TecnicoController tecnicoController;
    private AtivoController ativoController;

    public ManutencaoView(ManutencaoController manutencaoController, TecnicoController tecnicoController, AtivoController ativoController){
        this.manutencaoController = manutencaoController;
        this.tecnicoController = tecnicoController;
        this.ativoController = ativoController;
    }

    public void menuManutencao(){
        int opcao = 1;

        while(opcao != 0){
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);

            opcao = lerOpcao();

            switch (opcao){
                case 1 -> {
                    abrir();
                    aguardarEnter();
                }
                case 2 -> {
                    atribuirManutencao();
                    aguardarEnter();
                }
                case 3 -> {
                    registrar();
                    aguardarEnter();
                }
                case 4 -> {
                    finalizar();
                    aguardarEnter();
                }
                case 5 -> {
                    listarAbertas();
                    aguardarEnter();
                }
                case 6 -> {
                    listarPorTecnico();
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
        System.out.println(azul + negrito + "  ║ " + branco + "     GERENCIAR MANUTENÇÕES        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Abrir Manutenção              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Atribuir Técnico              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Registrar Observação          " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Finalizar Manutenção          " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Listar Abertas                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "6 - Listar por Técnico            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Voltar                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    public void abrir() {
        Integer idAtivo = lerId("ativo industrial");
        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(idAtivo);

        System.out.print("Insira a descrição do problema: ");
        String descricao = input.nextLine();

        Integer idTecnico = lerId("técnico");
        Tecnico tecnico = tecnicoController.buscarTecnico(idTecnico);

        Manutencao manutencao = new Manutencao(ativoIndustrial, tecnico, descricao);

        boolean sucesso = manutencaoController.abrirManutencao(manutencao);

        if (sucesso) {
            mensagemSucesso("Manutenção aberta com sucesso!");
        }
    }

    public void atribuirManutencao(){
        Integer idManutencao = lerId("manutenção");
        Integer idTecnico = lerId("tecnico");

        Tecnico tecnico = tecnicoController.buscarTecnico(idTecnico);

        boolean sucesso = manutencaoController.atribuirTecnicoManutencao(idManutencao, tecnico);
        if (sucesso) {
            mensagemSucesso("Técnico atribuído com sucesso!");
        }
    }

    public void registrar(){
        Integer idManutencao = lerId("manutenção");

        System.out.print("Insira a observação técnica: ");
        String observacao = input.nextLine();

        boolean sucesso = manutencaoController.registrarObservacao(idManutencao, observacao);
        if (sucesso) {
            mensagemSucesso("Observação registrada com sucesso!");
        }
    }

    public void finalizar(){
        Integer idManutencao = lerId("manutenção");

        boolean sucesso = manutencaoController.finalizarManutencao(idManutencao);
        if (sucesso) {
            mensagemSucesso("Manutenção finalizada com sucesso!");
        }
    }

    public void listarAbertas(){
        Collection<Manutencao> manutencoes = manutencaoController.listarManutencaoAberta();
        if (manutencoes != null) {
            manutencoes.forEach(System.out::println);
        }
    }

    public void listarPorTecnico(){
        Integer idTecnico = lerId("tecnico");

        Collection<Manutencao> manutencoes = manutencaoController.listarManutencaoTecnico(idTecnico);
        if (manutencoes != null) {
            manutencoes.forEach(System.out::println);
        }
    }

    private Integer lerId(String entidade){
        System.out.print("Digite o ID do " + entidade + ": ");
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