package controller;

import exception.AppException;
import model.entity.Tecnico;
import model.service.TecnicoService;

import java.util.Collection;

public class TecnicoController {

    TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    public boolean cadastrarTecnico(Tecnico tecnico){
        try{
            tecnicoService.cadastrarTecnico(tecnico);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Collection<Tecnico> listarTecnico(){
        try{
            return tecnicoService.listarTecnicos();
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Tecnico buscarTecnico(Integer id){
        try{
            return tecnicoService.buscarPorId(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean editarTecnico(Tecnico tecnico){
        try{
            tecnicoService.editarTecnico(tecnico);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean inativarTecnico(Integer id){
        try{
            tecnicoService.inativarTecnico(id);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Tecnico buscarTecnicos(Integer id){
        try{
            return tecnicoService.buscarPorId(id);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
