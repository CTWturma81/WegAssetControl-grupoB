package controller;

import enums.PerfilAcesso;
import exception.AppException;
import model.entity.Setor;
import model.service.AuthService;
import model.service.SetorService;

import java.util.Collection;

public class SetorController {

    private SetorService setorService;
    private AuthService authService;

    public SetorController(SetorService setorService, AuthService authService) {
        this.setorService = setorService;
        this.authService = authService;
    }

    public void cadastrarSetor(Setor setor){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            setorService.cadastrarSetor(setor);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public Collection<Setor> listarSetor(){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return setorService.listarSetores();
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Setor buscarSetor(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR, PerfilAcesso.TECNICO, PerfilAcesso.OPERADOR);
            return setorService.buscarPorId(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void editarSetor(Setor setor){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            setorService.editarSetor(setor);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarSetor(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            setorService.inativarSetor(id);
        }catch(AppException e){
            System.out.println(e.getMessage());
        }
    }
}
