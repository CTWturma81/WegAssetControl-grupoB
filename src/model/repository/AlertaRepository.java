package model.repository;

import enums.NivelAlerta;
import enums.StatusAlerta;
import model.entity.Alerta;
import model.entity.AtivoIndustrial;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class AlertaRepository {

    private HashMap<Integer, Alerta> alertas = new HashMap<>();
    private int proximoId = 1;

    public void salvar (Alerta alerta) {
        alerta.setId(proximoId);
        alertas.put(proximoId++, alerta);
    }

    public Alerta buscarPorId(int id) {
        return alertas.get(id);
    }

    public List<Alerta> listarTodos() {
        return new ArrayList<>(alertas.values());
    }

    public List <Alerta> listarAbertos() {
        List<Alerta> abertos = new ArrayList<>();
        for(Alerta alerta : alertas.values()) {
            if (alerta.getStatusAlerta() == StatusAlerta.ABERTO) {
                abertos.add(alerta);
            }
        }
        return abertos;
    }

    public List<Alerta> listarPorAtivo(AtivoIndustrial ativoIndustrial) {
        List<Alerta> resultado = new ArrayList<>();
        for (Alerta alerta : alertas.values()) {
            if(alerta.getAtivoIndustrial().getId() == ativoIndustrial.getId()) {
                resultado.add(alerta);
            }
        }
        return resultado;
    }

    public List<Alerta> listarCriticosAbertosPorAtivo (AtivoIndustrial ativoIndustrial) {
        List<Alerta> resultado = new ArrayList<>();
        for (Alerta alerta : alertas.values()) {
            if(alerta.getAtivoIndustrial().getId() == ativoIndustrial.getId()
                && alerta.getStatusAlerta() == StatusAlerta.ABERTO
                        && alerta.getNivelAlerta() == NivelAlerta.ALTO) {

                resultado.add(alerta);
            }
        }
        return resultado;
    }

    public void atualizar(Alerta alerta) {
        alertas.put(alerta.getId(), alerta);
    }
}