import controller.*;
import model.service.*;
import model.repository.*;
import view.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Repositories (guardam dado em memoria)
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        SetorRepository setorRepository = new SetorRepository();
        AtivoRepository ativoRepository = new AtivoRepository();
        SensorRepository sensorRepository = new SensorRepository();
        TecnicoRepository tecnicoRepository = new TecnicoRepository();
        AlertaRepository alertaRepository = new AlertaRepository();

        // Services (regra de negocio)
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        SetorService setorService = new SetorService(setorRepository, ativoRepository);
        AtivoService ativoService = new AtivoService(ativoRepository, setorRepository);
        SensorService sensorService = new SensorService(sensorRepository);
        TecnicoService tecnicoService = new TecnicoService(tecnicoRepository);
        AuthService authService = new AuthService(usuarioRepository);
        AlertaService alertaService = new AlertaService(ativoRepository, alertaRepository);

        // Controllers
        UsuarioController usuarioController = new UsuarioController(usuarioService);
        AuthController authController = new AuthController(authService);
        SetorController setorController = new SetorController(setorService);
        AtivoController ativoController = new AtivoController(ativoService);
        SensorController sensorController = new SensorController(sensorService);
        TecnicoController tecnicoController = new TecnicoController(tecnicoService);
        AlertaController alertaController = new AlertaController(alertaService);

        GerenciarUsuarios gerenciarUsuarios = new GerenciarUsuarios(usuarioController);
        GerenciarSetores gerenciarSetores = new GerenciarSetores(setorController);
        AtivoView ativoView = new AtivoView(ativoController, setorController);
        SensorView sensorView = new SensorView(sensorController, ativoController);
        GerenciarTecnicos gerenciarTecnicos = new GerenciarTecnicos(tecnicoController);
        AlertaView alertaView = new AlertaView(alertaController, ativoController, sensorController);

        MenuAdministrador menuAdministrador = new MenuAdministrador(scanner, gerenciarUsuarios, gerenciarSetores, ativoView, sensorView, gerenciarTecnicos, alertaView);
        MenuSupervisor menuSupervisor = new MenuSupervisor(scanner, gerenciarSetores, ativoView, sensorView, gerenciarTecnicos, alertaView);
        MenuTecnico menuTecnico = new MenuTecnico(scanner, ativoView, sensorView, alertaView);
        MenuOperador menuOperador = new MenuOperador(scanner, ativoView, gerenciarSetores, alertaView);

        SistemaView sistemaView = new SistemaView(scanner, authController, usuarioController, menuAdministrador, menuSupervisor, menuTecnico, menuOperador);
        sistemaView.iniciar();
    }
}