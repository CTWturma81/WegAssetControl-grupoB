package controller;

import exception.AppException;
import model.service.SetorService;
import view.GerenciarSetores;

public class SetorController {

    SetorService setorService;
    GerenciarSetores gerenciarSetores;

    public SetorController(GerenciarSetores gerenciarSetores,SetorService setorService) {
        this.gerenciarSetores = gerenciarSetores;
        this.setorService = setorService;
    }

    public void cadastrarSetor(){
        try{
            setorService.cadastrarSetor(gerenciarSetores.lerDadosSetor());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void listarSetor(){
        try{
            setorService.listarSetores();
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void buscarSetor(){
        try{
            setorService.buscarPorId(gerenciarSetores.lerId());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void editarSetor(){
        try{
            setorService.editarSetor(gerenciarSetores.lerDadosSetor());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void inativarSetor(){
        try{
            setorService.inativarSetor(gerenciarSetores.lerId());
        }catch(AppException e){
            e.getMessage();
        }
    }
}
