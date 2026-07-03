package controller;

import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.service.SensorService;

import java.util.Collection;


public class SensorController {

    SensorService sensorService;

    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }

    public boolean cadastrarSensor(Sensor sensor){
        try{
            sensorService.cadastrarSensor(sensor);
            return true;
        }catch (AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Collection<Sensor> listarSensor(){
        try{
            return sensorService.listarSensor();
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Sensor buscarSensorPorId(Integer id){
        try{
            return sensorService.buscarSensorPorId(id);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Sensor buscarSensorPorCodigo(String codigo){
        try{
            return sensorService.buscarSensorPorCodigo(codigo);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Collection<Sensor> listarSensorPorAtivo(AtivoIndustrial ativoIndustrial){
        try{
            return sensorService.listarSensorPorAtivo(ativoIndustrial);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean atualizarValorAtualSensor(Integer id, Double valor){
        try{
            sensorService.atualizarValorSensor(id, valor);
            return true;
        }catch (AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean inativarSensor(String codigo){
        try{
            sensorService.inativarSensor(codigo);
            return true;
        }catch (AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}