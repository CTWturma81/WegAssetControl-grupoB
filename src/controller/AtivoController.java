package controller;

import enums.PerfilAcesso;
import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.service.AtivoService;
import model.service.AuthService;

import java.sql.SQLOutput;
import java.util.Collection;

public class AtivoController {

    private AtivoService ativoService;
    private AuthService authService;

    public AtivoController(AtivoService ativoService, AuthService authService){
        this.ativoService = ativoService;
        this.authService = authService;
    }

    public void cadastrarAtivoIndustrial(AtivoIndustrial ativoIndustrial){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            ativoService.cadastrarAtivo(ativoIndustrial);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public Collection<AtivoIndustrial> listarAtivoIndustrial(){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return ativoService.listarAtivos();
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public AtivoIndustrial buscarAtivoIndustrialPorID(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return ativoService.buscarPorId(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void editarAtivoIndustrial(Integer id, AtivoIndustrial ativoIndustrial){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            ativoService.editarAtivo(id, ativoIndustrial);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarAtivoIndustrial(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            ativoService.inativarAtivo(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public Collection<AtivoIndustrial> listarAtivoIndustrialStatus(StatusAtivo statusAtivo){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return ativoService.listarPorStatus(statusAtivo);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Collection<AtivoIndustrial> listarAtivoIndustrialSetor(Integer idSetor){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return ativoService.listarPorSetor(idSetor);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
