package com.br.cadastro_empresa.service;

import com.br.cadastro_empresa.domain.CadastroEmpresa;
import com.br.cadastro_empresa.domain.Endereco;
import com.br.cadastro_empresa.domain.enums.Status;
import com.br.cadastro_empresa.domain.enums.TipoEmpresa;
import com.br.cadastro_empresa.dtos.CreateEmpresaDto;
import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import com.br.cadastro_empresa.dtos.EmpresaResponseDto;
import com.br.cadastro_empresa.exception.BusinessException;
import com.br.cadastro_empresa.exception.CnpjDuplicadoException;
import com.br.cadastro_empresa.repository.EmpresaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpresaServiceTest {
    @InjectMocks
    private EmpresaService empresaService;
    @Mock
    private EmpresaRepository empresaRepository;

    private CreateEmpresaDto criarEmpresaValida(){
        CreateEmpresaDto dto= new CreateEmpresaDto();
        Endereco endereco = new Endereco();
        endereco.setCep("32606412");
        endereco.setLogradouro("Rua das Acácias");
        endereco.setComplemento("Apto 202");
        endereco.setBairro("Centro");
        endereco.setLocalidade("Betim");
        endereco.setUf("MG");

        dto.setRazaoSocial("leandroTransporte");
        dto.setCnpj("04687401000112");
        dto.setEndereco(endereco);
        dto.setEmail("leandro@email.com");
        dto.setTelefone("313511-2557");
        dto.setStatus(Status.ATIVO);
        dto.setTipoEmpresa(TipoEmpresa.CORPORATIVO);
        dto.setResponsavelLegal("Leandro");
        return dto;

    }
    private CadastroEmpresa criarEmpresa(){
        CadastroEmpresa cadastroEmpresa=new CadastroEmpresa();
        LocalDateTime agora = LocalDateTime.of(2025, 7, 26, 13, 44);
        Endereco endereco = new Endereco();
        endereco.setCep("32606412");
        endereco.setLogradouro("Rua das Acácias");
        endereco.setComplemento("Apto 202");
        endereco.setBairro("Centro");
        endereco.setLocalidade("Betim");
        endereco.setUf("MG");

        cadastroEmpresa.setId(1L);
        cadastroEmpresa.setRazaoSocial("leandroTransporte");
        cadastroEmpresa.setCnpj("04687401000112");
        cadastroEmpresa.setEndereco(endereco);
        cadastroEmpresa.setEmail("leandro@email.com");
        cadastroEmpresa.setTelefone("313511-2557");
        cadastroEmpresa.setDataCriacao(agora);
        cadastroEmpresa.setStatus(Status.ATIVO);
        cadastroEmpresa.setTipoEmpresa(TipoEmpresa.CORPORATIVO);
        cadastroEmpresa.setResponsavelLegal("Leandro");
        return cadastroEmpresa;

    }
    private EmpresaResponseDto respostaCadastroEmpresa(){
        EmpresaResponseDto responseDto=new EmpresaResponseDto();
        LocalDateTime agora = LocalDateTime.of(2025, 7, 26, 13, 44);
        Endereco endereco = new Endereco();
        endereco.setCep("32606412");
        endereco.setLogradouro("Rua das Acácias");
        endereco.setComplemento("Apto 202");
        endereco.setBairro("Centro");
        endereco.setLocalidade("Betim");
        endereco.setUf("MG");

        responseDto.setId(1L);
        responseDto.setRazaoSocial("leandroTransporte");
        responseDto.setCnpj("04687401000112");
        responseDto.setEndereco(endereco);
        responseDto.setEmail("leandro@email.com");
        responseDto.setTelefone("313511-2557");
        responseDto.setDataCriacao(agora);
        responseDto.setStatus(Status.ATIVO);
        responseDto.setTipoEmpresa(TipoEmpresa.CORPORATIVO);
        responseDto.setResponsavelLegal("Leandro");
        return responseDto;
    }
    @Test
    @DisplayName("Teste criar cadastro de emrpresa")
    public void teste_criar_cadastro_empresa(){
        CreateEmpresaDto dto =criarEmpresaValida();
        CadastroEmpresa cadastroEmpresa=criarEmpresa();
        EmpresaResponseDto responseDto=respostaCadastroEmpresa();
        Assertions.assertNotNull(dto);
        when(empresaRepository.save(any(CadastroEmpresa.class))).thenReturn(cadastroEmpresa);
        EmpresaResponseDto resultado=empresaService.salvar(dto);
        assertEquals(responseDto.getId(),resultado.getId());
        assertEquals(responseDto.getRazaoSocial(),resultado.getRazaoSocial());
        assertEquals(responseDto.getCnpj(),resultado.getCnpj());
        assertEquals(responseDto.getEndereco(),resultado.getEndereco());
        assertEquals(responseDto.getEmail(),resultado.getEmail());
        assertEquals(responseDto.getTelefone(),resultado.getTelefone());
        assertEquals(responseDto.getDataCriacao(),resultado.getDataCriacao());
        assertEquals(responseDto.getStatus(),resultado.getStatus());
        assertEquals(responseDto.getTipoEmpresa(),resultado.getTipoEmpresa());
        assertEquals(responseDto.getResponsavelLegal(),resultado.getResponsavelLegal());

        verify(empresaRepository).save(any(CadastroEmpresa.class));

    }
    @Test
    @DisplayName("Teste cnpj duplicado")
    public void teste_cnpj_duplicado(){
        CreateEmpresaDto dto=criarEmpresaValida();
        when(empresaRepository.existsByCnpj(dto.getCnpj())).thenReturn(true);
        assertThrows(CnpjDuplicadoException.class,()->{
            empresaService.salvar(dto);
        });
        verify(empresaRepository).existsByCnpj(dto.getCnpj());
        verify(empresaRepository, never()).save(any());
    }
    @Test
    @DisplayName("Teste busca Empresa por ID")
    public void teste_busca_por_id(){
        CadastroEmpresa cadastroEmpresa=criarEmpresa();
        EmpresaResponseDto responseDto=respostaCadastroEmpresa();
        when(empresaRepository.findById(1L)).thenReturn(Optional.of(cadastroEmpresa));
        EmpresaResponseDto resultado=empresaService.buscarPorId(1L);
        Assertions.assertNotNull(resultado);
        assertEquals(responseDto.getId(),resultado.getId());
        assertEquals(responseDto.getRazaoSocial(),resultado.getRazaoSocial());
        assertEquals(responseDto.getCnpj(),resultado.getCnpj());
        assertEquals(responseDto.getEndereco(),resultado.getEndereco());
        assertEquals(responseDto.getEmail(),resultado.getEmail());
        assertEquals(responseDto.getTelefone(),resultado.getTelefone());
        assertEquals(responseDto.getDataCriacao(),resultado.getDataCriacao());
        assertEquals(responseDto.getStatus(),resultado.getStatus());
        assertEquals(responseDto.getTipoEmpresa(),resultado.getTipoEmpresa());
        assertEquals(responseDto.getResponsavelLegal(),resultado.getResponsavelLegal());

    }
    @Test
    @DisplayName("Teste busca Por ID não encontrado")
    public void teste_busca_por_id_nao_encontrado(){
        when(empresaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(BusinessException.class,()->{
            empresaService.buscarPorId(99L);
        });
    }
    @Test
    @DisplayName("Teste lista de empresa")
    public void teste_lista_empresa(){
        Endereco endereco = new Endereco();
        endereco.setCep("32606412");
        endereco.setLogradouro("Rua das Acácias");
        endereco.setComplemento("Apto 202");
        endereco.setBairro("Centro");
        endereco.setLocalidade("Betim");
        endereco.setUf("MG");
        LocalDateTime agora = LocalDateTime.of(2025, 7, 26, 13, 44);
        List<CadastroEmpresa>empresas= Arrays.asList(
                new CadastroEmpresa(1L,"leandroTransporte","04687401000112",endereco,"leandro@email.com","313511-2557",agora,Status.ATIVO,TipoEmpresa.CORPORATIVO,"leandro"),
                new CadastroEmpresa(2L,"gustavoTransporte","04687401000115",endereco,"gustavo@email.com","313511-2557",agora,Status.ATIVO,TipoEmpresa.CORPORATIVO,"gustavo")
        );
        when(empresaRepository.findAll()).thenReturn(empresas);
        List<EmpresaResponseDto>resultados=empresaService.buscarTodos();
        Assertions.assertNotNull(resultados);
        assertEquals(empresas.size(),resultados.size());
        for (int i=0;i<empresas.size();i++){
            assertEquals(empresas.get(i).getId(),resultados.get(i).getId());
            assertEquals(empresas.get(i).getRazaoSocial(),resultados.get(i).getRazaoSocial());
            assertEquals(empresas.get(i).getCnpj(),resultados.get(i).getCnpj());
            assertEquals(empresas.get(i).getEndereco(),resultados.get(i).getEndereco());
            assertEquals(empresas.get(i).getEmail(),resultados.get(i).getEmail());
            assertEquals(empresas.get(i).getTelefone(),resultados.get(i).getTelefone());
            assertEquals(empresas.get(i).getDataCriacao(),resultados.get(i).getDataCriacao());
            assertEquals(empresas.get(i).getStatus(),resultados.get(i).getStatus());
            assertEquals(empresas.get(i).getTipoEmpresa(),resultados.get(i).getTipoEmpresa());
            assertEquals(empresas.get(i).getResponsavelLegal(),resultados.get(i).getResponsavelLegal());
        }


    }
    @Test
    @DisplayName("Teste lista vazia")
    public void teste_lista_vazia(){
        when(empresaRepository.findAll()).thenReturn(Collections.emptyList());
        List<EmpresaResponseDto>responseDtos=empresaService.buscarTodos();

        Assertions.assertNotNull(responseDtos);
        Assertions.assertTrue(responseDtos.isEmpty());
    }
    @Test@DisplayName("Teste buscar Por CNPJ")
    public void teste_buscar_por_cnpj(){
        CadastroEmpresa cadastroEmpresa=criarEmpresa();
        EmpresaResponseDto responseDto=respostaCadastroEmpresa();
        when(empresaRepository.findByCnpj("04687401000112")).thenReturn(Optional.of(cadastroEmpresa));
        EmpresaResponseDto resultado=empresaService.buscarPorCnpj("04687401000112");
        Assertions.assertNotNull(resultado);
        assertEquals(responseDto.getId(),resultado.getId());
        assertEquals(responseDto.getRazaoSocial(),resultado.getRazaoSocial());
        assertEquals(responseDto.getCnpj(),resultado.getCnpj());
        assertEquals(responseDto.getEndereco(),resultado.getEndereco());
        assertEquals(responseDto.getEmail(),resultado.getEmail());
        assertEquals(responseDto.getTelefone(),resultado.getTelefone());
        assertEquals(responseDto.getDataCriacao(),resultado.getDataCriacao());
        assertEquals(responseDto.getStatus(),resultado.getStatus());
        assertEquals(responseDto.getTipoEmpresa(),resultado.getTipoEmpresa());
        assertEquals(responseDto.getResponsavelLegal(),resultado.getResponsavelLegal());


    }
    @Test
    @DisplayName("Teste busca Por CNPJ não encontrado")
    public void teste_busca_por_cnpj_nao_encontrado(){
        when(empresaRepository.findByCnpj("04687401000112")).thenReturn(Optional.empty());
        assertThrows(BusinessException.class,()->{
            empresaService.buscarPorCnpj("04687401000112");
        });
    }
    @Test
    @DisplayName("Teste editar  empresa não encontrado")
    public void teste_editar_empresa_nao_encontrado(){
        Long id=1L;
        CreateEmpresaDto createEmpresaDto= new CreateEmpresaDto();
        when(empresaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(BusinessException.class,()->empresaService.editarEmpresa(id,createEmpresaDto));
    }
    @Test
    @DisplayName("Teste editar empresa")
    public void teste_editar_empresa(){
        Endereco endereco = new Endereco();
        endereco.setCep("32606412");
        endereco.setLogradouro("Rua das Acácias");
        endereco.setComplemento("Apto 202");
        endereco.setBairro("Centro");
        endereco.setLocalidade("Betim");
        endereco.setUf("MG");
        LocalDateTime agora = LocalDateTime.of(2025, 7, 26, 13, 44);
        CadastroEmpresa empresaExistente= new CadastroEmpresa(1L,"leandroTransporte","04687401000112",endereco,"leandro@email.com","313511-2557",agora,Status.ATIVO,TipoEmpresa.CORPORATIVO,"leandro");
        CreateEmpresaDto createEmpresaDto=new CreateEmpresaDto("leandroTransportes","04687401000112","leandro@email.com",endereco,"313511-2557",Status.ATIVO,TipoEmpresa.CORPORATIVO,"leandrolopes");
        CadastroEmpresa empresaAtualizada=new CadastroEmpresa(1L,"leandroTransportes","04687401000112",endereco,"leandro@email.com","313511-2557",agora,Status.ATIVO,TipoEmpresa.CORPORATIVO,"leandrolopes");
        EmpresaResponseDto responseDto=new EmpresaResponseDto(1L,"leandroTransportes","04687401000112",endereco,"leandro@email.com","313511-2557",agora,Status.ATIVO,TipoEmpresa.CORPORATIVO,"leandrolopes");

        when(empresaRepository.findById(1L)).thenReturn(Optional.of(empresaExistente));
        when(empresaRepository.save(any(CadastroEmpresa.class))).thenReturn(empresaAtualizada);

        EmpresaResponseDto resultado=empresaService.editarEmpresa(1L,createEmpresaDto);
        Assertions.assertNotNull(resultado);

        assertEquals(responseDto.getId(),resultado.getId());
        assertEquals(responseDto.getRazaoSocial(),resultado.getRazaoSocial());
        assertEquals(responseDto.getCnpj(),resultado.getCnpj());
        assertEquals(responseDto.getEndereco(),resultado.getEndereco());
        assertEquals(responseDto.getEmail(),resultado.getEmail());
        assertEquals(responseDto.getTelefone(),resultado.getTelefone());
        assertEquals(responseDto.getDataCriacao(),resultado.getDataCriacao());
        assertEquals(responseDto.getStatus(),resultado.getStatus());
        assertEquals(responseDto.getTipoEmpresa(),resultado.getTipoEmpresa());
        assertEquals(responseDto.getResponsavelLegal(),resultado.getResponsavelLegal());


    }
    @Test
    void deveSalvarEmpresaComDadosDoEvento() {
        EmpresaEventoDto evento = new EmpresaEventoDto();
        evento.setRazaoSocial("Nova Empresa");
        evento.setCnpj("00.111.222/0001-33");
        evento.setTipoEmpresa(TipoEmpresa.CORPORATIVO);
        evento.setStatus(Status.ATIVO);

        empresaService.salvar(evento);

        ArgumentCaptor<CadastroEmpresa> captor = ArgumentCaptor.forClass(CadastroEmpresa.class);
        verify(empresaRepository).save(captor.capture());

        CadastroEmpresa entidadeSalva = captor.getValue();
        assertEquals("Nova Empresa", entidadeSalva.getRazaoSocial());
        assertEquals("00.111.222/0001-33", entidadeSalva.getCnpj());
        assertEquals(TipoEmpresa.CORPORATIVO, entidadeSalva.getTipoEmpresa());
        assertEquals(Status.ATIVO, entidadeSalva.getStatus());
    }
    @Test
    void deveAtualizarEmpresaComDadosDoEvento() {
        Long idEmpresa = 1L;

        EmpresaEventoDto evento = new EmpresaEventoDto();
        evento.setRazaoSocial("Empresa Atualizada");
        evento.setCnpj("99.888.777/0001-55");
        evento.setTipoEmpresa(TipoEmpresa.CORPORATIVO);
        evento.setStatus(Status.INATIVO);

        CadastroEmpresa empresaExistente = new CadastroEmpresa();
        empresaExistente.setId(idEmpresa);
        empresaExistente.setRazaoSocial("Empresa Original");

        when(empresaRepository.findById(idEmpresa)).thenReturn(Optional.of(empresaExistente));

        empresaService.atualizar(idEmpresa, evento);

        assertEquals("Empresa Atualizada", empresaExistente.getRazaoSocial());
        assertEquals("99.888.777/0001-55", empresaExistente.getCnpj());
        assertEquals(TipoEmpresa.CORPORATIVO, empresaExistente.getTipoEmpresa());
        assertEquals(Status.INATIVO, empresaExistente.getStatus());

        verify(empresaRepository).save(empresaExistente);
    }
    @Test
    void deveExcluirEmpresaQuandoExiste() {
        Long idEmpresa = 2L;

        when(empresaRepository.existsById(idEmpresa)).thenReturn(true);

        empresaService.excluir(idEmpresa);

        verify(empresaRepository).deleteById(idEmpresa);
    }

    @Test
    void deveLancarExcecaoQuandoEmpresaNaoExiste() {
        Long idEmpresa = 3L;

        when(empresaRepository.existsById(idEmpresa)).thenReturn(false);

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            empresaService.excluir(idEmpresa);
        });

        assertEquals("Empresa não encontrada", excecao.getMessage());
    }








}
