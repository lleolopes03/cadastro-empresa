package com.br.cadastro_empresa.dtos;

import com.br.cadastro_empresa.domain.enums.Status;
import com.br.cadastro_empresa.domain.enums.TipoEmpresa;

import java.time.LocalDateTime;

public class EmpresaEventoDto {
    private Long idEmpresa;
    private String razaoSocial;
    private String cnpj;
    private TipoEmpresa tipoEmpresa;
    private Status status;
    private String eventoTipo;
    private LocalDateTime dataEvento = LocalDateTime.now();

    public EmpresaEventoDto() {
    }

    public EmpresaEventoDto(Long idEmpresa, String razaoSocial, String cnpj, TipoEmpresa tipoEmpresa, Status status, String eventoTipo, LocalDateTime dataEvento) {
        this.idEmpresa = idEmpresa;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.tipoEmpresa = tipoEmpresa;
        this.status = status;
        this.eventoTipo = eventoTipo;
        this.dataEvento = dataEvento;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public TipoEmpresa getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEventoTipo() {
        return eventoTipo;
    }

    public void setEventoTipo(String eventoTipo) {
        this.eventoTipo = eventoTipo;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }
}
