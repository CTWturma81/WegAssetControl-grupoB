package controller;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.service.AtivoService;

import java.util.Collection;

public class AtivoController {

    AtivoService ativoService;

    public AtivoController(AtivoService ativoService){
        this.ativoService = ativoService;
    }

    public boolean cadastrarAtivoIndustrial(AtivoIndustrial ativoIndustrial){
        try{
            ativoService.cadastrarAtivo(ativoIndustrial);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
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

    public boolean editarAtivoIndustrial(AtivoIndustrial ativoIndustrial){
        try{
            ativoService.editarAtivo(ativoIndustrial);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean inativarAtivoIndustrial(Integer id){
        try{
            ativoService.inativarAtivo(id);
            return true;
        }catch(AppException e){
            System.out.println(e.getMessage());
            return false;
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