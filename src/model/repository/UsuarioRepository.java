package model.repository;

import exception.AppException;
import model.entity.Usuario;

import java.util.Collection;
import java.util.HashMap;

public class UsuarioRepository {

    private HashMap<Integer, Usuario> usuarioRepository = new HashMap<Integer, Usuario>();
    private static Integer proximoID = 1;

    public Usuario criarUsuario(Usuario usuario){
        usuario.setId(proximoID);
        usuarioRepository.put(proximoID, usuario);
        proximoID++;
        return usuario;
    }

    public Collection<Usuario> listarUsuarios(){
        return usuarioRepository.values();
    }

    public Usuario buscarPorId(Integer id){
        return usuarioRepository.get(id);
    }

    public Usuario buscarPorLogin(String email){
        for(Usuario usuario : usuarioRepository.values()){
            if(usuario.getLogin().equals(email)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado){
        if(usuarioRepository.containsKey(id)){
            usuarioRepository.put(id, usuarioAtualizado);
            return usuarioAtualizado;
        }else{
            return null;
        }
    }

    public boolean deletarUsuario(Integer id){
        if(usuarioRepository.containsKey(id)){
            usuarioRepository.remove(id);
            return true;
        }else{
            return false;
        }
    }
}