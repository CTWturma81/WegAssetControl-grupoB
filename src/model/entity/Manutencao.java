package model.entity;

import enums.StatusManutencao;
import exception.AppException;

import java.time.LocalDate;

public class Manutencao {

    private Integer id;
    private AtivoIndustrial ativoIndustrial;
    private Tecnico tecnico;
    private String descricaoProblema;
    private String observacaoTecnica;
    private StatusManutencao status;
    private LocalDate dataAbertura;
    private LocalDate dataFinalizacao;

    public Manutencao(AtivoIndustrial ativoIndustrial, Tecnico tecnico, String descricaoProblema, String observacaoTecnica, StatusManutencao status){
        setAtivoIndustrial(ativoIndustrial);
        setTecnico(tecnico);
        setDescricaoProblema(descricaoProblema);
        setObservacaoTecnica(observacaoTecnica);
        setStatus(status);
        this.dataAbertura = LocalDate.now();
    }

    public void setId (Integer id){
        if(id == null || id <= 0){
            throw new AppException("ERRO: O ID não pode ser nulo");
        }
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public void setAtivoIndustrial(AtivoIndustrial ativoIndustrial) {
        if (ativoIndustrial == null){
            throw new AppException("ERRO: O ativo industrial não pode ser nulo.");
        }
        this.ativoIndustrial = ativoIndustrial;
    }

    public AtivoIndustrial getAtivoIndustrial(){
        return ativoIndustrial;
    }

    public void setTecnico(Tecnico tecnico){
        if (tecnico == null){
            throw new AppException("ERRO: O técnico não pode ser nulo.");
        }
        this.tecnico = tecnico;
    }

    public Tecnico getTecnico(){
        return tecnico;
    }

    public void setDescricaoProblema(String descricaoProblema){
        if (descricaoProblema.isBlank()){
            throw new AppException("ERRO: A descrição do problema deve ser preenchida.");
        }
        this.descricaoProblema = descricaoProblema;
    }

    public String getDescricaoProblema(){
        return descricaoProblema;
    }

    public void setObservacaoTecnica(String observacaoTecnica){
        if(observacaoTecnica.isBlank()){
            throw new AppException("ERRO: A observação técnica deve ser preenchida.");
        }
        this.observacaoTecnica = observacaoTecnica;
    }

    public String getObservacaoTecnica(){
        return observacaoTecnica;
    }

    public void setStatus(StatusManutencao status) {
        if (status == null){
            throw new AppException("ERRO: O status não pode ser nulo.");
        }
        this.status = status;
    }

    public StatusManutencao getStatus(){
        return status;
    }

    public void setDataAbertura(LocalDate dataAbertura){
        if (dataAbertura == null){
            throw new AppException("ERRO: A data de abertura não pode ser nula.");
        }
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataAbertura(){
        return dataAbertura;
    }

    public void setDataFinalizacao(LocalDate dataFinalizacao){
        if (dataFinalizacao == null){
            throw new AppException("ERRO: A data de finalização não pode ser nula.");
        }
        this.dataFinalizacao = dataFinalizacao;
    }

    public LocalDate getDataFinalizacao(){
        return dataFinalizacao;
    }

    @Override
    public String toString(){
        return "Manutenção{" +
                "id=" + id +
                ", ativoIndustral=" + ativoIndustrial +
                ", tecnico=" + tecnico +
                ", descricaoProblema='" + descricaoProblema + '\'' +
                ", observacaoTecnica='" + observacaoTecnica + '\'' +
                ", status=" + status +
                ", dataAbertura=" + dataAbertura +
                ", dataFinalizacao=" + dataFinalizacao +
                '}';
    }
}