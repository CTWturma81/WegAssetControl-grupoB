package controller;

import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.service.SensorService;

import java.util.Collection;
import java.util.List;


public class SensorController {

    SensorService sensorService;

    public SensorController(SensorService sensorService){
        this.sensorService = sensorService;
    }

    public void cadastrarSensor(Sensor sensor){
        try{
            sensorService.cadastrarSensor(sensor);
        }catch (AppException e){
            System.out.println(e.getMessage());
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

    public void atualizarValorAtualSensor(Integer id, Double valor){
        try{
            sensorService.atualizarValorSensor(id, valor);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarSensor(String codigo){
        try{
            sensorService.inativarSensor(codigo);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }
}
