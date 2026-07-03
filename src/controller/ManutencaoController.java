package controller;

import exception.AppException;
import model.entity.Manutencao;
import model.entity.Tecnico;
import model.service.ManutencaoService;

import java.util.Collection;

public class ManutencaoController {

    ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService){
        this.manutencaoService = manutencaoService;
    }

    public boolean abrirManutencao(Manutencao manutencao){
        try{
            manutencaoService.abrirManutencao(manutencao);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atribuirTecnicoManutencao(Integer id, Tecnico tecnico){
        try{
            manutencaoService.atribuirTecnico(id, tecnico);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean registrarObservacao(Integer id, String observacaoTecnica){
        try{
            manutencaoService.registrarObservacao(id, observacaoTecnica);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean finalizarManutencao(Integer id){
        try{
            manutencaoService.finalizarManutencao(id);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Collection<Manutencao> listarManutencaoAberta(){
        try{
            return manutencaoService.listarManutencoesAbertas();
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Collection<Manutencao> listarManutencaoTecnico(Integer id){
        try{
            return manutencaoService.listarManutencaoTecnico(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}