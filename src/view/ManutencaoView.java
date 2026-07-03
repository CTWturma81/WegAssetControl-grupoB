package view;

import controller.AtivoController;
import controller.ManutencaoController;
import controller.TecnicoController;
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
            System.out.println("5 - Listar manutenções abertas");
            System.out.println("6 - Listar manutenções por técnico");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = lerOpcao();

            switch (opcao){
                case 1 -> abrir();
                case 2 -> atribuirManutencao();
                case 3 -> registrar();
                case 4 -> finalizar();
                case 5 -> listarAbertas();
                case 6 -> listarPorTecnico();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    public void abrir() {
        Integer idAtivo = lerId("ativo industrial");
        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(idAtivo);

        System.out.print("Insira a descrição do problema: ");
        String descricao = input.nextLine();

        Integer idTecnico = lerId("técnico");
        Tecnico tecnico = tecnicoController.buscarTecnico(idTecnico);

        Manutencao manutencao = new Manutencao(ativoIndustrial, tecnico, descricao);

        boolean sucesso = manutencaoController.abrirManutencao(manutencao);

        if (sucesso) {
            System.out.println("Manutenção aberta com sucesso!");
        }
    }

    public void atribuirManutencao(){
        Integer idManutencao = lerId("manutenção");
        Integer idTecnico = lerId("tecnico");

        Tecnico tecnico = tecnicoController.buscarTecnico(idTecnico);

        boolean sucesso = manutencaoController.atribuirTecnicoManutencao(idManutencao, tecnico);
        if (sucesso) {
            System.out.println("Técnico atribuído com sucesso!");
        }
    }

    public void registrar(){
        Integer idManutencao = lerId("manutenção");

        System.out.println("Insira a observação técnica: ");
        String observacao = input.nextLine();

        boolean sucesso = manutencaoController.registrarObservacao(idManutencao, observacao);
        if (sucesso) {
            System.out.println("Observação registrada com sucesso!");
        }
    }

    public void finalizar(){
        Integer idManutencao = lerId("manutenção");

        boolean sucesso = manutencaoController.finalizarManutencao(idManutencao);
        if (sucesso) {
            System.out.println("Manutenção finalizada com sucesso!");
        }
    }

    public void listarAbertas(){
        Collection<Manutencao> manutencoes = manutencaoController.listarManutencaoAberta();
        if (manutencoes != null) {
            manutencoes.forEach(System.out::println);
        }
    }

    public void listarPorTecnico(){
        Integer idTecnico = lerId("tecnico");

        Collection<Manutencao> manutencoes = manutencaoController.listarManutencaoTecnico(idTecnico);
        if (manutencoes != null) {
            manutencoes.forEach(System.out::println);
        }
    }

    private Integer lerId(String entidade){
        System.out.println("Digite o ID do " + entidade + ": ");
        while(!input.hasNextInt()){
            System.out.println("Digite um número válido.");
            input.next();
        }
        Integer id = input.nextInt();
        input.nextLine();
        return id;
    }

    private int lerOpcao(){
        while (true){
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Opção inválida. Digite um número.");
                System.out.print("Opção: ");
            }
        }
    }
}