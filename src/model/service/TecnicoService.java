package model.service;

import exception.AppException;
import model.entity.Tecnico;
import model.repository.TecnicoRepository;

import java.util.ArrayList;
import java.util.List;

public class TecnicoService {

    private TecnicoRepository tecnicoRepository;

    public TecnicoService(TecnicoRepository tecnicoRepository){
        this.tecnicoRepository = tecnicoRepository;
    }

    public void cadastrarTecnico(Tecnico tecnico){
        if(tecnicoRepository.buscarPorMatricula(tecnico.getMatricula()) != null){
            throw new AppException("ERRO: Matrícula já está em uso.");
        }
        tecnicoRepository.salvar(tecnico);
    }

    public List<Tecnico> listarTecnicos(){
        List<Tecnico> tecnicos = tecnicoRepository.listarTodos();
        if(tecnicos.isEmpty()){
            throw new AppException("ERRO: Nenhum técnico cadastrado.");
        }
        return tecnicos;
    }

    public void editarTecnico(Tecnico tecnicoAtualizado){
        Tecnico tecnico = tecnicoRepository.buscarPorId(tecnicoAtualizado.getId());
        if(tecnico == null){
            throw new AppException("ERRO: Técnico não encontrado.");
        }
        if(!tecnico.getMatricula().equals(tecnicoAtualizado.getMatricula()) && tecnicoRepository.buscarPorMatricula(tecnicoAtualizado.getMatricula()) != null){
            throw new AppException("ERRO: Matricula já está em uso.");
        }
        tecnicoRepository.atualizar(tecnicoAtualizado);
    }

    public void inativarTecnico(Integer id){
        Tecnico tecnico = tecnicoRepository.buscarPorId(id);
        if(tecnico == null){
            throw new AppException("ERRO: Técnico não encontrado.");
        }
        if(!tecnico.isAtivo()) {
            throw new AppException("ERRO: Técnico já está inativo.");
        }
        tecnico.setAtivo(false);
    }

    public Tecnico buscarPorId(Integer id){
        Tecnico tecnico = tecnicoRepository.buscarPorId(id);
        if(tecnico == null){
            throw new AppException("ERRO: Técnico não encontrado.");
        }
        return tecnico;
    }
}
