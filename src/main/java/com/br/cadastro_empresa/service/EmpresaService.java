package com.br.cadastro_empresa.service;

import com.br.cadastro_empresa.domain.CadastroEmpresa;
import com.br.cadastro_empresa.dtos.CreateEmpresaDto;
import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import com.br.cadastro_empresa.dtos.EmpresaResponseDto;
import com.br.cadastro_empresa.dtos.mapper.EmpresaMapper;
import com.br.cadastro_empresa.exception.BusinessException;
import com.br.cadastro_empresa.exception.CnpjDuplicadoException;
import com.br.cadastro_empresa.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaResponseDto salvar(CreateEmpresaDto createEmpresaDto){
        if (empresaRepository.existsByCnpj(createEmpresaDto.getCnpj())) {
            throw new CnpjDuplicadoException("CNPJ já cadastrado!");
        }
        CadastroEmpresa cadastroEmpresa= EmpresaMapper.toCadastroEmpresa(createEmpresaDto);
        CadastroEmpresa cadastroSalvo=empresaRepository.save(cadastroEmpresa);
        EmpresaResponseDto responseDto=EmpresaMapper.toDto(cadastroSalvo);
        return responseDto;

    }
    public EmpresaResponseDto buscarPorId(Long id){
        CadastroEmpresa cadastroEmpresa=empresaRepository.findById(id).orElseThrow(()->new BusinessException(String.format("Empresa com id: %s não encontrado",id)));
        return EmpresaMapper.toDto(cadastroEmpresa);
    }
    public List<EmpresaResponseDto>buscarTodos(){
        List<CadastroEmpresa>cadastroEmpresas=empresaRepository.findAll();
        return EmpresaMapper.toListDto(cadastroEmpresas);
    }
    @Transactional
    public void deletarPorId(Long id){
        CadastroEmpresa cadastroEmpresa=empresaRepository.findById(id).orElseThrow(()->new BusinessException(String.format("Empresa com id: %s não encontrado",id)));
        empresaRepository.deleteById(id);

    }
    @Transactional
    public EmpresaResponseDto editarEmpresa(Long id, CreateEmpresaDto empresaDto){
        CadastroEmpresa cadastroEmpresa=empresaRepository.findById(id).orElseThrow(()->new BusinessException(String.format("Empresa com id: %s não encontrado",id)));
        cadastroEmpresa.setEndereco(empresaDto.getEndereco());
        cadastroEmpresa.setEmail(empresaDto.getEmail());
        cadastroEmpresa.setTelefone(empresaDto.getTelefone());
        cadastroEmpresa.setStatus(empresaDto.getStatus());
        cadastroEmpresa.setTipoEmpresa(empresaDto.getTipoEmpresa());
        cadastroEmpresa.setResponsavelLegal(empresaDto.getResponsavelLegal());
        CadastroEmpresa atualizado=empresaRepository.save(cadastroEmpresa);
        return EmpresaMapper.toDto(atualizado);
    }
    public EmpresaResponseDto buscarPorCnpj(String cnpj){
        CadastroEmpresa cadastroEmpresa=empresaRepository.findByCnpj(cnpj).orElseThrow(()->new BusinessException(String.format("Cnpj: %s não encontrado",cnpj)));
        return EmpresaMapper.toDto(cadastroEmpresa);
    }
    public void salvar(EmpresaEventoDto evento) {
        CadastroEmpresa novaEmpresa = new CadastroEmpresa();

        novaEmpresa.setRazaoSocial(evento.getRazaoSocial());
        novaEmpresa.setCnpj(evento.getCnpj());
        novaEmpresa.setTipoEmpresa(evento.getTipoEmpresa());
        novaEmpresa.setStatus(evento.getStatus());

        empresaRepository.save(novaEmpresa);
    }
    public void atualizar(Long id, EmpresaEventoDto evento) {
        CadastroEmpresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CadastroEmpresa não encontrada"));

        empresa.setRazaoSocial(evento.getRazaoSocial());
        empresa.setCnpj(evento.getCnpj());
        empresa.setTipoEmpresa(evento.getTipoEmpresa());
        empresa.setStatus(evento.getStatus());

        empresaRepository.save(empresa);
    }
    public void excluir(Long idEmpresa) {
        if (!empresaRepository.existsById(idEmpresa)) {
            throw new RuntimeException("Empresa não encontrada");
        }

        empresaRepository.deleteById(idEmpresa);
    }



}
