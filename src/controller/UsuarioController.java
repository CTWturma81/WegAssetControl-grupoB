package controller;

import exception.AppException;
import model.entity.Usuario;
import model.service.UsuarioService;

import java.util.Collection;

public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    public boolean cadastrarUsuario(Usuario usuario){
        try {
            usuarioService.cadastrarUsuario(usuario);
            return true;
        } catch (AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Collection<Usuario> listarUsuarios(){
        try{
            return usuarioService.listarUsuarios();
        } catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean atualizarUsuario(Usuario novoUsuario){
        try{
            usuarioService.atualizarUsuario(novoUsuario);
            return true;
        } catch (AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean inativarUsuario(Integer id){
        try{
            usuarioService.inativarUsuario(id);
            return true;
        } catch (AppException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Usuario buscarUsuario(Integer id){
        try{
            return usuarioService.buscarPorId(id);
        }catch (AppException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}