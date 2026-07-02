package view;

import controller.AtivoController;
import controller.SensorController;
import enums.StatusAtivo;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;

import java.util.Collection;
import java.util.Scanner;

public class SensorView {

    private Scanner scanner;
    SensorController sensorController;
    AtivoController ativoController;

    public SensorView(SensorController sensorController, AtivoController ativoController){
        scanner = new Scanner(System.in);
        this.sensorController = sensorController;
        this.ativoController = ativoController;
    }

    public void menuSensor(){
        int opcao = 1;
        while(opcao != 0){
            System.out.println("\n====MENU SENSOR=====");
            System.out.println("1 - Cadastrar Sensor");
            System.out.println("2 - Listar Sensores");
            System.out.println("3 - Buscar Sensor por ID");
            System.out.println("4 - Buscar Sensor por código");
            System.out.println("5 - Listar Sensores por Ativo");
            System.out.println("6 - Atualizar valor atual do Sensor");
            System.out.println("7 - Inativar sensor");
            System.out.println("0 - Sair");
            System.out.println("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1 -> cadastrar();
                case 2 -> sensorController.listarSensor();
                case 3 -> buscarId();
                case 4 -> buscarCodigo();
                case 5 -> listarAtivo();
                case 6 -> atualizarValorAtualSensor();
                case 7 -> inativar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public void cadastrar(){
        scanner.nextLine();
        System.out.println("Digite o código do sensor: ");
        String codigo = scanner.nextLine();

        System.out.println("Tipo: (TEMPERATURA, VIBRACAO, CORRENTE): ");
        String tipo = scanner.nextLine();

        System.out.println("Digite o valor do sensor: ");
        Double valorAtual = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Digite a unidade de medida: ");
        String unidadeMedida = scanner.nextLine();

        System.out.println("Digite o ID do ativo industrial que desejas cadastrar: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(id);

        Sensor sensor = new Sensor(codigo, tipo, ativoIndustrial, valorAtual, unidadeMedida, StatusAtivo.NORMAL);
        sensorController.cadastrarSensor(sensor);

        System.out.println("Sensor cadastrado com sucesso");
    }

    public void buscarId(){
        System.out.println("Digite o ID do sensor: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        sensorController.buscarSensorPorId(id);
        System.out.println("Sensor buscado com sucesso");
    }

    public void buscarCodigo(){
        System.out.println("Digite o codigo do sensor: ");
        String codigo = scanner.nextLine();

        sensorController.buscarSensorPorCodigo(codigo);
        System.out.println("Sensor buscado com sucesso: ");
    }

    public void listarAtivo(){
        System.out.println("Digite o ID do ativo industrial: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(id);
        sensorController.listarSensorPorAtivo(ativoIndustrial);
    }

    public void atualizarValorAtualSensor(){
        System.out.println("Digite o Id do sensor: ");
        Integer id = scanner.nextInt();

        System.out.println("Digite o valor do sensor: ");
        Double valorNovo = scanner.nextDouble();
        scanner.nextLine();

        sensorController.atualizarValorAtualSensor(id, valorNovo);
    }

    public void inativar(){
        System.out.println("Digite o codigo do sensor: ");
        String codigo = scanner.nextLine();

        sensorController.inativarSensor(codigo);
    }

    public Integer lerIdAtivo(){
        scanner.nextLine();
        System.out.println("Digite o ID do ativo: ");
        Integer idAtivo = scanner.nextInt();
        scanner.nextLine();

        return idAtivo;
    }

    public void exibirSensor(Sensor sensor){
        System.out.println("ID: " + sensor.getId());
        System.out.println("Código: " + sensor.getCodigo());
        System.out.println("Tipo: " + sensor.getTipo());
        System.out.println("Ativo: "+ sensor.getAtivoIndustrial().getNome());
        System.out.println("Valor atual: " + sensor.getValorAtual());
        System.out.println("Unidade de medida: " + sensor.getUnidadeMedida());
        System.out.println("Status: " + sensor.getStatusAtivo());
    }

    public void exibirSensores(Collection<Sensor> sensores){
        for(Sensor s : sensores){
            exibirSensor(s);
            System.out.println("====================================");
        }
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }

    public Double lerValorAtual(){
        System.out.println("Digite o valor novo: ");
        return scanner.nextDouble();
    }
}
