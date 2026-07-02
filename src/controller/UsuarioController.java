package controller;

import enums.PerfilAcesso;
import exception.AppException;
import model.entity.Usuario;
import model.service.AuthService;
import model.service.UsuarioService;
import view.UsuarioView;

import java.util.Collection;

public class UsuarioController {

    private UsuarioService usuarioService;
    private AuthService authService;

    public UsuarioController(UsuarioService usuarioService, AuthService authService){
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    public void cadastrarUsuario(Usuario usuario){
        try {
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            usuarioService.cadastrarUsuario(usuario);
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarUsuarios(){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR, PerfilAcesso.SUPERVISOR);
            usuarioService.listarUsuarios();
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void atualizarUsuario(Integer id, Usuario novoUsuario){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR);
            usuarioService.atualizarUsuario(id, novoUsuario);
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarUsuario(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR);
            usuarioService.inativarUsuario(id);
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void buscarUsuario(Integer id){
        try{
            authService.validarPermissao(PerfilAcesso.ADMINISTRADOR);
            usuarioService.buscarPorId(id);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

}
