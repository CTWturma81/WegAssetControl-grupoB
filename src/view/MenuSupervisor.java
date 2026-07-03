package view;

import util.ConsoleUtils;

import java.util.Scanner;

public class MenuSupervisor {

    private final Scanner scanner;
    private GerenciarSetores gerenciarSetores;
    private AtivoView ativoView;
    private SensorView sensorView;
    private GerenciarTecnicos gerenciarTecnicos;
    private ManutencaoView manutencaoView;

    public MenuSupervisor(Scanner scanner, GerenciarSetores gerenciarSetores, AtivoView ativoView, SensorView sensorView, GerenciarTecnicos gerenciarTecnicos, ManutencaoView manutencaoView) {
        this.gerenciarSetores = gerenciarSetores;
        this.ativoView = ativoView;
        this.sensorView = sensorView;
        this.gerenciarTecnicos = gerenciarTecnicos;
        this.scanner = scanner;
        this.manutencaoView = manutencaoView;
    }

    public boolean menuSupervisor() {
        while (true) {
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> gerenciarSetores.menuSetor();
                case 2 -> ativoView.menuAtivo();
                case 3 -> sensorView.menuSensor();
                case 4 -> avisoEmDesenvolvimento("Gerenciar Alertas");
                case 5 -> gerenciarTecnicos.subMenuTecnico();
                case 6 -> avisoEmDesenvolvimento("Gerenciar Manutenções");
                case 7 -> avisoEmDesenvolvimento("Relatórios");
                case 8 -> {
                    mensagemSucesso("Saindo...");
                    aguardarEnter();
                    return true;
                }
                case 0 -> {
                    return false;
                }
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
        System.out.println(azul + negrito + "  ║ " + branco + "          MENU SUPERVISOR         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Gerenciar Setores             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Gerenciar Ativos              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Gerenciar Sensores            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Gerenciar Alertas             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Gerenciar Tecnicos            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "6 - Gerenciar Manutencoes         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "7 - Relatorios                    " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "8 - Logout                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Encerrar Sistema              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    private int lerOpcao(){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                mensagemErro("Opção inválida. Digite um número.");
                System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            }
        }
    }

    private void avisoEmDesenvolvimento(String nomeModulo){
        System.out.println(ConsoleUtils.BRANCO + "\n  " + nomeModulo + " (em desenvolvimento)" + ConsoleUtils.RESET);
        aguardarEnter();
    }

    private void mensagemSucesso(String texto){
        System.out.println("\u001B[92m  ✔ " + texto + ConsoleUtils.RESET);
    }

    private void mensagemErro(String texto){
        System.out.println("\u001B[91m  ✘ " + texto + ConsoleUtils.RESET);
    }

    private void aguardarEnter(){
        System.out.print(ConsoleUtils.BRANCO + "\n  Pressione ENTER para continuar..." + ConsoleUtils.RESET);
        scanner.nextLine();
    }

}