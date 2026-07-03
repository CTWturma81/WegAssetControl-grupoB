package view;

import util.ConsoleUtils;

import java.util.Scanner;

public class MenuTecnico {

    private final Scanner scanner;
    private AtivoView ativoView;
    private SensorView sensorView;
    private ManutencaoView manutencaoView;

    public MenuTecnico(Scanner scanner, AtivoView ativoView, SensorView sensorView, ManutencaoView manutencaoView) {
        this.scanner = scanner;
        this.ativoView = ativoView;
        this.sensorView = sensorView;
        this.manutencaoView = manutencaoView;
    }

    public boolean menuTecnico(){
        while(true){
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> ativoView.menuAtivo();
                case 2 -> sensorView.menuSensor();
                case 3 -> avisoEmDesenvolvimento("Consultar Alertas");
                case 4 -> avisoEmDesenvolvimento("Atualizar Manutenções");
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

        System.out.println(azul + negrito + "  ╔══════════════════════════════════════╗" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "           MENU TECNICO              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠══════════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Consultar Ativos                 " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Consultar Sensores               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Consultar Alertas                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Atualizar Manutencoes Atribuidas " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠══════════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "8 - Logout                           " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Encerrar Sistema                 " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚══════════════════════════════════════╝" + reset);
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