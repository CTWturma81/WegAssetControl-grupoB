package model.entity;

import enums.StatusAtivo;

public class AtivoIndustrial {

    private Integer id;
    private String codigoPatrimonial;
    private String nome;
    private String tipo;
    private String ciclagem;
    private String modelo;
    private Setor setor;
    private StatusAtivo status;

    public AtivoIndustrial(String codigoPatrimonial, String nome, String tipo, String ciclagem, String modelo, Setor setor){
        setCodigoPatromonial(codigoPatrimonial);
        setNome(nome);
        setTipo(tipo);
        setCiclagem(ciclagem);
        setModelo(modelo);
        setSetor(setor);
        this.status = StatusAtivo.NORMAL;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoPatrimonial(){
        return codigoPatrimonial;
    }

    public void setCodigoPatromonial(String codigoPatrimonial){
        if(!codigoPatrimonial.isBlank()) {
            this.codigoPatrimonial = codigoPatrimonial;
        }else{
            throw new RuntimeException("ERRO: Codigo patrimonial não pode estar vazio");
        }
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        if(!nome.isBlank()){
            this.nome = nome;
        }else{
            throw new RuntimeException("ERRO: Nome não pode estar vazio");
        }
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        if(!tipo.isBlank()){
            this.tipo = tipo;
        }else {
            throw new RuntimeException("ERRO: Tipo não pode estar vazio");
        }
    }

    public String getCiclagem(){
        return ciclagem;
    }

    public void setCiclagem(String ciclagem){
        if(!ciclagem.isBlank()){
            this.ciclagem = ciclagem;
        }else{
            throw new RuntimeException("ERRO: Ciclagem não pode estar vazio");
        }
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        if(!modelo.isBlank()){
            this.modelo = modelo;
        }else{
            throw new RuntimeException("ERRO: Modelo não pode estart vazio");
        }
    }

    public Setor getSetor(){
        return setor;
    }

    public void setSetor(Setor setor){
        if(setor != null){
            this.setor = setor;
        }else{
            throw new RuntimeException("ERRO: Setor não pode estar nulo");
        }
    }

    public StatusAtivo getStatusAtivo(){
        return status;
    }

    public void setStatusAtivos(StatusAtivo status){
        if(status != null){
            this.status = status;
        }else{
            throw new RuntimeException("ERRO: Status ativos não pode ser nulo");
        }
    }

    @Override
    public String toString() {
        return "-----------------------------\n" +
                "ID: " + id + "\n" +
                "Código Patrimonial: " + codigoPatrimonial + "\n" +
                "Nome: " + nome + "\n" +
                "Tipo: " + tipo + "\n" +
                "Ciclagem: " + ciclagem + "\n" +
                "Modelo: " + modelo + "\n" +
                "Setor: " + setor.getNome() + "\n" +
                "Status: " + status + "\n" +
                "-----------------------------";
    }
}
