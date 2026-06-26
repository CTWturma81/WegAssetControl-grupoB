package view;

import java.util.Scanner;

public class MenuTecnico {

    private Scanner scanner;

    public MenuTecnico(){
        scanner = new Scanner(System.in);
    }

    public void menuTecnico(){
        int opcao = 0;
        while(opcao != 9){
            System.out.println("\n=== MENU TÉCNICO ===");
            System.out.println("1 - Consultar Ativos");
            System.out.println("2 - Consultar Sensores");
            System.out.println("3 - Consultar Alertas");
            System.out.println("4 - Atualizar Manutenções");
            System.out.println("0 - Logout");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 9 -> System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }
}
