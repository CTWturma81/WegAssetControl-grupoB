package model.entity;

public class Setor {

    private static int contadorId = 1;

    private int id;
    private String nome;
    private String descricao;

    public Setor(String nome, String descricao) {

        this.id = contadorId++;
        setNome(nome);
        setDescricao(descricao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(!nome.isBlank()) {
            this.nome = nome;
        }
        else {
            throw new RuntimeException("ERRO: Nome não pode ser vazio");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(!descricao.isBlank()) {
            this.descricao = descricao;
        }
        else{
            throw new RuntimeException("ERRO: Descrição não pode ser vazia");
        }
    }

    public String toString() {
        return "Setor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}