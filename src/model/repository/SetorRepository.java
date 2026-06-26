package model.repository;

import java.util.HashMap;
import model.entity.Setor;

public class SetorRepository {

    private HashMap<Integer, Setor> setores = new HashMap<>();
    private int proximoId = 1;

    public void salvar(Setor setor) {
        setor.setId(proximoId);
        setores.put(proximoId++, setor);
    }

    public Setor buscarPorId(int id) {
        return setores.get(id);
    }

    public Setor buscarPorNome (String nome) {
        for(Setor setor : setores.values()) {
            if (setor.getNome().equalsIgnoreCase(nome)) {
                return setor;
            }
        }
        return null;
    }

    public HashMap<Integer, Setor> listarTodos(){
        return setores;
    }

    public void atualizar(Setor setor) {
        setores.put(setor.getId(), setor);
    }


}