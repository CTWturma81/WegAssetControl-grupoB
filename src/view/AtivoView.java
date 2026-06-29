package view;

import controller.AtivoController;
import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Setor;
import model.entity.Usuario;

import java.util.Scanner;

public class AtivoView {

    Scanner input = new Scanner(System.in);
    AtivoController ativoController;

    public AtivoView(AtivoController ativoController) {
        this.ativoController = ativoController;
    }

    public void menuAtivo(){
        int opcao = 0;

        while(opcao != 0){
            System.out.println("1 - Cadastrar ativo");
            System.out.println("2 - Listar ativo");
            System.out.println("3 - Buscar ativo");
            System.out.println("4 - Editar ativo");
            System.out.println("5 - Inativar ativo");
            System.out.println("6 - Listar ativo por status");
            System.out.println("7 - Listar ativo por setor");
            System.out.println("0 - Sair");
            opcao = input.nextInt();

            switch(opcao){
                case 1-> ativoController.cadastrarAtivoIndustrial();
                case 2-> ativoController.listarAtivoIndustrial();
                case 3-> ativoController.buscarAtivoIndustrialPorID();
                case 4-> ativoController.editarAtivoIndustrial();
                case 5-> ativoController.inativarAtivoIndustrial();
                case 6-> ativoController.listarAtivoIndustrialStatus();
                case 7-> ativoController.listarAtivoIndustrialSetor();
                case 0->System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public AtivoIndustrial lerDadosAtivo(){
        System.out.println("Digite o nome do ativo: ");
        String nome = input.nextLine();

        System.out.println("Digite o codigo patrimonial do ativo: ");
        String codigoPatrimonial = input.nextLine();

        System.out.println("Digite o tipo do ativo: ");
        String tipo = input.nextLine();

        System.out.println("Digite o ciclagem do ativo: ");
        String ciclagem = input.nextLine();

        System.out.println("Digite o modelo do ativo: ");
        String modelo = input.nextLine();

        System.out.println("Digite o nome do setor do ativo: ");
        String nomeSetor = input.nextLine();

        System.out.println("Digite a descriçao do setor do ativo: ");
        String descricaoSetor = input.nextLine();

        Setor setor = new Setor(nomeSetor, descricaoSetor);

        System.out.println("Digite o status do ativo:" +
                "1 - NORMAL" +
                "2 - ATENÇÃO" +
                "3 - CRITICO" +
                "4 - EM_MANUTENÇÃO" +
                "5 - INATIVO");
        int opcao = input.nextInt();
        input.nextLine();

        StatusAtivo statusAtivo;
        statusAtivo = switch (opcao){
            case 1 -> StatusAtivo.NORMAL;
            case 2 -> StatusAtivo.ATENCAO;
            case 3 -> StatusAtivo.CRITICO;
            case 4 -> StatusAtivo.EM_MANUTENCAO;
            case 5 -> StatusAtivo.INATIVO;
            default -> throw new AppException("Coloque um valor valido");
        };

        return new AtivoIndustrial(nome, codigoPatrimonial, tipo, ciclagem, modelo , setor, statusAtivo);
    }

    public Integer lerId(){
        System.out.println("Digite o id do ativo que deseja buscar: ");
        return input.nextInt();
    }

    public StatusAtivo lerStatus(){
        System.out.println("Digite o status do ativo:" +
                "1 - NORMAL" +
                "2 - ATENÇÃO" +
                "3 - CRITICO" +
                "4 - EM_MANUTENÇÃO" +
                "5 - INATIVO");
        int opcao = input.nextInt();
        input.nextLine();

         return switch (opcao){
            case 1 -> StatusAtivo.NORMAL;
            case 2 -> StatusAtivo.ATENCAO;
            case 3 -> StatusAtivo.CRITICO;
            case 4 -> StatusAtivo.EM_MANUTENCAO;
            case 5 -> StatusAtivo.INATIVO;
            default -> throw new AppException("Coloque um valor valido");
        };
    }

    public Integer lerIdSetor(){
        System.out.println("Digite o ID do setor do ativo: ");
        Integer id = input.nextInt();

        return id;
    }
}