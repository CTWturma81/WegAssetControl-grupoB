package view;

import controller.SensorController;
import enums.StatusAtivo;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;

import java.util.Collection;
import java.util.Scanner;

public class SensorView {

    private Scanner scanner;
    SensorController sensorController;

    public SensorView(SensorController sensorController){
        scanner = new Scanner(System.in);
        this.sensorController = sensorController;
    }

    public Integer lerId(){
        System.out.println("Digite o ID: ");
        Integer id = scanner.nextInt();
        return id;
    }

    public Integer lerIdAtivo(){
        scanner.nextLine();
        System.out.println("Digite o ID do ativo: ");
        Integer idAtivo = scanner.nextInt();
        return idAtivo;
    }


    public Sensor lerDadosSensor(AtivoIndustrial ativo){
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

        return new Sensor(codigo,tipo, ativo, valorAtual, unidadeMedida, StatusAtivo.NORMAL);
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
            switch (opcao){
                case 1 -> sensorController.cadastrarSensor();
                case 2 -> sensorController.listarSensor();
                case 3 -> sensorController.buscarSensorPorId();
                case 4 -> sensorController.buscarSensorPorCodigo();
                case 5 -> sensorController.listarSensorPorAtivo();
                case 6 -> sensorController.atualizarValorAtualSensor();
                case 7 -> sensorController.inativarSensor();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção invalida");
            }
        }
    }

    public void lerStatusSensor(){

    }
}
