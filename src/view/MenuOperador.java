package view;

import java.util.Scanner;

public class MenuOperador {

    private final Scanner scanner;
    private AtivoView ativoView;
    private GerenciarSetores gerenciarSetores;
    private AlertaView alertaView;

    public MenuOperador(Scanner scanner, AtivoView ativoView, GerenciarSetores gerenciarSetores, AlertaView alertaView) {
        this.scanner = scanner;
        this.ativoView = ativoView;
        this.gerenciarSetores = gerenciarSetores;
        this.alertaView = alertaView;
    }

    public boolean menuOperador(){
        while(true){
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> ativoView.menuAtivo();
                case 2 -> gerenciarSetores.menuSetor();
                case 3 -> alertaView.menuAlerta();
                case 4 -> {
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
        System.out.println(azul + negrito + "  ║ " + branco + "           MENU OPERADOR            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Consultar Ativos               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Consultar Setores              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Consultar Alertas              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Logout                         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Encerrar Sistema               " + azul + "║" + reset);
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