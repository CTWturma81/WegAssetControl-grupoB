package controller;

import enums.PerfilAcesso;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.service.AuthService;
import model.service.SensorService;

import java.util.Collection;

public class SensorController {

    private SensorService sensorService;
    private AuthService authService;

    public SensorController(SensorService sensorService, AuthService authService){
        this.sensorService = sensorService;
        this.authService = authService;
    }

    public void cadastrarSensor(Sensor sensor){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            sensorService.cadastrarSensor(sensor);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public Collection<Sensor> listarSensor(){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return sensorService.listarSensor();
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Sensor buscarSensorPorId(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return sensorService.buscarSensorPorId(id);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Sensor buscarSensorPorCodigo(String codigo){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return sensorService.buscarSensorPorCodigo(codigo);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Collection<Sensor> listarSensorPorAtivo(AtivoIndustrial ativoIndustrial){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return sensorService.listarSensorPorAtivo(ativoIndustrial);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void atualizarValorAtualSensor(Integer id, Double valor){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO);
            sensorService.atualizarValorSensor(id, valor);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarSensor(String codigo){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            sensorService.inativarSensor(codigo);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }
}