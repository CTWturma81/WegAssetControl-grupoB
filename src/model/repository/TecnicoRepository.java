package model.repository;
import model.entity.Tecnico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TecnicoRepository {

    private HashMap<Integer, Tecnico> tecnicoRepositorio;

    private Integer contadorId;

    public TecnicoRepository(){
        this.tecnicoRepositorio = new HashMap<>();
        this.contadorId = 1;
    }

    public Tecnico salvar(Tecnico tecnico){
        tecnico.setId(contadorId);
        tecnicoRepositorio.put(tecnico.getId(), tecnico);
        contadorId++;
        return tecnico;
    }

    public Tecnico buscarPorId (Integer id){
        return tecnicoRepositorio.get(id);
    }

    public Tecnico buscarPorMatricula (String matricula){

        for (Tecnico tecnico : tecnicoRepositorio.values()){
            if (tecnico.getMatricula().equals(matricula)){
                return tecnico;
            }
        } return null;
    }

    public List<Tecnico> listarTodos(){
        return new ArrayList<>(tecnicoRepositorio.values());
    }

    public List<Tecnico> listarAtivos(){

        List<Tecnico> tecnicosAtivos = new ArrayList<>();

        for (Tecnico tecnico : tecnicoRepositorio.values()){
            if (tecnico.isAtivo()){
                tecnicosAtivos.add(tecnico);
            }
        }
        return tecnicosAtivos;
    }

    public Tecnico atualizar(Tecnico tecnico){
        Tecnico tecnicoAtualizado = null;
        if (tecnico.getId() != null && tecnicoRepositorio.containsKey(tecnico.getId())){
            tecnicoRepositorio.put(tecnico.getId(), tecnico);
            tecnicoAtualizado = tecnico;
        }
        return tecnicoAtualizado;
    }
}
