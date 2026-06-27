package model.repository;

import enums.StatusManutencao;
import model.entity.Manutencao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManutencaoRepository {

    private HashMap<Integer, Manutencao> manutencaoRepositorio;

    private Integer contadorId;

    public ManutencaoRepository(){
        this.manutencaoRepositorio = new HashMap<>();
        this.contadorId = 1;
    }

    public Manutencao salvar(Manutencao manutencao){
        manutencao.setId(contadorId);
        manutencaoRepositorio.put(manutencao.getId(), manutencao);
        contadorId++;
        return manutencao;
    }

    public Manutencao buscarPorId(Integer id){
        return manutencaoRepositorio.get(id);
    }

    public List<Manutencao> listarTodos(){
        return new ArrayList<>(manutencaoRepositorio.values());
    }

    public List<Manutencao> listarAbertas(){
        List<Manutencao> manutencoesAbertas = new ArrayList<>();

        for (Manutencao manutencao : manutencaoRepositorio.values()){
            if(manutencao.getStatus() == StatusManutencao.ABERTA){
                manutencoesAbertas.add(manutencao);
            }
        }
        return manutencoesAbertas;
    }

    public List<Manutencao> listarPorTecnico(Integer tecnicoId){
        List<Manutencao> manutencoesTecnico = new ArrayList<>();

        for(Manutencao manutencao : manutencaoRepositorio.values()){
            if(manutencao.getTecnico() != null && manutencao.getTecnico().getId().equals(tecnicoId)){

                manutencoesTecnico.add(manutencao);
            }
        }
        return manutencoesTecnico;
    }

    public List<Manutencao> listarPorAtivo(Integer ativoId){
        List<Manutencao> manutencoesAtivo = new ArrayList<>();

        for (Manutencao manutencao : manutencaoRepositorio.values()){
            if(manutencao.getAtivoIndustrial() != null && manutencao.getAtivoIndustrial().getId().equals(ativoId)){

                manutencoesAtivo.add(manutencao);
            }
        }
        return manutencoesAtivo;
    }

    public Manutencao atualizar(Manutencao manutencao){
        Manutencao manutencaoAtualizada = null;

        if(manutencao.getId() != null && manutencaoRepositorio.containsKey(manutencao.getId())){
            manutencaoRepositorio.put(manutencao.getId(), manutencao);
            manutencaoAtualizada = manutencao;
        }
        return manutencaoAtualizada;
    }
}
