package controller;

import exception.AppException;
import model.entity.Usuario;
import model.service.UsuarioService;
import view.UsuarioView;

import java.util.Collection;

public class UsuarioController {

    private UsuarioService usuarioService;
    private UsuarioView usuarioView;

    public UsuarioController(UsuarioService usuarioService, UsuarioView usuarioView){
        this.usuarioService = usuarioService;
        this.usuarioView = usuarioView;
    }

    public void cadastrarUsuario(){
        try {
            Usuario usuario = usuarioView.lerDadosUsuario();
            usuarioService.cadastrarUsuario(usuario);
            usuarioView.exibirMensagem("Usuario cadastrado com sucesso!");
        } catch (AppException e){
            usuarioView.exibirMensagem(e.getMessage());
        }
    }

    public void listarUsuarios(){
        try{
            Collection<Usuario> usuarios = usuarioService.listarUsuarios();
            usuarioView.exibirUsuarios(usuarios);
        } catch (AppException e){
            usuarioView.exibirMensagem(e.getMessage());
        }
    }

    public void atualizarUsuario(){
        try{
            Integer id = usuarioView.lerId();
            Usuario novoUsuario = usuarioView.lerDadosUsuario();
            usuarioService.atualizarUsuario(id, novoUsuario);
            usuarioView.exibirMensagem("Usuario atualizado com sucesso!");
        } catch (AppException e){
            usuarioView.exibirMensagem(e.getMessage());
        }
    }

    public void inativarUsuario(){
        try{
            Integer id = usuarioView.lerId();
            usuarioService.inativarUsuario(id);
            usuarioView.exibirMensagem("Usuario inativado com sucesso!");
        } catch (AppException e){
            usuarioView.exibirMensagem(e.getMessage());
        }
    }

    public void buscarUsuario(){
        try{
            Integer id = usuarioView.lerId();
            Usuario usuario = usuarioService.buscarPorId(id);
            usuarioView.exibirUsuario(usuario);
        }catch (AppException e){
            usuarioView.exibirMensagem(e.getMessage());
        }
    }

}
