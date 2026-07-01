package controller;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.service.AtivoService;

import java.sql.SQLOutput;

public class AtivoController {

    AtivoService ativoService;


    public AtivoController(AtivoService ativoService){
        this.ativoService = ativoService;
    }

    public void cadastrarAtivoIndustrial(AtivoIndustrial ativoIndustrial){
        try{
            ativoService.cadastrarAtivo(ativoIndustrial);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarAtivoIndustrial(){
        try{
            ativoService.listarAtivos();
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void buscarAtivoIndustrialPorID(Integer id){
        try{
            ativoService.buscarPorId(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void editarAtivoIndustrial(Integer id, AtivoIndustrial ativoIndustrial){
        try{
            ativoService.editarAtivo(id, ativoIndustrial);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarAtivoIndustrial(Integer id){
        try{
            ativoService.inativarAtivo(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarAtivoIndustrialStatus(StatusAtivo statusAtivo){
        try{
            ativoService.listarPorStatus(statusAtivo);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarAtivoIndustrialSetor(Integer idSetor){
        try{
            ativoService.listarPorSetor(idSetor);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }
}
