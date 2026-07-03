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
            ConsoleUtils.telaPadrao();
            exibirMenu();
            System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);

            opcao = lerOpcao();

            switch (opcao){
                case 1 -> {
                    cadastrar();
                    aguardarEnter();
                }
                case 2 -> {
                    listar();
                    aguardarEnter();
                }
                case 3 -> {
                    buscarId();
                    aguardarEnter();
                }
                case 4 -> {
                    buscarCodigo();
                    aguardarEnter();
                }
                case 5 -> {
                    listarAtivo();
                    aguardarEnter();
                }
                case 6 -> {
                    atualizarValorAtualSensor();
                    aguardarEnter();
                }
                case 7 -> {
                    inativar();
                    aguardarEnter();
                }
                case 0 -> mensagemSucesso("Saindo...");
                default -> {
                    mensagemErro("Opção inválida.");
                    aguardarEnter();
                }
            }
        }
    }

    private void exibirMenu(){
        String azul = ConsoleUtils.AZUL_BRILHANTE;
        String branco = ConsoleUtils.BRANCO;
        String reset = ConsoleUtils.RESET;
        String negrito = ConsoleUtils.NEGRITO;

        System.out.println(azul + negrito + "  ╔═══════════════════════════════════╗" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "       GERENCIAR SENSORES         " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Cadastrar Sensor              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Listar Sensores               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Buscar Sensor por ID          " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Buscar Sensor por Código      " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Listar Sensores por Ativo     " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "6 - Atualizar Valor do Sensor     " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "7 - Inativar Sensor               " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Voltar                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    public void cadastrar(){
        System.out.print("Digite o código do sensor: ");
        String codigo = scanner.nextLine();

        String tipo = lerTipo();

        System.out.print("Digite o valor do sensor: ");
        Double valorAtual = lerDouble();

        String unidadeMedida = lerUnidadeMedida();

        Integer id = lerId("ativo industrial");
        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(id);

        Sensor sensor = new Sensor(codigo, tipo, ativoIndustrial, valorAtual, unidadeMedida);
        boolean sucesso = sensorController.cadastrarSensor(sensor);

        if (sucesso) {
            mensagemSucesso("Sensor cadastrado com sucesso!");
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
            mensagemSucesso("Sensor encontrado!");
            System.out.println(sensor);
        }
    }

    public void buscarCodigo(){
        System.out.print("Digite o código do sensor: ");
        String codigo = scanner.nextLine();

        Sensor sensor = sensorController.buscarSensorPorCodigo(codigo);

        if (sensor != null) {
            mensagemSucesso("Sensor encontrado!");
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

        System.out.print("Digite o valor do sensor: ");
        Double valorNovo = lerDouble();

        boolean sucesso = sensorController.atualizarValorAtualSensor(id, valorNovo);
        if (sucesso) {
            mensagemSucesso("Valor atualizado com sucesso!");
        }
    }

    public void inativar(){
        System.out.print("Digite o código do sensor: ");
        String codigo = scanner.nextLine();

        boolean sucesso = sensorController.inativarSensor(codigo);
        if (sucesso) {
            mensagemSucesso("Sensor inativado com sucesso!");
        }
    }

    private Integer lerId(String entidade){
        System.out.print("Digite o ID do " + entidade + ": ");
        while(!scanner.hasNextInt()){
            mensagemErro("Digite um número válido.");
            scanner.next();
        }
        Integer id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    private Double lerDouble(){
        while(!scanner.hasNextDouble()){
            mensagemErro("Digite um número válido.");
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
                mensagemErro("Opção inválida. Digite um número.");
                System.out.print(ConsoleUtils.BRANCO + "  Opção: " + ConsoleUtils.RESET);
            }
        }
    }

    private String lerTipo(){
        while(true){
            System.out.println("\nTipo do sensor:");
            System.out.println("1 - TEMPERATURA");
            System.out.println("2 - VIBRAÇÃO");
            System.out.println("3 - CORRENTE");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return "TEMPERATURA";
                case "2": return "VIBRACAO";
                case "3": return "CORRENTE";
                default: mensagemErro("Opção inválida, tente novamente.");
            }
        }
    }

    private String lerUnidadeMedida(){
        while(true){
            System.out.println("\nUnidade de medida:");
            System.out.println("1 - °C (Celsius)");
            System.out.println("2 - mm/s (Vibração)");
            System.out.println("3 - A (Amperes)");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return "°C";
                case "2": return "mm/s";
                case "3": return "A";
                default: mensagemErro("Opção inválida, tente novamente.");
            }
        }
    }

    private void mensagemSucesso(String texto){
        System.out.println("\u001B[92m  ✔ " + texto + ConsoleUtils.RESET);
    }

    private void mensagemErro(String texto){
        System.out.println("\u001B[91m  ✘ " + texto + ConsoleUtils.RESET);
    }

    private void aguardarEnter(){
        System.out.print(ConsoleUtils.BRANCO + "\n  Pressione ENTER para continuar..." + ConsoleUtils.RESET);
        scanner.nextLine();
    }
}