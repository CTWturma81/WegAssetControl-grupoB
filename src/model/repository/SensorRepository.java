package model.repository;

import model.entity.AtivoIndustrial;
import model.entity.Sensor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SensorRepository {

    Map<Integer, Sensor> sensorMap = new HashMap<>();
    private static Integer idcontador = 1;

    public void salvar(Sensor sensor){
        sensorMap.put(idcontador++,sensor);
    }

    public Sensor buscarPorId(int id){
        return sensorMap.get(id);
    }

    public Sensor buscarPorCodigo(String codigo) {

        for (Sensor sensorCodigo : sensorMap.values()) {

            if (sensorCodigo.getCodigo() != null && sensorCodigo.getCodigo().equals(codigo)) {
                return sensorCodigo;
            }
        }
        return null;
    }

    public Collection<Sensor> listarTodos(){
        return sensorMap.values();
    }

    public Collection<Sensor> listarPorAtivo(AtivoIndustrial ativoIndustrial) {
        return sensorMap.values().stream()
                .filter(sensor -> sensor.getAtivoIndustrial() != null)
                .filter(sensor -> Objects.equals(sensor.getAtivoIndustrial(), ativoIndustrial))
                .collect(Collectors.toSet());
    }

    public Sensor Atualizar(Integer id,Sensor sensor){
        if(sensorMap.containsKey(id)){
            return sensorMap.replace(id,sensor);
        }else{
            return null;
        }
    }
}