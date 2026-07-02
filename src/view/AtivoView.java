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
        int opcao = 1;

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
                case 1-> cadastrar();
                case 2-> ativoController.listarAtivoIndustrial();
                case 3-> buscar();
                case 4-> editar();
                case 5-> inativar();
                case 6-> listarStatus();
                case 7-> listarSetor();
                case 0->System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public void cadastrar(){
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

        AtivoIndustrial ativoIndustrial = new AtivoIndustrial(nome, codigoPatrimonial, tipo, ciclagem,  modelo, setor, statusAtivo);
        ativoController.cadastrarAtivoIndustrial(ativoIndustrial);
    }

    public void buscar(){
        System.out.println("Digite o id do ativo que deseja buscar: ");
        Integer id = input.nextInt();
        ativoController.buscarAtivoIndustrialPorID(id);

        System.out.println("Ativo buscado com sucesso");
    }

    public void editar() {
        System.out.println("Digite o id do ativo que deseja buscar: ");
        Integer idAtivo = input.nextInt();

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
        statusAtivo = switch (opcao) {
            case 1 -> StatusAtivo.NORMAL;
            case 2 -> StatusAtivo.ATENCAO;
            case 3 -> StatusAtivo.CRITICO;
            case 4 -> StatusAtivo.EM_MANUTENCAO;
            case 5 -> StatusAtivo.INATIVO;
            default -> throw new AppException("Coloque um valor valido");
        };

        AtivoIndustrial ativoIndustrial = new AtivoIndustrial(codigoPatrimonial, nome, tipo, ciclagem, modelo, setor, statusAtivo);
        ativoController.editarAtivoIndustrial(idAtivo,ativoIndustrial);

        System.out.println("Ativo buscado com sucesso:");
    }

    public void inativar(){
        System.out.println("Digite o id do ativo que deseja inativar: ");
        Integer id = input.nextInt();
        ativoController.inativarAtivoIndustrial(id);

        System.out.println("Ativo inativado com sucesso");
    }

    public void listarStatus(){
        System.out.println("Digite o status do ativo:" +
                "1 - NORMAL" +
                "2 - ATENÇÃO" +
                "3 - CRITICO" +
                "4 - EM_MANUTENÇÃO" +
                "5 - INATIVO");
        int opcao = input.nextInt();
        input.nextLine();

         StatusAtivo statusAtivo = switch (opcao){
            case 1 -> StatusAtivo.NORMAL;
            case 2 -> StatusAtivo.ATENCAO;
            case 3 -> StatusAtivo.CRITICO;
            case 4 -> StatusAtivo.EM_MANUTENCAO;
            case 5 -> StatusAtivo.INATIVO;
            default -> throw new AppException("Coloque um valor valido");
        };

         ativoController.listarAtivoIndustrialStatus(statusAtivo);
    }

    public void listarSetor(){
        System.out.println("Digite o ID do setor do ativo: ");
        Integer id = input.nextInt();

        ativoController.listarAtivoIndustrialSetor(id);
    }
}