package model.service;

import enums.StatusAtivo;
import exception.AppException;
import model.entity.AtivoIndustrial;
import model.repository.AtivoRepository;
import model.repository.SetorRepository;

import java.util.Collection;

public class AtivoService {

    private AtivoRepository ativoRepository;
    private SetorRepository setorRepository;

    public AtivoService(AtivoRepository ativoRepository, SetorRepository setorRepository){
        this.ativoRepository = ativoRepository;
        this.setorRepository = setorRepository;
    }

    public AtivoIndustrial cadastrarAtivo(AtivoIndustrial ativoIndustrial){
        if(ativoRepository.buscarPorCodigoPatrimonial(ativoIndustrial.getCodigoPatrimonial()) != null){
            throw new AppException("ERRO: Código patrimonial já em uso.");
        }
        if(!ativoIndustrial.getSetor().getAtivo()){
            throw new AppException("ERRO: Setor inativo não pode receber ativos.");
        }
        ativoIndustrial.setStatusAtivos(StatusAtivo.NORMAL);
        return ativoRepository.criarAtivo(ativoIndustrial);
    }

    public Collection<AtivoIndustrial> listarAtivos(){
        Collection<AtivoIndustrial> ativos = ativoRepository.listarAtivos();

        if(ativos.isEmpty()){
            throw new AppException("ERRO: Nenhum ativo cadastrado.");
        }
        return ativos;
    }

    public AtivoIndustrial buscarPorId(Integer id){
        AtivoIndustrial ativo = ativoRepository.buscarPorId(id);

        if(ativo == null){
            throw new AppException("ERRO: Ativo não encontrado.");
        }
        return ativo;
    }

    public Collection<AtivoIndustrial> listarPorSetor(Integer idSetor){
        Collection<AtivoIndustrial> ativos = ativoRepository.listarPorSetor(idSetor);

        if(ativos.isEmpty()){
            throw new AppException("ERRO: Nenhum ativo encontrado para esse setor.");
        }

        return ativos;
    }

    public Collection<AtivoIndustrial> listarPorStatus(StatusAtivo status){
        Collection<AtivoIndustrial> ativos = ativoRepository.listarPorStatus(status);
        if(ativos.isEmpty()){
            throw new AppException("ERRO: Nenhum ativo encontrado com esse status.");
        }
        return ativos;
    }

    public AtivoIndustrial editarAtivo(Integer id, AtivoIndustrial ativoIndustrial){
        AtivoIndustrial ativo = ativoRepository.buscarPorId(id);
        if(ativo == null){
            throw new AppException("ERRO: Ativo não encontrado.");
        }
        if(!ativo.getCodigoPatrimonial().equals(ativoIndustrial.getCodigoPatrimonial()) && ativoRepository.buscarPorCodigoPatrimonial(ativoIndustrial.getCodigoPatrimonial()) != null){
            throw new AppException("ERRO: Código patrimonial já em uso.");
        }
        if(!ativoIndustrial.getSetor().getAtivo()){
            throw new AppException("ERRO: Setor inativos não pode receber ativos.");
        }
        ativoIndustrial.setId(id);
        return ativoRepository.atualizarAtivo(id, ativoIndustrial);
    }

    public void inativarAtivo(Integer id){
        AtivoIndustrial ativo = ativoRepository.buscarPorId(id);
        if(ativo == null){
            throw new AppException("ERRO: Ativo não encontrado.");
        }
        ativo.setStatusAtivos(StatusAtivo.INATIVO);
    }

}
