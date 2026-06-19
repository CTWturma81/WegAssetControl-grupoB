package repository;

import exception.AppException;
import model.entity.Usuario;

import java.util.Collection;
import java.util.HashMap;

public class UsuarioRepository {

    private HashMap<Integer, Usuario> usuarioRepository = new HashMap<Integer, Usuario>();
    private static Integer proximoID = 1;

    public Usuario criarUsuario(Usuario usuario){
        try {
            usuarioRepository.put(proximoID, usuario);
            proximoID++;
            return usuario;
        } catch (Exception e) {
            throw new AppException("ERRO: Não foi possível criar o usuário. " + e.getMessage());
        }
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
        throw new AppException("ERRO: Usuário com email " + email + " não encontrado.");
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuarioAtualizado){
        if(usuarioRepository.containsKey(id)){
            usuarioRepository.put(id, usuarioAtualizado);
            return usuarioAtualizado;
        }else{
            throw new AppException("ERRO: Usuário com ID " + id + " não encontrado.");
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