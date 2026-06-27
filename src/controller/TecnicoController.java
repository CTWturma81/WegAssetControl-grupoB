package controller;

import exception.AppException;
import model.entity.Tecnico;
import model.service.TecnicoService;
import view.GerenciarTecnicos;

public class TecnicoController {

    TecnicoService tecnicoService;
    GerenciarTecnicos gerenciarTecnicos;

    public TecnicoController(TecnicoService tecnicoService, GerenciarTecnicos gerenciarTecnicos){
        this.tecnicoService = tecnicoService;
        this.gerenciarTecnicos = gerenciarTecnicos;
    }

    public void cadastrarTecnico(){
        try{
            Tecnico tecnico = gerenciarTecnicos.lerDadosTecnicos();
            tecnicoService.cadastrarTecnico(tecnico);
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void listarTecnico(){
        try{
            tecnicoService.listarTecnicos();
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void editarTecnico(){
        try {
            Integer id = gerenciarTecnicos.lerId();
            Tecnico tecnico = gerenciarTecnicos.lerDadosTecnicos();
            tecnicoService.editarTecnico(id, tecnico);
        }catch (AppException e){
            e.getMessage();
        }
    }

    public void inativarTecnicos(){
        try{
            Integer id = gerenciarTecnicos.lerId();
            tecnicoService.inativarTecnico(id);
        }catch(AppException e){
            e.getMessage();
        }
    }
}
