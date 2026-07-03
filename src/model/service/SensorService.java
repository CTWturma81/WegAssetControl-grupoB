package model.service;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.repository.SensorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SensorService {

    SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    public void cadastrarSensor(Sensor sensor){

        ArrayList<Sensor> sensores = new ArrayList<>(sensorRepository.listarTodos());

        if(sensores.contains(sensor.getCodigo())){
            throw new AppException("Erro: código do sensor tem que ser único");
        }
        if(sensores.contains(sensor.getTipo())){
            throw new AppException("Erro: tipo do sensor tem que ser único");
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

    public void atualizarValorSensor(int id,Double valorAtual){
        sensorRepository.buscarPorId(id).setValorAtual(valorAtual);
    }

    public void inativarSensor(String codigo) {
        sensorRepository.buscarPorCodigo(codigo).setStatusAtivo(StatusAtivo.valueOf("INATIVO"));
    }
}
