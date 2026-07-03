package view;

import controller.AtivoController;
import controller.SensorController;
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
            System.out.println("\n=== MENU SENSOR ===");
            System.out.println("1 - Cadastrar Sensor");
            System.out.println("2 - Listar Sensores");
            System.out.println("3 - Buscar Sensor por ID");
            System.out.println("4 - Buscar Sensor por código");
            System.out.println("5 - Listar Sensores por Ativo");
            System.out.println("6 - Atualizar valor atual do Sensor");
            System.out.println("7 - Inativar sensor");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = lerOpcao();

            switch (opcao){
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> buscarId();
                case 4 -> buscarCodigo();
                case 5 -> listarAtivo();
                case 6 -> atualizarValorAtualSensor();
                case 7 -> inativar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção Inválida");
            }
        }
    }

    public void cadastrar(){
        System.out.println("Digite o código do sensor: ");
        String codigo = scanner.nextLine();

        String tipo = lerTipo();

        System.out.println("Digite o valor do sensor: ");
        Double valorAtual = lerDouble();

        String unidadeMedida = lerUnidadeMedida();

        Integer id = lerId("ativo industrial");
        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(id);

        Sensor sensor = new Sensor(codigo, tipo, ativoIndustrial, valorAtual, unidadeMedida);
        boolean sucesso = sensorController.cadastrarSensor(sensor);

        if (sucesso) {
            System.out.println("Sensor cadastrado com sucesso!");
        }
    }

    public void listar() {
        Collection<Sensor> sensores = sensorController.listarSensor();
        if (sensores != null) {
            sensores.forEach(System.out::println);
        }
    }

    public void buscarId(){
        Integer id = lerId("sensor");
        Sensor sensor = sensorController.buscarSensorPorId(id);

        if (sensor != null) {
            System.out.println("Sensor encontrado!");
            System.out.println(sensor);
        }
    }

    public void buscarCodigo(){
        System.out.println("Digite o codigo do sensor: ");
        String codigo = scanner.nextLine();

        Sensor sensor = sensorController.buscarSensorPorCodigo(codigo);

        if (sensor != null) {
            System.out.println("Sensor encontrado!");
            System.out.println(sensor);
        }
    }

    public void listarAtivo(){
        Integer id = lerId("ativo industrial");
        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(id);

        Collection<Sensor> sensores = sensorController.listarSensorPorAtivo(ativoIndustrial);
        if (sensores != null) {
            sensores.forEach(System.out::println);
        }
    }

    public void atualizarValorAtualSensor(){
        Integer id = lerId("sensor");

        System.out.println("Digite o valor do sensor: ");
        Double valorNovo = lerDouble();

        boolean sucesso = sensorController.atualizarValorAtualSensor(id, valorNovo);
        if (sucesso) {
            System.out.println("Valor atualizado com sucesso!");
        }
    }

    public void inativar(){
        System.out.println("Digite o codigo do sensor: ");
        String codigo = scanner.nextLine();

        boolean sucesso = sensorController.inativarSensor(codigo);
        if (sucesso) {
            System.out.println("Sensor inativado com sucesso!");
        }
    }

    private Integer lerId(String entidade){
        System.out.println("Digite o ID do " + entidade + ": ");
        while(!scanner.hasNextInt()){
            System.out.println("Digite um número válido.");
            scanner.next();
        }
        Integer id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private Double lerDouble(){
        while(!scanner.hasNextDouble()){
            System.out.println("Digite um número válido.");
            scanner.next();
        }
        Double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private int lerOpcao(){
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Opção inválida. Digite um número.");
                System.out.print("Opção: ");
            }
        }
    }

    private String lerTipo(){
        while(true){
            System.out.println("Tipo do sensor:");
            System.out.println("1 - TEMPERATURA");
            System.out.println("2 - VIBRACAO");
            System.out.println("3 - CORRENTE");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return "TEMPERATURA";
                case "2": return "VIBRACAO";
                case "3": return "CORRENTE";
                default: System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private String lerUnidadeMedida(){
        while(true){
            System.out.println("Unidade de medida:");
            System.out.println("1 - °C (Celsius)");
            System.out.println("2 - mm/s (Vibração)");
            System.out.println("3 - A (Amperes)");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return "°C";
                case "2": return "mm/s";
                case "3": return "A";
                default: System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

}