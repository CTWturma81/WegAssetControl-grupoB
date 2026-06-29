package controller;

import exception.AppException;
import model.entity.AtivoIndustrial;
import model.service.AtivoService;
import view.AtivoView;

public class AtivoController {

    AtivoService ativoService;
    AtivoView ativoView;

    public AtivoController(AtivoService ativoService, AtivoView ativoView){
        this.ativoService = ativoService;
        this.ativoView = ativoView;
    }

    public void cadastrarAtivoIndustrial(){
        try{
            ativoService.cadastrarAtivo(ativoView.lerDadosAtivo());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void listarAtivoIndustrial(){
        try{
            ativoService.listarAtivos();
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void buscarAtivoIndustrialPorID(){
        try{
            ativoService.buscarPorId(ativoView.lerId());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void editarAtivoIndustrial(){
        try{
            ativoService.editarAtivo(ativoView.lerId(), ativoView.lerDadosAtivo());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void inativarAtivoIndustrial(){
        try{
            ativoService.inativarAtivo(ativoView.lerId());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void listarAtivoIndustrialStatus(){
        try{
            ativoService.listarPorStatus(ativoView.lerStatus());
        }catch(AppException e){
            e.getMessage();
        }
    }

    public void listarAtivoIndustrialSetor(){
        try{
            ativoService.listarPorSetor(ativoView.lerIdSetor());
        }catch(AppException e){
            e.getMessage();
        }
    }
}
