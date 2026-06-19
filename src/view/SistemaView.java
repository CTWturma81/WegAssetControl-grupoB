package view;

import controller.AuthController;
import model.entity.Usuario;

public class SistemaView {

    private AuthController authController;
    private MenuAdministrador menuAdministrador;
    private MenuSupervisor menuSupervisor;
    private MenuTecnico menuTecnico;
    private MenuOperador menuOperador;


    public SistemaView(AuthController authController, MenuAdministrador menuAdministrador, MenuSupervisor menuSupervisor, MenuTecnico menuTecnico, MenuOperador menuOperador){
        this.authController = authController;
        this.menuAdministrador = menuAdministrador;
        this.menuSupervisor = menuSupervisor;
        this.menuTecnico = menuTecnico;
        this.menuOperador = menuOperador;
    }

    public void iniciar(){
        while(true){

            Usuario usuario = authController.login();

            if(usuario == null){
                continue;
            }

            switch (usuario.getPerfil()){
                case ADMINISTRADOR -> menuAdministrador.menuAdm();
                case SUPERVISOR -> menuSupervisor.menuSupervisor();
                case TECNICO -> menuTecnico.menuTecnico();
                case OPERADOR -> menuOperador.menuOperador();
            }


        }
    }

}
