package view;

import enums.PerfilAcesso;
import model.entity.Usuario;

import java.util.Collection;
import java.util.Scanner;

public class UsuarioView {

    private Scanner scanner;

    public UsuarioView(){
        this.scanner = new Scanner(System.in);
    }

    public Usuario lerDadosUsuario(){
        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Login: ");
        String login = scanner.nextLine();

        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("Perfil ");
        System.out.println("1 - Administrador");
        System.out.println("2 - Supervisor");
        System.out.println("3 - Tecnico");
        System.out.println("4 - Operador");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        PerfilAcesso perfil = switch (opcao){
            case 1 -> PerfilAcesso.ADMINISTRADOR;
            case 2 -> PerfilAcesso.SUPERVISOR;
            case 3 -> PerfilAcesso.TECNICO;
            default -> PerfilAcesso.OPERADOR;
        };

        return new Usuario(nome, login, senha, perfil);
    }

    public Integer lerId(){
        System.out.println("Digite o Id: ");
        return scanner.nextInt();
    }

    public void exibirUsuario(Usuario usuario){
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Login: " + usuario.getLogin());
        System.out.println("Perfil: " + usuario.getPerfil());
        System.out.println("Ativo: " + usuario.getAtivo());
    }

    public void exibirUsuarios(Collection<Usuario> listaDeUsuarios){
        for(Usuario u : listaDeUsuarios){
            exibirUsuario(u);
            System.out.println("============================");
        }
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }
}