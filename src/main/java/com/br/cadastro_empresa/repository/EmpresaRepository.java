package com.br.cadastro_empresa.repository;

import com.br.cadastro_empresa.domain.CadastroEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<CadastroEmpresa,Long> {
    boolean existsByCnpj(String cnpj);
    Optional<CadastroEmpresa> findByCnpj(String cnpj);
}
