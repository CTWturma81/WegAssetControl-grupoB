package controller;

import exception.AppException;
import model.entity.Tecnico;
import model.service.TecnicoService;
import view.GerenciarTecnicos;

import java.util.Collection;

public class TecnicoController {

    private TecnicoService tecnicoService;
    private GerenciarTecnicos gerenciarTecnicos;

    public TecnicoController(TecnicoService tecnicoService, GerenciarTecnicos gerenciarTecnicos){
        this.tecnicoService = tecnicoService;
        this.gerenciarTecnicos = gerenciarTecnicos;
    }

    public void cadastrarTecnico(Tecnico tecnico){
        try{
            tecnicoService.cadastrarTecnico(tecnico);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public Collection<Tecnico> listarTecnico(){
        try{
            return tecnicoService.listarTecnicos();
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void editarTecnico(Integer id, Tecnico tecnico){
        try {
            tecnicoService.editarTecnico(id, tecnico);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarTecnicos(Integer id){
        try{
            tecnicoService.inativarTecnico(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }
}
