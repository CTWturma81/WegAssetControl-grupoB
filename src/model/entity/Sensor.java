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
    private String unidadeMedida;
    private StatusAtivo statusAtivo;

    public Sensor(String codigo, String tipo, AtivoIndustrial ativoIndustrial, Double valorAtual, String unidadeMedida, StatusAtivo statusAtivo) {
        setCodigo(codigo);
        setTipo(tipo);
        setAtivoIndustrial(ativoIndustrial);
        setValorAtual(valorAtual);
        setUnidadeMedida(unidadeMedida);
        setStatusAtivo(statusAtivo);
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

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
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

    @Override
    public String toString() {
        return "-----------------------------\n" +
                "ID: " + id + "\n" +
                "Código: " + codigo + "\n" +
                "Tipo: " + tipo + "\n" +
                "Ativo Industrial: " + (ativoIndustrial != null ? ativoIndustrial.getNome() : "N/A") + "\n" +
                "Valor Atual: " + valorAtual + "\n" +
                "Unidade de Medida: " + unidadeMedida + "\n" +
                "Status: " + statusAtivo + "\n" +
                "-----------------------------";
    }
}