package model.service;

import enums.NivelAlerta;
import enums.StatusAlerta;
import enums.StatusAtivo;
import exception.AppException;
import model.entity.Alerta;
import model.entity.AtivoIndustrial;
import model.entity.Sensor;
import model.repository.AlertaRepository;
import model.repository.AtivoRepository;

import java.time.LocalDate;
import java.util.List;

public class AlertaService {

    private AlertaRepository alertaRepository;
    private AtivoRepository ativoRepository;

    public AlertaService(AtivoRepository ativoRepository, AlertaRepository alertaRepository) {
        this.ativoRepository = ativoRepository;
        this.alertaRepository = alertaRepository;
    }

    public void abrirAlerta(AtivoIndustrial ativoIndustrial, Sensor sensor, String descricao, NivelAlerta nivelAlerta) {

        if (ativoIndustrial == null) {
            throw new AppException ("ERRO: Ativo Industrial não pode ser vazio");
        }

        if(sensor == null) {
            throw new AppException("ERRO: Sensor não pode ser vazio");
        }

        if (ativoIndustrial.getStatusAtivo() == StatusAtivo.INATIVO) {
            throw new AppException("ERRO: Ativo não pode ser INATIVO");
        }

        if(sensor.getStatusAtivo() == StatusAtivo.INATIVO) {
            throw new AppException("ERRO: Status não pode ser INATIVO");
        }

        Alerta alerta = new Alerta(ativoIndustrial, sensor, descricao, nivelAlerta);

        alterarStatusAtivo(ativoIndustrial, nivelAlerta);

        alertaRepository.salvar(alerta);
    }

    private void alterarStatusAtivo(AtivoIndustrial ativoIndustrial, NivelAlerta nivelAlerta) {

        if (nivelAlerta == NivelAlerta.ALTO) {
            ativoIndustrial.setStatusAtivos(StatusAtivo.CRITICO);
        } else if (nivelAlerta == NivelAlerta.MEDIO) {
            ativoIndustrial.setStatusAtivos(StatusAtivo.ATENCAO);
        }

        ativoRepository.atualizarAtivo(ativoIndustrial.getId(), ativoIndustrial);
    }


    public void finalizarAlerta(int id) {

        Alerta alerta = alertaRepository.buscarPorId(id);

        if(alerta == null) {
            throw new AppException("ERRO: O alerta com esse ID não foi encontrado");
        }

        if(alerta.getStatusAlerta() == StatusAlerta.FINALIZADO) {
            throw new AppException("ERRO: Esse alerta já está finalizado");
        }

        alerta.setStatusAlerta(StatusAlerta.FINALIZADO);
        alerta.setDataFechamento(LocalDate.now());

        alertaRepository.atualizar(alerta);
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.listarTodos();
    }

    public List<Alerta> listarAbertos() {
        return alertaRepository.listarAbertos();
    }

    public List<Alerta> listarPorAtivo(AtivoIndustrial ativoIndustrial) {
        if (ativoIndustrial == null) {
            throw new AppException("ERRO: Ativo Industrial não pode ser vazio");
        }
        return alertaRepository.listarPorAtivo(ativoIndustrial);
    }

    public List<Alerta> listarCriticosAbertosPorAtivo(AtivoIndustrial ativoIndustrial) {
        if(ativoIndustrial == null) {
            throw new AppException("ERRO: Ativo Industrial não pode ser vazio");
        }
        return alertaRepository.listarCriticosAbertosPorAtivo(ativoIndustrial);
    }
}