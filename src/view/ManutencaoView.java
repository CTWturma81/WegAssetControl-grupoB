package view;

import controller.AtivoController;
import controller.ManutencaoController;
import controller.TecnicoController;
import enums.StatusManutencao;
import model.entity.AtivoIndustrial;
import model.entity.Manutencao;
import model.entity.Tecnico;

import java.util.Collection;
import java.util.Scanner;

public class ManutencaoView {

    Scanner input = new Scanner(System.in);
    private ManutencaoController manutencaoController;
    private TecnicoController tecnicoController;
    private AtivoController ativoController;

    public ManutencaoView(ManutencaoController manutencaoController, TecnicoController tecnicoController, AtivoController ativoController){
        this.manutencaoController = manutencaoController;
        this.tecnicoController = tecnicoController;
        this.ativoController = ativoController;
    }

    public void menuManutencao(){
        int opcao = 1;

        while(opcao != 0){
            System.out.println("1 - Abrir manutenção");
            System.out.println("2 - Atribuir manutenção a um técnico");
            System.out.println("3 - Registrar observação");
            System.out.println("4 - Finalizar manutenção");
            System.out.println("5 - Listar manutenção abertas");
            System.out.println("6 - Listar manutenção por tecnicos");
            System.out.println("0 - Sair");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao){
                case 1 -> {
                    abrir();
                }
                case 2 -> {
                    atribuirManutencao();
                }
                case 3 -> {
                    registrar();
                }
                case 4 -> {
                    finalizar();
                }
                case 5 -> {

                }
                case 6 -> {

                }
                case 0 -> System.out.println("Saindo...");
            }
        }
    }

    public void abrir(){
        System.out.println("Insira o id do ativo industrial: ");
        Integer idAtivo = input.nextInt();

        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(idAtivo);

        System.out.println("Insira o id do tecnico: ");
        Integer idTecnico = input.nextInt();
        input.nextLine();

        Tecnico tecnico = tecnicoController.buscarTecnico(idTecnico);

        System.out.println("Insira a descrição do problema: ");
        String descricao = input.nextLine();

        System.out.println("Insira a observação tecnica");
        String observacao = input.nextLine();

        System.out.println("Insira o status da Manutenção(ABERTAS, EM_MANUTENCAO, FINALIZADA)");
        StatusManutencao statusManutencao = StatusManutencao.valueOf(input.nextLine().toUpperCase());

        Manutencao manutencao = new Manutencao(ativoIndustrial,tecnico,descricao,observacao,statusManutencao);
    }

    public void atribuirManutencao(){
        System.out.println("Insira o id da manutenção: ");
        Integer idManutencao = input.nextInt();

        System.out.println("Insira o id do tecnico: ");
        Integer idTecnico = input.nextInt();
        input.nextLine();

        Tecnico tecnico = tecnicoController.buscarTecnico(idTecnico);

        manutencaoController.atribuirTecnicoManutencao(idManutencao, tecnico);
    }

    public void registrar(){
        System.out.println("Insira o id da manutenção: ");
        Integer idManutencao = input.nextInt();
        input.nextLine();

        System.out.println("Insira a observação tecnica");
        String observacao = input.nextLine();

        manutencaoController.registrarObservacao(idManutencao, observacao);
    }

    public void finalizar(){
        System.out.println("Insira o id da manutenção: ");
        Integer idManutencao = input.nextInt();
        input.nextLine();

        manutencaoController.finalizarManutenção(idManutencao);
    }
}
