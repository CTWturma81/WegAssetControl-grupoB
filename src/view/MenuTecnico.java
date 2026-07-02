package view;

import java.util.Scanner;

public class MenuTecnico {

    private final Scanner scanner;
    private AtivoView ativoView;
    private SensorView sensorView;

    public MenuTecnico(Scanner scanner, AtivoView ativoView, SensorView sensorView) {
        this.scanner = scanner;
        this.ativoView = ativoView;
        this.sensorView = sensorView;
    }

    public boolean menuTecnico(){
        while(true){
            System.out.println("\n=== MENU TECNICO ===");
            System.out.println("1 - Consultar Ativos");
            System.out.println("2 - Consultar Sensores");
            System.out.println("3 - Consultar Alertas");
            System.out.println("4 - Atualizar Manutencoes Atribuidas");
            System.out.println("8 - Logout");
            System.out.println("0 - Encerrar Sistema");
            System.out.print("Opcao: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> ativoView.menuAtivo();
                case 2 -> sensorView.menuSensor();
                case 3 -> System.out.println("Consultar Alertas - em construcao.");
                case 4 -> System.out.println("Atualizar Manutencoes - em construcao.");
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