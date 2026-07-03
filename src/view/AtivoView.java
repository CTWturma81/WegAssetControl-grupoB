package view;

import controller.AtivoController;
import controller.SetorController;
import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Setor;

import java.util.Collection;
import java.util.Scanner;

public class AtivoView {

    Scanner input = new Scanner(System.in);
    private AtivoController ativoController;
    private SetorController setorController;

    public AtivoView(AtivoController ativoController, SetorController setorController) {
        this.setorController = setorController;
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
            System.out.print("Opção: ");
            opcao = input.nextInt();
            input.nextLine();

            switch(opcao){
                case 1-> cadastrar();
                case 2-> listar();
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

        Setor setor = lerSetor();

        boolean sucesso = ativoController.cadastrarAtivoIndustrial(new AtivoIndustrial(nome, codigoPatrimonial, tipo, ciclagem, modelo, setor));

        if(sucesso) {
            System.out.println("Ativo cadastrado com sucesso!");
        }
    }

    public void listar(){
        Collection<AtivoIndustrial> ativos = ativoController.listarAtivoIndustrial();
        if (ativos == null || ativos.isEmpty()) {
            System.out.println("Nenhum ativo cadastrado.");
        } else {
            ativos.forEach(System.out::println);
        }
    }

    public void buscar(){
        System.out.println("Digite o id do ativo que deseja buscar: ");
        Integer id = input.nextInt();
        input.nextLine();

        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(id);

        if(ativoIndustrial != null) {
            System.out.println("Ativo encontrado!");
            System.out.println(ativoIndustrial);
        } else {
            System.out.println("Ativo não encontrado.");
        }
    }

    public void editar() {
        System.out.println("Digite o id do ativo que deseja editar: ");
        Integer idAtivo = input.nextInt();
        input.nextLine();

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

        Setor setor = lerSetor();

        AtivoIndustrial ativoIndustrial = new AtivoIndustrial(nome, codigoPatrimonial, tipo, ciclagem, modelo, setor);
        ativoIndustrial.setId(idAtivo);

        boolean sucesso = ativoController.editarAtivoIndustrial(ativoIndustrial);

        if(sucesso) {
            System.out.println("Ativo editado com sucesso!");
        }
    }

    public void inativar(){
        System.out.println("Digite o id do ativo que deseja inativar: ");
        Integer id = input.nextInt();
        input.nextLine();

        boolean sucesso = ativoController.inativarAtivoIndustrial(id);

        if(sucesso) {
            System.out.println("Ativo inativado com sucesso!");
        }
    }

    public void listarStatus(){
        StatusAtivo statusAtivo = lerStatus();

        Collection<AtivoIndustrial> ativos = ativoController.listarAtivoIndustrialStatus(statusAtivo);
        if (ativos != null) {
            ativos.forEach(System.out::println);
        }
    }

    public void listarSetor(){
        System.out.println("Digite o ID do setor do ativo: ");
        Integer id = input.nextInt();
        input.nextLine();

        Collection<AtivoIndustrial> ativos = ativoController.listarAtivoIndustrialSetor(id);
        if (ativos != null) {
            ativos.forEach(System.out::println);
        }
    }

    private Setor lerSetor(){
        System.out.println("Digite o id do setor do ativo: ");
        Integer id = input.nextInt();
        input.nextLine();

        return setorController.buscarSetor(id);
    }

    private StatusAtivo lerStatus(){
        while(true){
            System.out.println("Digite o status do ativo:");
            System.out.println("1 - NORMAL");
            System.out.println("2 - ATENÇÃO");
            System.out.println("3 - CRITICO");
            System.out.println("4 - EM_MANUTENÇÃO");
            System.out.println("5 - INATIVO");
            System.out.print("Escolha: ");
            String opcao = input.nextLine().trim();

            switch (opcao) {
                case "1": return StatusAtivo.NORMAL;
                case "2": return StatusAtivo.ATENCAO;
                case "3": return StatusAtivo.CRITICO;
                case "4": return StatusAtivo.EM_MANUTENCAO;
                case "5": return StatusAtivo.INATIVO;
                default: System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}