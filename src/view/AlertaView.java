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
            System.out.println("\n=== MENU ALERTA ===");
            System.out.println("1 - Cadastrar Alerta");
            System.out.println("2 - Listar Todos Alertas");
            System.out.println("3 - Listar Alertas Abertos");
            System.out.println("4 - Listar Alertas por Ativo");
            System.out.println("5 - Listar Críticos Abertos por Ativo");
            System.out.println("6 - Finalizar Alerta");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = lerOpcao();

            switch (opcao){
                case 1 -> cadastrar();
                case 2 -> listarTodos();
                case 3 -> listarAbertos();
                case 4 -> listarPorAtivo();
                case 5 -> listarCriticosAbertosPorAtivo();
                case 6 -> finalizar();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção Inválida");
            }
        }
    }

    public void cadastrar(){
        Integer idAtivo = lerId("ativo industrial");
        AtivoIndustrial ativoIndustrial = ativoController.buscarAtivoIndustrialPorID(idAtivo);

        Integer idSensor = lerId("sensor");
        Sensor sensor = sensorController.buscarSensorPorId(idSensor);

        System.out.println("Digite a descrição do alerta: ");
        String descricao = scanner.nextLine();

        NivelAlerta nivelAlerta = lerNivelAlerta();

        Alerta alerta = new Alerta(ativoIndustrial, sensor, descricao, nivelAlerta);
        boolean sucesso = alertaController.cadastrarAlerta(alerta);

        if (sucesso) {
            System.out.println("Alerta cadastrado com sucesso!");
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
            System.out.println("Alerta finalizado com sucesso!");
        }
    }

    private NivelAlerta lerNivelAlerta(){
        while(true){
            System.out.println("Nível do alerta:");
            System.out.println("1 - BAIXO");
            System.out.println("2 - MEDIO");
            System.out.println("3 - ALTO");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao){
                case "1": return NivelAlerta.BAIXO;
                case "2": return NivelAlerta.MEDIO;
                case "3": return NivelAlerta.ALTO;
                default: System.out.println("Opção inválida, tente novamente.");
            }
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
}