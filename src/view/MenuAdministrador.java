package view;

import util.ConsoleUtils;

import java.util.Scanner;

public class MenuAdministrador {

    private final Scanner scanner;
    private GerenciarUsuarios gerenciarUsuarios;
    private GerenciarSetores gerenciarSetores;
    private AtivoView ativoView;
    private SensorView sensorView;
    private GerenciarTecnicos gerenciarTecnicos;

    public MenuAdministrador(Scanner scanner, GerenciarUsuarios gerenciarUsuarios, GerenciarSetores gerenciarSetores, AtivoView ativoView, SensorView sensorView, GerenciarTecnicos gerenciarTecnicos) {
        this.scanner = scanner;
        this.gerenciarUsuarios = gerenciarUsuarios;
        this.gerenciarSetores = gerenciarSetores;
        this.ativoView = ativoView;
        this.sensorView = sensorView;
        this.gerenciarTecnicos = gerenciarTecnicos;
    }

    public boolean menuAdm(){
        while(true){
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> gerenciarUsuarios.subMenuUsuario();
                case 2 -> gerenciarSetores.menuSetor();
                case 3 -> ativoView.menuAtivo();
                case 4 -> sensorView.menuSensor();
                case 5 -> avisoEmDesenvolvimento("Gerenciar Alertas");
                case 6 -> gerenciarTecnicos.subMenuTecnico();
                case 7 -> avisoEmDesenvolvimento("Gerenciar Manutenções");
                case 8 -> avisoEmDesenvolvimento("Relatórios");
                case 9 -> {
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
        System.out.println(azul + negrito + "  ║ " + branco + "        MENU ADMINISTRADOR        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Gerenciar Usuarios            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Gerenciar Setores             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Gerenciar Ativos              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Gerenciar Sensores            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Gerenciar Alertas             " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "6 - Gerenciar Tecnicos            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "7 - Gerenciar Manutencoes         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "8 - Relatorios                    " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "9 - Logout                        " + azul + "║" + reset);
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