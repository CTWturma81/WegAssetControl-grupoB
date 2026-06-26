package model.repository;

import enums.StatusAtivo;
import model.entity.AtivoIndustrial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AtivoRepository {

    private HashMap<Integer, AtivoIndustrial> ativoRepository = new HashMap<>();
    private static Integer proximoID = 1;

    public AtivoIndustrial criarAtivo(AtivoIndustrial ativo) {

        ativo.setId(proximoID);
        ativoRepository.put(proximoID, ativo);

        proximoID++;

        return ativo;
    }

    public Collection<AtivoIndustrial> listarAtivos() {
        return ativoRepository.values();
    }

    public AtivoIndustrial buscarPorId(Integer id) {
        return ativoRepository.get(id);
    }

    public AtivoIndustrial buscarPorCodigoPatrimonial(String codigoPatrimonial) {

        for (AtivoIndustrial ativo : ativoRepository.values()) {

            if (ativo.getCodigoPatrimonial().equals(codigoPatrimonial)) {
                return ativo;
            }

        }

        return null;
    }

    public Collection<AtivoIndustrial> listarPorSetor(Integer idSetor) {

        ArrayList<AtivoIndustrial> ativos = new ArrayList<>();

        for (AtivoIndustrial ativo : ativoRepository.values()) {

            if (ativo.getSetor().getId().equals(idSetor)) {
                ativos.add(ativo);
            }

        }

        return ativos;
    }

    public Collection<AtivoIndustrial> listarPorStatus(StatusAtivo status) {

        ArrayList<AtivoIndustrial> ativos = new ArrayList<>();

        for (AtivoIndustrial ativo : ativoRepository.values()) {

            if (ativo.getStatusAtivo().equals(status)) {
                ativos.add(ativo);
            }

        }

        return ativos;
    }

    public AtivoIndustrial atualizarAtivo(Integer id, AtivoIndustrial ativoAtualizado) {

        if (ativoRepository.containsKey(id)) {

            ativoRepository.put(id, ativoAtualizado);

            return ativoAtualizado;
        }

        return null;
    }

    public boolean deletarAtivo(Integer id) {

        if (ativoRepository.containsKey(id)) {

            ativoRepository.remove(id);

            return true;
        }

        return false;
    }
}