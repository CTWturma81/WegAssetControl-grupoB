package model.service;

import enums.NivelAlerta;
import enums.StatusAlerta;
import enums.StatusAtivo;
import exception.AppException;
import model.entity.Alerta;
import model.entity.AtivoIndustrial;
import model.repository.AlertaRepository;
import model.repository.AtivoRepository;

import java.time.LocalDate;
import java.util.Collection;

public class AlertaService {

    private AlertaRepository alertaRepository;
    private AtivoRepository ativoRepository;

    public AlertaService(AtivoRepository ativoRepository, AlertaRepository alertaRepository) {
        this.ativoRepository = ativoRepository;
        this.alertaRepository = alertaRepository;
    }

    public void cadastrarAlerta(Alerta alerta) {

        if (alerta.getAtivoIndustrial() == null) {
            throw new AppException("ERRO: Ativo Industrial não pode ser vazio");
        }

        if (alerta.getSensor() == null) {
            throw new AppException("ERRO: Sensor não pode ser vazio");
        }

        if (alerta.getAtivoIndustrial().getStatusAtivo() == StatusAtivo.INATIVO) {
            throw new AppException("ERRO: Ativo não pode ser INATIVO");
        }

        if (alerta.getSensor().getStatusAtivo() == StatusAtivo.INATIVO) {
            throw new AppException("ERRO: Sensor não pode ser INATIVO");
        }

        alterarStatusAtivo(alerta.getAtivoIndustrial(), alerta.getNivelAlerta());

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

    public void finalizarAlerta(Integer id) {

        Alerta alerta = alertaRepository.buscarPorId(id);

        if (alerta == null) {
            throw new AppException("ERRO: O alerta com esse ID não foi encontrado");
        }

        if (alerta.getStatusAlerta() == StatusAlerta.FINALIZADO) {
            throw new AppException("ERRO: Esse alerta já está finalizado");
        }

        alerta.setStatusAlerta(StatusAlerta.FINALIZADO);
        alerta.setDataFechamento(LocalDate.now());

        alertaRepository.atualizar(alerta);
    }

    public Collection<Alerta> listarTodos() {
        Collection<Alerta> alertas = alertaRepository.listarTodos();

        if (alertas.isEmpty()) {
            throw new AppException("ERRO: Nenhum alerta cadastrado.");
        }

        return alertas;
    }

    public Collection<Alerta> listarAbertos() {
        Collection<Alerta> alertas = alertaRepository.listarAbertos();

        if (alertas.isEmpty()) {
            throw new AppException("ERRO: Nenhum alerta aberto encontrado.");
        }

        return alertas;
    }

    public Collection<Alerta> listarPorAtivo(Integer id) {

        Collection<Alerta> alertas = alertaRepository.listarPorAtivo(id);

        if (alertas.isEmpty()) {
            throw new AppException("ERRO: Nenhum alerta encontrado para esse ativo.");
        }

        return alertas;
    }

    public Collection<Alerta> listarCriticosAbertosPorAtivo(Integer id) {

        Collection<Alerta> alertas = alertaRepository.listarCriticosAbertosPorAtivo(id);

        if (alertas.isEmpty()) {
            throw new AppException("ERRO: Nenhum alerta crítico aberto encontrado para esse ativo.");
        }

        return alertas;
    }
}