package com.br.cadastro_empresa;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CadastroEmpresaApplication {
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaServers;

	public static void main(String[] args) {
		SpringApplication.run(CadastroEmpresaApplication.class, args);
	}

	@PostConstruct
	public void checkKafkaBootstrap() {
		System.out.println("🔍 bootstrap-servers carregado: " + kafkaServers);
	}



}


