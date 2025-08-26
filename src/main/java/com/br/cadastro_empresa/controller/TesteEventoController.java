package com.br.cadastro_empresa.controller;

import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import com.br.cadastro_empresa.kafka.EmpresaEventoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/teste-evento")
public class TesteEventoController {
    @Autowired
    private EmpresaEventoProducer producer;

    public TesteEventoController(EmpresaEventoProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Void> dispararEvento(@RequestBody EmpresaEventoDto evento) {
        evento.setDataEvento(LocalDateTime.now());
        producer.enviarEvento(evento);
        return ResponseEntity.ok().build();
    }



}
