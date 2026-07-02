package view;

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
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1 - Gerenciar Usuarios");
            System.out.println("2 - Gerenciar Setores");
            System.out.println("3 - Gerenciar Ativos");
            System.out.println("4 - Gerenciar Sensores");
            System.out.println("5 - Gerenciar Alertas");
            System.out.println("6 - Gerenciar Tecnicos");
            System.out.println("7 - Gerenciar Manutencoes");
            System.out.println("8 - Relatorios");
            System.out.println("9 - Logout");
            System.out.println("0 - Encerrar Sistema");
            System.out.print("Opcao: ");
            int opcao = lerOpcao();

            switch (opcao) {
                case 1 -> gerenciarUsuarios.subMenuUsuario();
                case 2 -> gerenciarSetores.menuSetor();
                case 3 -> ativoView.menuAtivo();
                case 4 -> sensorView.menuSensor();
                case 5 -> System.out.println("Gerenciar Alertas ");
                case 6 -> gerenciarTecnicos.subMenuTecnico();
                case 7 -> System.out.println("Gerenciar Manutencoes ");
                case 8 -> System.out.println("Relatorios");
                case 9 -> {
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