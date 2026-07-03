package view;

import controller.AlertaController;
import controller.AtivoController;
import controller.SensorController;
import enums.NivelAlerta;
import model.entity.Alerta;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;

import java.util.Collection;
import java.util.Scanner;

public class AlertaView {

    private Scanner scanner;
    private AlertaController alertaController;
    private AtivoController ativoController;
    private SensorController sensorController;

    public AlertaView(AlertaController alertaController, AtivoController ativoController, SensorController sensorController){
        scanner = new Scanner(System.in);
        this.alertaController = alertaController;
        this.ativoController = ativoController;
        this.sensorController = sensorController;
    }

    public void menuAlerta(){
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
                    listarTodos();
                    aguardarEnter();
                }
                case 3 -> {
                    listarAbertos();
                    aguardarEnter();
                }
                case 4 -> {
                    listarPorAtivo();
                    aguardarEnter();
                }
                case 5 -> {
                    listarCriticosAbertosPorAtivo();
                    aguardarEnter();
                }
                case 6 -> {
                    finalizar();
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
        System.out.println(azul + negrito + "  ║ " + branco + "       GERENCIAR ALERTAS          " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "1 - Cadastrar Alerta              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "2 - Listar Todos                  " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "3 - Listar Abertos                " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "4 - Listar por Ativo              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "5 - Críticos por Ativo            " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "6 - Finalizar Alerta              " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╠═══════════════════════════════════╣" + reset);
        System.out.println(azul + negrito + "  ║ " + branco + "0 - Voltar                        " + azul + "║" + reset);
        System.out.println(azul + negrito + "  ╚═══════════════════════════════════╝" + reset);
        System.out.println();
    }

    public void cadastrar(){
        Integer idAtivo = lerId("ativo industrial");
        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(idAtivo);

        Integer idSensor = lerId("sensor");
        Sensor sensor = sensorController.buscarSensorPorId(idSensor);

        System.out.print("Digite a descrição do alerta: ");
        String descricao = scanner.nextLine();

        NivelAlerta nivelAlerta = lerNivelAlerta();

        Alerta alerta = new Alerta(ativoIndustrial, sensor, descricao, nivelAlerta);
        boolean sucesso = alertaController.cadastrarAlerta(alerta);

        if (sucesso) {
            mensagemSucesso("Alerta cadastrado com sucesso!");
        }
    }

    public void listarTodos(){
        Collection<Alerta> alertas = alertaController.listarTodosAlertas();
        if (alertas != null) {
            alertas.forEach(System.out::println);
        }
    }

    public void listarAbertos(){
        Collection<Alerta> alertas = alertaController.listarAbertos();
        if (alertas != null) {
            alertas.forEach(System.out::println);
        }
    }

    public void listarPorAtivo(){
        Integer id = lerId("ativo industrial");
        Collection<Alerta> alertas = alertaController.listarPorAtivo(id);
        if (alertas != null) {
            alertas.forEach(System.out::println);
        }
    }

    public void listarCriticosAbertosPorAtivo(){
        Integer id = lerId("ativo industrial");
        Collection<Alerta> alertas = alertaController.listarCriticosAbertosPorAtivo(id);
        if (alertas != null) {
            alertas.forEach(System.out::println);
        }
    }

    public void finalizar(){
        Integer id = lerId("alerta");
        boolean sucesso = alertaController.finalizarAlerta(id);

        if (sucesso) {
            mensagemSucesso("Alerta finalizado com sucesso!");
        }
    }

    private NivelAlerta lerNivelAlerta(){
        while(true){
            System.out.println("\nNível do alerta:");
            System.out.println("1 - BAIXO");
            System.out.println("2 - MÉDIO");
            System.out.println("3 - ALTO");
            System.out.print("Escolha: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return NivelAlerta.BAIXO;
                case "2": return NivelAlerta.MEDIO;
                case "3": return NivelAlerta.ALTO;
                default: mensagemErro("Opção inválida, tente novamente.");
            }
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