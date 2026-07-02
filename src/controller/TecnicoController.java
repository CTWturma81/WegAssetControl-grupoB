package controller;

import enums.PerfilAcesso;
import exception.AppException;
import model.entity.Tecnico;
import model.service.AuthService;
import model.service.TecnicoService;

import java.util.Collection;

public class TecnicoController {

    private TecnicoService tecnicoService;
    private AuthService authService;

    public TecnicoController(TecnicoService tecnicoService, AuthService authService){
        this.tecnicoService = tecnicoService;
        this.authService = authService;
    }

    public void cadastrarTecnico(Tecnico tecnico){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            tecnicoService.cadastrarTecnico(tecnico);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public Collection<Tecnico> listarTecnico(){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR,
                    PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return tecnicoService.listarTecnicos();
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void editarTecnico(Integer id, Tecnico tecnico){
        try {
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            tecnicoService.editarTecnico(id, tecnico);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarTecnicos(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            tecnicoService.inativarTecnico(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }
}