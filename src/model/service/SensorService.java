package model.service;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.repository.SensorRepository;

import java.util.ArrayList;
import java.util.Collection;

public class SensorService {

    SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    public void cadastrarSensor(Sensor sensor){

        if(sensorRepository.buscarPorCodigo(sensor.getCodigo()) != null){
            throw new AppException("Erro: código do sensor tem que ser único");
        }
        if(sensor.getStatusAtivo() == StatusAtivo.INATIVO){
            throw new AppException("Erro: Ao cadastrar sensor ele deve estar ativado");
        }
        if(sensor.getValorAtual() < 0){
            throw new AppException("Erro: Valor atual não pode ser negativo");
        }

        sensorRepository.salvar(sensor);
    }

    public Collection<Sensor> listarSensor(){
        ArrayList<Sensor> sensor = new ArrayList<>(sensorRepository.listarTodos());

        if(sensor.isEmpty()){
            throw new AppException("Erro: nenhum sensor cadastrado");
        }

        return sensor;
    }

    public Sensor buscarSensorPorId(Integer id){
        Sensor sensor = sensorRepository.buscarPorId(id);

        if(sensor == null){
            throw new AppException("Erro: nenhum sensor foi encontrado");
        }

        return sensor;
    }

    public Sensor buscarSensorPorCodigo(String codigo){
        Sensor sensor = sensorRepository.buscarPorCodigo(codigo);

        if(sensor == null){
            throw new AppException("Erro: nenhum sensor foi encontrado");
        }

        return sensor;
    }

    public Collection<Sensor> listarSensorPorAtivo(AtivoIndustrial ativoIndustrial){
        Collection<Sensor> sensor = new ArrayList<>(sensorRepository.listarPorAtivo(ativoIndustrial));

        if(sensor.isEmpty()){
            throw new AppException("Erro: nenhum sensor cadastrado");
        }

        return sensor;
    }

    public void atualizarValorSensor(Integer id, Double valorAtual){
        Sensor sensor = sensorRepository.buscarPorId(id);

        if(sensor == null){
            throw new AppException("Erro: nenhum sensor foi encontrado");
        }
        if(valorAtual < 0){
            throw new AppException("Erro: Valor atual não pode ser negativo");
        }

        sensor.setValorAtual(valorAtual);
    }

    public void inativarSensor(String codigo) {
        Sensor sensor = sensorRepository.buscarPorCodigo(codigo);

        if(sensor == null){
            throw new AppException("Erro: nenhum sensor foi encontrado");
        }

        if(sensor.getStatusAtivo() == StatusAtivo.INATIVO) {
            throw new AppException("ERRO: Sensor já está inativado");
        }

        sensor.setStatusAtivo(StatusAtivo.INATIVO);
    }
}