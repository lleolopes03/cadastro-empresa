package com.br.cadastro_empresa.dtos;

import com.br.cadastro_empresa.domain.Endereco;
import com.br.cadastro_empresa.domain.enums.Status;
import com.br.cadastro_empresa.domain.enums.TipoEmpresa;

import java.time.LocalDateTime;

public class EmpresaResponseDto {
    private Long id;
    private String razaoSocial;
    private String cnpj;
    private Endereco endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataCriacao;
    private Status status;
    private TipoEmpresa tipoEmpresa;
    private String responsavelLegal;

    public EmpresaResponseDto() {
    }

    public EmpresaResponseDto(Long id, String razaoSocial, String cnpj, Endereco endereco, String email, String telefone, LocalDateTime dataCriacao, Status status, TipoEmpresa tipoEmpresa, String responsavelLegal) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.tipoEmpresa = tipoEmpresa;
        this.responsavelLegal = responsavelLegal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getResponsavelLegal() {
        return responsavelLegal;
    }

    public void setResponsavelLegal(String responsavelLegal) {
        this.responsavelLegal = responsavelLegal;
    }
}
