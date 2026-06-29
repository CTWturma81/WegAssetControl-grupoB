package controller;

import exception.AppException;
import model.service.SensorService;
import view.AtivoView;
import view.SensorView;

public class SensorController {

    SensorService sensorService;
    SensorView sensorView;
    AtivoView ativoView;
    public SensorController(SensorService sensorService, SensorView sensorView, AtivoView ativoView){
        this.sensorService = sensorService;
        this.sensorView = sensorView;
        this.ativoView = ativoView;
    }

    public void cadastrarSensor(){
        try{
            sensorService.cadastrarSensor(sensorView.lerDadosSensor(ativoView.lerDadosAtivo()));
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void listarSensor(){
        try{
            sensorService.listarSensor();
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void buscarSensorPorId(){
        try{
            sensorService.buscarSensorPorId(sensorView.lerId());
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void buscarSensorPorCodigo(){
        try{
            sensorService.buscarSensorPorCodigo(sensorView.lerDadosSensor(ativoView.lerDadosAtivo()));
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void listarSensorPorAtivo(){
        try{
            sensorService.listarSensorPorAtivo(ativoView.lerDadosAtivo());
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void atualizarValorAtualSensor(){
        try{
            sensorService.atualizarValorSensor(sensorView.lerId(), sensorView.lerValorAtual());
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void inativarSensor(){
        try{
            sensorService.intivarSensor(sensorView.lerDadosSensor(ativoView.lerDadosAtivo()),ativoView.lerStatus());
        }catch (AppException e){
            e.getMessage();
        }
    }
}
