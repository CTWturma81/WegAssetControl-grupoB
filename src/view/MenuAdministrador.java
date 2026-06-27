package view;

import java.util.Scanner;

public class MenuAdministrador {

    private Scanner scanner;
    GerenciarUsuarios gerenciarUsuarios;
    GerenciarTecnicos gerenciarTecnicos;
    GerenciarSetores gerenciarSetores;

    public MenuAdministrador(GerenciarUsuarios gerenciarUsuarios,GerenciarTecnicos gerenciarTecnicos,GerenciarSetores gerenciarSetores){
        scanner = new Scanner(System.in);
        this.gerenciarUsuarios = gerenciarUsuarios;
        this.gerenciarTecnicos = gerenciarTecnicos;
        this.gerenciarSetores =  gerenciarSetores;
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
                case 1-> gerenciarUsuarios.subMenuUsuario();
                case 6-> gerenciarTecnicos.subMenuTecnico();
                case 9-> System.out.println("Saindo");
                default -> System.out.println("Opção invalida");
            }
        }
    }

}