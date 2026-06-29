package view;

import java.util.Scanner;

public class MenuOperador {

    private Scanner scanner;

    public MenuOperador(){
        scanner = new Scanner(System.in);
    }

    public void menuOperador(){
        int opcao = 0;
        while(opcao != 0){
            System.out.println("\n=== MENU OPERADOR ===");
            System.out.println("1 - Consultar Ativos");
            System.out.println("2 - Consultar Setores");
            System.out.println("3 - Consultar Alertas");
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