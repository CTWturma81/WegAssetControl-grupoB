package view;

import java.util.Scanner;

public class MenuAdministrador {

    private Scanner scanner;

    public MenuAdministrador(){
        scanner = new Scanner(System.in);
    }

    public void menuAdm(){
        int opcao = 0;
        while(opcao != 9){
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Setores");
            System.out.println("3 - Gerenciar Ativos");
            System.out.println("4 - Gerenciar Sensores");
            System.out.println("5 - Gerenciar Alertas");
            System.out.println("6 - Gerenciar Técnicos");
            System.out.println("7 - Gerenciar Manutenções");
            System.out.println("8 - Relatórios");
            System.out.println("9 - Logout");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1-> System.out.println("Gerenciando usuarios");
                case 9-> System.out.println("Saindo");
                default -> System.out.println("Opção invalida");
            }
        }
    }

}