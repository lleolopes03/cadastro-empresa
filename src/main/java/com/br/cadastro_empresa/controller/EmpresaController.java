package com.br.cadastro_empresa.controller;

import com.br.cadastro_empresa.dtos.CreateEmpresaDto;
import com.br.cadastro_empresa.dtos.EmpresaResponseDto;
import com.br.cadastro_empresa.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaResponseDto>criarEmpresa(@RequestBody @Valid CreateEmpresaDto empresaDto){
        EmpresaResponseDto responseDto=empresaService.salvar(empresaDto);
        URI location=URI.create("/api/v1/empresa/"+responseDto.getId());
        return ResponseEntity.created(location).body(responseDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDto>buscarEmpresaPorId(@PathVariable Long id){
        EmpresaResponseDto empresaResponseDto=empresaService.buscarPorId(id);
        return ResponseEntity.ok(empresaResponseDto);
    }
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<EmpresaResponseDto>buscarEmpresaPorCnpj(@PathVariable String cnpj){
        EmpresaResponseDto responseDto=empresaService.buscarPorCnpj(cnpj);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping
    public ResponseEntity<List<EmpresaResponseDto>>buscarTodos(){
        List<EmpresaResponseDto>empresaResponseDtos=empresaService.buscarTodos();
        return ResponseEntity.ok(empresaResponseDtos);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletarPorId(@PathVariable Long id){
        empresaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDto>editarEmpresa(@PathVariable Long id, @RequestBody @Valid CreateEmpresaDto createEmpresaDto){
        EmpresaResponseDto empresaResponseDto=empresaService.editarEmpresa(id,createEmpresaDto);
        return ResponseEntity.ok(empresaResponseDto);
    }

}
