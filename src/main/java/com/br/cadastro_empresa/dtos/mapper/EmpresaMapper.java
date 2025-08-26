package com.br.cadastro_empresa.dtos.mapper;

import com.br.cadastro_empresa.domain.CadastroEmpresa;
import com.br.cadastro_empresa.dtos.CreateEmpresaDto;
import com.br.cadastro_empresa.dtos.EmpresaResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class EmpresaMapper {
    private static final ModelMapper mapper= new ModelMapper();

    static {
        PropertyMap<CadastroEmpresa, EmpresaResponseDto>props=new PropertyMap<CadastroEmpresa, EmpresaResponseDto>() {
            @Override
            protected void configure() {

            }
        };

    }
    public static CadastroEmpresa toCadastroEmpresa(CreateEmpresaDto createEmpresaDto){
        return mapper.map(createEmpresaDto, CadastroEmpresa.class);
    }
    public static EmpresaResponseDto toDto(CadastroEmpresa cadastroEmpresa){
        return mapper.map(cadastroEmpresa, EmpresaResponseDto.class);
    }
    public static List<EmpresaResponseDto>toListDto(List<CadastroEmpresa>cadastroEmpresas){
        return cadastroEmpresas.stream().map(EmpresaMapper::toDto).collect(Collectors.toList());
    }
}
