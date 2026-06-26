package model.service;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.Sensor;
import model.repository.SensorRepository;

import java.util.ArrayList;

public class SensorService {

    SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    public void cadastrarSensor(Sensor sensor){

        ArrayList<Sensor> sensores = new ArrayList<>(sensorRepository.listarTodos());

        if(sensores.contains(sensor.getCodigo())){
            throw new AppException("Erro: codigo do sensor tem que ser único");
        }
        if(sensores.contains(sensor.getTipo())){
            throw new AppException("Erro: tipo do sensor tem que ser único");
        }
        if(sensorRepository.listarPorAtivo(sensor.getAtivoIndustrial()).isEmpty()){
            throw new AppException("Erro: O Ativo industrial deve ter vinculo com o sensor");
        }
        if(sensor.getStatusAtivo() == StatusAtivo.INATIVO){
            throw new AppException("Erro: Ao cadastrar sensor ele deve estar ativadado");
        }
        if(sensor.getValorAtual() < 0){
            throw new AppException("Erro: Valor atual não pode ser negativo");
        }

        sensorRepository.salvar(sensor);
    }

    public void atualizarValorSensor(Sensor sensor){
        sensorRepository.buscarPorCodigo(sensor).setValorAtual(sensor.getValorAtual());
    }

    public void intivarSensor(Sensor sensor, StatusAtivo statusAtivo){
        String inativo = "INATIVO";
        sensorRepository.buscarPorCodigo(sensor).setStatusAtivo(statusAtivo.valueOf(inativo));
    }
}
