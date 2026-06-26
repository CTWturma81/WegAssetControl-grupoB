package model.entity;

import enums.NivelAlerta;
import enums.StatusAlerta;
import exception.AppException;

import java.time.LocalDate;

public class Alerta {

    private int id;
    private AtivoIndustrial ativoIndustrial;
    private Sensor sensor;
    private String descricao;
    private NivelAlerta nivelAlerta;
    private StatusAlerta statusAlerta;
    private LocalDate dataAbertura;
    private LocalDate dataFechamento;


    public Alerta(AtivoIndustrial ativoIndustrial, Sensor sensor, String descricao, NivelAlerta nivelAlerta) {

        setAtivoIndustrial(ativoIndustrial);
        setSensor(sensor);
        setDescricao(descricao);
        setNivelAlerta(nivelAlerta);
        this.statusAlerta = StatusAlerta.ABERTO;
        this.dataAbertura = LocalDate.now();
        this.dataFechamento = null;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AtivoIndustrial getAtivoIndustrial() {
        return ativoIndustrial;
    }

    public void setAtivoIndustrial(AtivoIndustrial ativoIndustrial) {
        if (ativoIndustrial != null) {
            this.ativoIndustrial = ativoIndustrial;
        } else {
            throw new AppException("ERRO: Ativo Industrial não pode ser vazio");
        }
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        if (sensor != null) {
            this.sensor = sensor;
        } else {
            throw new AppException("ERRO: Sensor não pode ser vazio");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (!descricao.isBlank()) {
            this.descricao = descricao;
        } else {
            throw new AppException("ERRO: Descrição não pode ser vazia");
        }
    }

    public NivelAlerta getNivelAlerta() {
        return nivelAlerta;
    }

    public void setNivelAlerta(NivelAlerta nivelAlerta) {
        if (nivelAlerta != null) {
            this.nivelAlerta = nivelAlerta;
        } else {
            throw new AppException("ERRO: Nível de Alerta não pode ser vazio");
        }
    }

    public StatusAlerta getStatusAlerta() {
        return statusAlerta;
    }

    public void setStatusAlerta(StatusAlerta statusAlerta) {
        if (statusAlerta != null) {
            this.statusAlerta = statusAlerta;
        } else {
            throw new AppException("ERRO: Status de Alerta não pode ser vazio");
        }
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        if (dataAbertura != null) {
            this.dataAbertura = dataAbertura;
        } else {
            throw new AppException("ERRO: Data de Abertura não pode ser vazia");
        }
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String toString() {
        return "Alerta {" +
                ", Id: " + id +
                ", Ativo Industrial: " + ativoIndustrial +
                ", Sensor: " + sensor +
                ", Descrição: " + descricao +
                ", Nível de Alerta: " + nivelAlerta +
                ", Status de Alerta: " + statusAlerta +
                ", Data de Abertura: " + dataAbertura +
                ", Data de Fechamento: " + dataFechamento +
                "}";
    }
}