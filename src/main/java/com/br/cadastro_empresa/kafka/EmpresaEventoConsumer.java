package com.br.cadastro_empresa.kafka;

import com.br.cadastro_empresa.dtos.EmpresaEventoDto;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmpresaEventoConsumer {

    private final EmpresaEventoHandler eventoHandler;

    public EmpresaEventoConsumer(EmpresaEventoHandler eventoHandler) {
        this.eventoHandler = eventoHandler;
    }

    @KafkaListener(topics = "empresa-eventos", groupId = "grupo-empresa")
    public void consumir(EmpresaEventoDto evento) {
        eventoHandler.tratarEvento(evento);
    }
}
