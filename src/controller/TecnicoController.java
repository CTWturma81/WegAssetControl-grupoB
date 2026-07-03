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

    public void cadastrarTecnico(Tecnico tecnico){
        try{
            tecnicoService.cadastrarTecnico(tecnico);
        }catch(AppException e){
            System.out.println(e.getMessage());
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

    public void editarTecnico(Tecnico tecnico){
        try{
            tecnicoService.editarTecnico(tecnico);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarTecnico(Integer id){
        try{
            tecnicoService.inativarTecnico(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }
}