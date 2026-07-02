package view;

import java.util.Scanner;

public class MenuSupervisor {

    private final Scanner scanner;
    private GerenciarSetores gerenciarSetores;
    private AtivoView ativoView;
    private SensorView sensorView;
    private GerenciarTecnicos gerenciarTecnicos;

    public MenuSupervisor(Scanner scanner, GerenciarSetores gerenciarSetores, AtivoView ativoView, SensorView sensorView, GerenciarTecnicos gerenciarTecnicos) {
        this.gerenciarSetores = gerenciarSetores;
        this.ativoView = ativoView;
        this.sensorView = sensorView;
        this.gerenciarTecnicos = gerenciarTecnicos;
        this.scanner = scanner;
    }

    public boolean menuSupervisor() {
        while (true) {
            System.out.println("\n=== MENU SUPERVISOR ===");
            System.out.println("1 - Gerenciar Setores");
            System.out.println("2 - Gerenciar Ativos");
            System.out.println("3 - Gerenciar Sensores");
            System.out.println("4 - Gerenciar Alertas");
            System.out.println("5 - Gerenciar Tecnicos");
            System.out.println("6 - Gerenciar Manutencoes");
            System.out.println("7 - Relatorios");
            System.out.println("8 - Logout");
            System.out.println("0 - Encerrar Sistema");
            System.out.print("Opcao: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> gerenciarSetores.menuSetor();
                case 2 -> ativoView.menuAtivo();
                case 3 -> sensorView.menuSensor();
                case 4 -> System.out.println("Gerenciar Alertas");
                case 5 -> gerenciarTecnicos.subMenuTecnico();
                case 6 -> System.out.println("Gerenciar Manutencoes");
                case 7 -> System.out.println("Relatorios");
                case 8 -> {
                    System.out.println("Saindo...");
                    return true;
                }
                case 0 -> {
                    return false;
                }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private int lerOpcao(){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Opcao invalida. Digite um numero.");
                System.out.print("Opcao: ");
            }
        }
    }
}