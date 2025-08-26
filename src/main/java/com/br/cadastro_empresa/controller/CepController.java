package com.br.cadastro_empresa.controller;

import com.br.cadastro_empresa.domain.Endereco;
import com.br.cadastro_empresa.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/cep")
    public Endereco buscarEndereco(@RequestParam String cep) {
        return cepService.buscarEnderecoPorCep(cep);
    }
}
