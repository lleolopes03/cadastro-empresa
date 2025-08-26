package com.br.cadastro_empresa.kafka;

import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import com.br.cadastro_empresa.service.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

@Service
public class EmpresaEventoHandler {
    private final EmpresaService empresaService;

    private static final Logger log = LoggerFactory.getLogger(EmpresaEventoHandler.class);

    public EmpresaEventoHandler(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public void tratarEvento(EmpresaEventoDto evento) {
        switch (evento.getEventoTipo()) {
            case "CADASTRO_EMPRESA":
                empresaService.salvar(evento); // ← verificar se esse método existe!
                break;
            case "ATUALIZACAO_EMPRESA":
                empresaService.atualizar(evento.getIdEmpresa(), evento);
                break;
            case "EXCLUSAO_EMPRESA":
                empresaService.excluir(evento.getIdEmpresa());
                break;
            default:
                log.warn("Evento desconhecido: {}", evento.getEventoTipo());
        }
    }

}
