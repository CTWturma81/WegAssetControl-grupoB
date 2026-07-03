package controller;

import exception.AppException;
import model.entity.Alerta;
import model.service.AlertaService;

import java.util.Collection;

public class AlertaController {

    private AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    public boolean cadastrarAlerta(Alerta alerta) {
        try {
            alertaService.cadastrarAlerta(alerta);
            return true;
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Collection<Alerta> listarTodosAlertas() {
        try {
            return alertaService.listarTodos();
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean finalizarAlerta(Integer id) {
        try {
            alertaService.finalizarAlerta(id);
            return true;
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Collection<Alerta> listarAbertos() {
        try {
            return alertaService.listarAbertos();
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Collection<Alerta> listarPorAtivo(Integer id) {
        try {
            return alertaService.listarPorAtivo(id);
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Collection<Alerta> listarCriticosAbertosPorAtivo(Integer id) {
        try {
            return alertaService.listarCriticosAbertosPorAtivo(id);
        } catch (AppException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
