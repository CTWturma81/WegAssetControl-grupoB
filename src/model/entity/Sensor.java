package model.entity;

import model.entity.*;
import enums.StatusAtivo;
import exception.AppException;

public class Sensor {

    private Integer id;
    private String codigo;
    private String tipo;
    private AtivoIndustrial ativoIndustrial;
    private Double valorAtual;
    private Double unidadeMedida;
    private StatusAtivo statusAtivo;

    public Sensor(String codigo, String tipo, AtivoIndustrial ativoIndustrial, Double valorAtual, Double unidadeMedida, StatusAtivo statusAtivo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.ativoIndustrial = ativoIndustrial;
        this.valorAtual = valorAtual;
        this.unidadeMedida = unidadeMedida;
        this.statusAtivo = statusAtivo;
    }

    public Integer getId() {
        return id;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if(!codigo.isBlank()){
            this.codigo = codigo;
        }else{
            throw new AppException("Erro: codigo não pode ser nulo");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(!tipo.isBlank()){
            this.tipo = tipo;
        }else {
            throw new AppException("Erro: tipo não pode ser nulo");
        }
    }

    public AtivoIndustrial getAtivoIndustrial() {
        return ativoIndustrial;
    }

    public void setAtivoIndustrial(AtivoIndustrial ativoIndustrial) {
        if(ativoIndustrial != null) {
            this.ativoIndustrial = ativoIndustrial;
        }else{
            throw new AppException("Erro: ativoIndustrial não pode ser nulo");
        }
    }

    public Double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Double valorAtual) {
        if(valorAtual != null){
            this.valorAtual = valorAtual;
        }else{
            throw new AppException("Erro: valor atual não pode ser nulo");
        }
    }

    public Double getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(Double unidadeMedida) {
        if(unidadeMedida != null){
            this.unidadeMedida = unidadeMedida;
        }else{
            throw new AppException("Erro: unidade de medida não pode ser nulo");
        }
    }

    public StatusAtivo getStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(StatusAtivo statusAtivo) {
        if(statusAtivo != null){
            this.statusAtivo = statusAtivo;
        }else{
            throw new AppException("Erro: status ativo não pode ser nulo");
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ativoIndustrial=" + ativoIndustrial +
                ", valorAtual=" + valorAtual +
                ", unidadeMedida=" + unidadeMedida +
                ", statusAtivo=" + statusAtivo +
                '}';
    }
}