package com.br.cadastro_empresa.kafka;

import com.br.cadastro_empresa.domain.enums.Status;
import com.br.cadastro_empresa.domain.enums.TipoEmpresa;
import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmpresaEventoProducerTest {
    @Mock
    private KafkaTemplate<String, EmpresaEventoDto> kafkaTemplate;

    private EmpresaEventoProducer empresaEventoProducer;

    @Test
    void deveEnviarEventoEmpresa() {
        empresaEventoProducer = new EmpresaEventoProducer(kafkaTemplate, "empresa.cadastro.evento");

        EmpresaEventoDto evento = new EmpresaEventoDto();
        evento.setIdEmpresa(1L);
        evento.setRazaoSocial("Empresa XPTO");
        evento.setCnpj("12.345.678/0001-99");
        evento.setTipoEmpresa(TipoEmpresa.CORPORATIVO);
        evento.setStatus(Status.ATIVO);
        evento.setEventoTipo("CADASTRO_EMPRESA");
        evento.setDataEvento(LocalDateTime.now());

        empresaEventoProducer.enviarEvento(evento);

        verify(kafkaTemplate, times(1)).send("empresa.cadastro.evento", evento);
    }



}
