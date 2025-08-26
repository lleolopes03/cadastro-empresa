package com.br.cadastro_empresa.kafka;


import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmpresaEventoProducer {
    private static final Logger log = LoggerFactory.getLogger(EmpresaEventoProducer.class);

    private final KafkaTemplate<String, EmpresaEventoDto> kafkaTemplate;
    private final String topicoEmpresaEvento;

    public EmpresaEventoProducer(KafkaTemplate<String, EmpresaEventoDto> kafkaTemplate,
                                 @Value("${topic.empresa-evento}") String topicoEmpresaEvento) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicoEmpresaEvento = topicoEmpresaEvento;
    }

    public void enviarEvento(EmpresaEventoDto eventoDto) {
        kafkaTemplate.send(topicoEmpresaEvento, eventoDto);
        log.info("🔊 Evento enviado com sucesso: {}", eventoDto);
    }




}
