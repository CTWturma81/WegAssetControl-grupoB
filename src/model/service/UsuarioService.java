package model.service;

import enums.PerfilAcesso;
import exception.AppException;
import model.entity.Usuario;
import model.repository.UsuarioRepository;

import java.util.Collection;


public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario usuario){

        if(usuarioRepository.buscarPorLogin(usuario.getLogin()) != null){
            throw new AppException("ERRO: Login já está em uso.");
        }
        return usuarioRepository.criarUsuario(usuario);
    }

    public Collection<Usuario> listarUsuarios(){
        Collection<Usuario> usuarios = usuarioRepository.listarUsuarios();

        if(usuarios.isEmpty()){
            throw new AppException("ERRO: Nenhum usuário cadastrado.");
        }

        return usuarios;
    }

    public Usuario atualizarUsuario(Integer id, Usuario novoUsuario){
        Usuario usuario = usuarioRepository.buscarPorId(id);

        if(usuario == null){
            throw  new AppException("ERRO: Usuário não encontrado.");
        }

        if(!usuario.getLogin().equals(novoUsuario.getLogin()) && usuarioRepository.buscarPorLogin(novoUsuario.getLogin()) != null){
            throw new AppException("ERRO: Login já está em uso.");
        }

        return usuarioRepository.atualizarUsuario(id ,novoUsuario);
    }

    public void inativarUsuario(Integer id){
        Usuario usuario = usuarioRepository.buscarPorId(id);

        if(usuario == null){
            throw new AppException("ERRO: Usuário não encontrado.");
        }

        if(usuario.getPerfil() == PerfilAcesso.ADMINISTRADOR){
            int totalAdminsAtivos = 0;
            for(Usuario u : usuarioRepository.listarUsuarios()){
                if(u.getPerfil() == PerfilAcesso.ADMINISTRADOR && u.getAtivo()){
                    totalAdminsAtivos++;
                }
            }

            if(totalAdminsAtivos <= 1){
                throw new AppException("ERRO: Precisa de pelo menos um administrador ativo no sistema");
            }
        }

        usuario.setAtivo(false);
    }

    public Usuario buscarPorId(Integer id){
        Usuario usuario = usuarioRepository.buscarPorId(id);

        if(usuario == null){
            throw new AppException("ERRO: Usuario não encontrado.");
        }

        return usuario;
    }

}