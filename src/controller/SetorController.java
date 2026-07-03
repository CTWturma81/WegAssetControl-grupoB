package controller;

import exception.AppException;
import model.entity.Setor;
import model.service.SetorService;

import java.util.Collection;

public class SetorController {

    SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    public boolean cadastrarSetor(Setor setor){
        try{
            setorService.cadastrarSetor(setor);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Collection<Setor> listarSetor(){
        try{
            return setorService.listarSetores();
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Setor buscarSetor(Integer id){
        try{
            return setorService.buscarPorId(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean editarSetor(Setor setor){
        try{
            setorService.editarSetor(setor);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean inativarSetor(Integer id){
        try{
            setorService.inativarSetor(id);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
