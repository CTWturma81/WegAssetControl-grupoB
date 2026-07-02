package view;

import java.util.Scanner;

public class MenuSupervisor {

    private final Scanner scanner;

    public MenuSupervisor(Scanner scanner){
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
                case 1 -> System.out.println("Gerenciar Setores");
                case 2 -> System.out.println("Gerenciar Ativos");
                case 3 -> System.out.println("Gerenciar Sensores");
                case 4 -> System.out.println("Gerenciar Alertas");
                case 5 -> System.out.println("Gerenciar Tecnicos");
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