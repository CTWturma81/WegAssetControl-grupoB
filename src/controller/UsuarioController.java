package controller;

import exception.AppException;
import model.entity.Usuario;
import model.service.UsuarioService;
import view.UsuarioView;

import java.util.Collection;

public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    public void cadastrarUsuario(Usuario usuario){
        try {
            usuarioService.cadastrarUsuario(usuario);
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarUsuarios(){
        try{
            usuarioService.listarUsuarios();
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void atualizarUsuario(Integer id, Usuario novoUsuario){
        try{
            usuarioService.atualizarUsuario(id, novoUsuario);
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void inativarUsuario(Integer id){
        try{
            usuarioService.inativarUsuario(id);
        } catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

    public void buscarUsuario(Integer id){
        try{
            usuarioService.buscarPorId(id);
        }catch (AppException e){
            System.out.println(e.getMessage());
        }
    }

}
