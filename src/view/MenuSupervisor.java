package view;

import java.util.Scanner;

public class MenuSupervisor {

    private Scanner scanner;

    public MenuSupervisor(){
        scanner = new Scanner(System.in);
    }

    public void menuSupervisor() {
        int opcao = 0;
        while (opcao != 0) {
            System.out.println("\n=== MENU SUPERVISOR ===");
            System.out.println("1 - Gerenciar Setores");
            System.out.println("2 - Gerenciar Ativos");
            System.out.println("3 - Gerenciar Sensores");
            System.out.println("4 - Gerenciar Alertas");
            System.out.println("5 - Gerenciar Técnicos");
            System.out.println("6 - Gerenciar Manutenções");
            System.out.println("7 - Relatórios");
            System.out.println("0 - Logout");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
