package model.service;

import enums.StatusAtivo;
import enums.StatusManutencao;
import exception.AppException;
import model.entity.Manutencao;
import model.entity.Tecnico;
import model.repository.ManutencaoRepository;

import java.time.LocalDate;

public class ManutencaoService {

    private ManutencaoRepository manutencaoRepositorio;

    public Manutencao abrirManutencao(Manutencao manutencao) {

        manutencao.setStatus(StatusManutencao.ABERTA);
        manutencao.setDataAbertura(LocalDate.now());
        manutencao.getAtivoIndustrial().setStatusAtivos(StatusAtivo.EM_MANUTENCAO);

        return manutencaoRepositorio.salvar(manutencao);
    }

    public Manutencao atribuirTecnico(Integer manutencaoId, Tecnico tecnico){
        Manutencao manutencao = buscarManutencao(manutencaoId);
        verificarSeFinalizada(manutencao);

        if (tecnico == null && !tecnico.isAtivo()){
            throw new AppException("ERRO: Não é possível atribuir um técnico inativo.");
        }

        manutencao.setTecnico(tecnico);
        return manutencaoRepositorio.atualizar(manutencao);

    }

    public Manutencao registrarObservacao(Integer manutencaoId, String observacaoTecnica){
        Manutencao manutencao = buscarManutencao(manutencaoId);
        verificarSeFinalizada(manutencao);

        if(observacaoTecnica == null || observacaoTecnica.isBlank()){
            throw new AppException("ERRO: A observação não pode ser vazia.");
        }

        manutencao.setObservacaoTecnica(observacaoTecnica);
        return manutencaoRepositorio.atualizar(manutencao);
    }

    public Manutencao finalizarManutencao(Integer manutencaoId){
        Manutencao manutencao = buscarManutencao(manutencaoId);
        verificarSeFinalizada(manutencao);

        if(manutencao.getTecnico() == null){
            throw new AppException("ERRO: Não é possível finalizar uma manutenção sem um técnico responsável.");
        }
        manutencao.setStatus(StatusManutencao.FINALIZADA);
        manutencao.setDataFinalizacao(LocalDate.now());

        return manutencaoRepositorio.atualizar(manutencao);
    }

    private Manutencao buscarManutencao (Integer id){
        Manutencao manutencao = manutencaoRepositorio.buscarPorId(id);
        if(manutencao == null){
            throw new AppException("ERRO: Manutenção não encontrada.");
        }
        return manutencao;
    }

    private void verificarSeFinalizada(Manutencao manutencao){
        if(manutencao.getStatus() == StatusManutencao.FINALIZADA){
            throw new AppException("ERRO: Esta manutenção já foi finalizada e não pode ser alterada.");
        }
    }
}


