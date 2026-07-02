package controller;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.service.AtivoService;

import java.sql.SQLOutput;
import java.util.Collection;

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

    public Collection<AtivoIndustrial> listarAtivoIndustrial(){
        try{
            return ativoService.listarAtivos();
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AtivoIndustrial buscarAtivoIndustrialPorID(Integer id){
        try{
            return ativoService.buscarPorId(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
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

    public Collection<AtivoIndustrial> listarAtivoIndustrialStatus(StatusAtivo statusAtivo){
        try{
            return ativoService.listarPorStatus(statusAtivo);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Collection<AtivoIndustrial> listarAtivoIndustrialSetor(Integer idSetor){
        try{
            return ativoService.listarPorSetor(idSetor);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
