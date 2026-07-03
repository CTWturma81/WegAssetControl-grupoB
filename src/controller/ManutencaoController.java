package controller;

import exception.AppException;
import model.entity.Manutencao;
import model.entity.Tecnico;
import model.service.ManutencaoService;

public class ManutencaoController {

    ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService){
        this.manutencaoService = manutencaoService;
    }

    public void abrirManutencao(Manutencao manutencao){
        try{
            manutencaoService.abrirManutencao(manutencao);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void atribuirTecnicoManutencao(Integer id, Tecnico tecnico){
        try{
            manutencaoService.atribuirTecnico(id, tecnico);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void registrarObservacao(Integer id, String observacaoTecnica){
        try{
            manutencaoService.registrarObservacao(id, observacaoTecnica);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void finalizarManutenção(Integer id){
        try{
            manutencaoService.finalizarManutencao(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarManutencaoAberta(){
        try{
            manutencaoService.listarManutencoesAbertas();
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarManutencaoTecnico(Integer id){
        try{
            manutencaoService.listarManutencaoTecnico(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }
}
