package model.service;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.repository.SensorRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<Sensor> listarSensor(){
        ArrayList<Sensor> sensor = new ArrayList<>(sensorRepository.listarTodos());

        if(sensor.isEmpty()){
            throw new AppException("Erro: nenhum sensor cadastrado");
        }

        return sensor;
    }

    public void buscarSensorPorId(Integer id){
        if(sensorRepository.buscarPorId(id) == null){
            throw new AppException("Erro: nenhum sensor foi encontrado");
        }
    }

    public void buscarSensorPorCodigo(String codigo){
        if(sensorRepository.buscarPorCodigo(codigo) == null){
            throw new AppException("Erro: nenhum sensor foi encontrado");
        }
    }

    public List<Sensor> listarSensorPorAtivo(AtivoIndustrial ativoIndustrial){
        ArrayList<Sensor> sensor = new ArrayList<>(sensorRepository.listarPorAtivo(ativoIndustrial));

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
