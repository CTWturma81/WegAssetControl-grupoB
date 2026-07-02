package view;

import java.util.Scanner;

public class MenuOperador {

    private final Scanner scanner;
    private AtivoView ativoView;
    private GerenciarSetores gerenciarSetores;

    public MenuOperador(Scanner scanner, AtivoView ativoView, GerenciarSetores gerenciarSetores) {
        this.scanner = scanner;
        this.ativoView = ativoView;
        this.gerenciarSetores = gerenciarSetores;
    }

    public boolean menuOperador(){
        while(true){
            System.out.println("\n=== MENU OPERADOR ===");
            System.out.println("1 - Consultar Ativos");
            System.out.println("2 - Consultar Setores");
            System.out.println("3 - Consultar Alertas");
            System.out.println("4 - Logout");
            System.out.println("0 - Encerrar Sistema");
            System.out.print("Opcao: ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> ativoView.menuAtivo();
                case 2 -> gerenciarSetores.menuSetor();
                case 3 -> System.out.println("Consultar Alertas");
                case 4 -> {
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