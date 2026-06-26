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

    public Tecnico cadastrarTecnico(Tecnico tecnico){
        if(tecnicoRepository.buscarPorMatricula(tecnico.getMatricula()) != null){
            throw new AppException("ERRO: Matrícula já está em uso.");
        }
        return tecnicoRepository.salvar(tecnico);
    }

    public List<Tecnico> listarTecnicos(){
        List<Tecnico> tecnicos = tecnicoRepository.listarTodos();
        if(tecnicos.isEmpty()){
            throw new AppException("ERRO: Nenhum técnico cadastrado.");
        }
        return tecnicos;
    }

    public Tecnico editarTecnico(Integer id, Tecnico tecnicoAtualizado){
        Tecnico tecnico = tecnicoRepository.buscarPorId(id);
        if(tecnico == null){
            throw new AppException("ERRO: Técnico não encotrado.");
        }
        if(!tecnico.getMatricula().equals(tecnicoAtualizado.getMatricula()) && tecnicoRepository.buscarPorMatricula(tecnicoAtualizado.getMatricula()) != null){
            throw new AppException("ERRO: Matricula já está em uso.");
        }
        tecnicoAtualizado.setId(id);
        return tecnicoRepository.atualizar(tecnicoAtualizado);
    }

    public void inativarTecnico(Integer id){
        Tecnico tecnico = tecnicoRepository.buscarPorId(id);
        if(tecnico == null){
            throw new AppException("ERRO: Técnico não encontrado.");
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
