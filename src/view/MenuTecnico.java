package view;

import java.util.Scanner;

public class MenuTecnico {

    private final Scanner scanner;

    public MenuTecnico(Scanner scanner){
        this.scanner = scanner;
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
                case 1 -> System.out.println("Consultar Ativos - em construcao.");
                case 2 -> System.out.println("Consultar Sensores - em construcao.");
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