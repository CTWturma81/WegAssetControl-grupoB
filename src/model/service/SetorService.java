package model.service;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial ;
import model.entity.Setor;
import model.repository.AtivoRepository;
import model.repository.SetorRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SetorService {

    private SetorRepository setorRepository;
    private AtivoRepository ativoRepository;

    public SetorService(SetorRepository setorRepository, AtivoRepository ativoRepository) {
        this.setorRepository = setorRepository;
        this.ativoRepository = ativoRepository;
    }

    public Setor cadastrarSetor(Setor setor){

        if(setorRepository.buscarPorNome(setor.getNome()) != null){
            throw new AppException("ERRO: Nome do setor já está em uso.");
        }
        if(setor.getNome().trim().isEmpty()){
            throw new AppException("ERRO: Nome do setor deve ser preenchido.");
        }

        setorRepository.salvar(setor);

        return setor;
    }

    public List<Setor> listarSetores(){
        List<Setor> setores = new ArrayList<>(setorRepository.listarTodos().values());

        if(setores.isEmpty()){
            throw new AppException("ERRO: Nenhum setor cadastrado.");
        }

        return setores;
    }

    public void editarSetor(Setor novoSetor){
        Setor setor = setorRepository.buscarPorId(novoSetor.getId());

        if(setor == null){
            throw new AppException("ERRO: Setor não encontrado.");
        }

        Setor setorComMesmoNome = setorRepository.buscarPorNome(novoSetor.getNome());

        if (setorComMesmoNome != null && !setorComMesmoNome.getId().equals(novoSetor.getId())) {
            throw new AppException("ERRO: Já existe outro setor com o mesmo nome.");
        }

        setorRepository.atualizar(novoSetor);
    }

    public void inativarSetor(Integer id){
        Setor setor = setorRepository.buscarPorId(id);

        if(setor == null){
            throw new AppException("ERRO: Setor não encontrado");
        }

        if(!setor.getAtivo()) {
            throw new AppException("ERRO: Setor já está inativo:");
        }

        boolean possuiAtivoVinculado = ativoRepository.listarAtivos().stream()
                .anyMatch(ativo -> ativo.getSetor() != null && ativo.getSetor().getId().equals(id));

        if(possuiAtivoVinculado) {
            throw new AppException("ERRO: Não é possível inativar o setor. Existem ativos industriais vinculados a ele.");
        }

        setor.setAtivo(false);
        setorRepository.atualizar(setor);

    }

    public Setor buscarPorId(Integer id){

        Setor setor = setorRepository.buscarPorId(id);

        if(setor == null){
            throw new AppException("ERRO: Setor não encontrado.");
        }

        return setor;
    }
}



