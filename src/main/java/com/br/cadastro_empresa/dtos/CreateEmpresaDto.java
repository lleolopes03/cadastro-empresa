package com.br.cadastro_empresa.dtos;

import com.br.cadastro_empresa.domain.Endereco;
import com.br.cadastro_empresa.domain.enums.Status;
import com.br.cadastro_empresa.domain.enums.TipoEmpresa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;

public class CreateEmpresaDto {
    @NotBlank
    private String razaoSocial;
    @CNPJ
    private String cnpj;
    @NotNull
    private Endereco endereco;
    @NotBlank
    @Email(message = "formato do e-mail está inválido",regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;
    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone com formato inválido")
    private String telefone;
    @NotNull
    private Status status;
    @NotNull
    private TipoEmpresa tipoEmpresa;
    @NotBlank
    private String responsavelLegal;

    public CreateEmpresaDto() {
    }

    public CreateEmpresaDto(String razaoSocial, String cnpj, String email, Endereco endereco, String telefone, Status status, TipoEmpresa tipoEmpresa, String responsavelLegal) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.status = status;
        this.tipoEmpresa = tipoEmpresa;
        this.responsavelLegal = responsavelLegal;
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
