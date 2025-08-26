package com.br.cadastro_empresa.kafka;

import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerHealthIndicator implements HealthIndicator {
    private final KafkaTemplate<String, EmpresaEventoDto> kafkaTemplate;

    public KafkaProducerHealthIndicator(KafkaTemplate<String, EmpresaEventoDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Health health() {
        try {
            kafkaTemplate.send("health-check-topic", new EmpresaEventoDto()).get(); // Simulação
            return Health.up().withDetail("Kafka Producer", "Operacional").build();
        } catch (Exception e) {
            return Health.down(e).withDetail("Kafka Producer", "Falha no envio").build();
        }
    }

}
