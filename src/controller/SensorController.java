package controller;

import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.service.SensorService;

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

    public List<Sensor> listarSensor(){
        try{
            return sensorService.listarSensor();
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void buscarSensorPorId(Integer id){
        try{
             sensorService.buscarSensorPorId(id);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void buscarSensorPorCodigo(String codigo){
        try{
            sensorService.buscarSensorPorCodigo(codigo);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarSensorPorAtivo(AtivoIndustrial ativoIndustrial){
        try{
            sensorService.listarSensorPorAtivo(ativoIndustrial);
        }catch (AppException e){
            System.out.println(e.getMessage());
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
