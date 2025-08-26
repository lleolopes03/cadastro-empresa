package com.br.cadastro_empresa.kafka;

import com.br.cadastro_empresa.dtos.EmpresaEventoDto;
import com.br.cadastro_empresa.service.EmpresaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class EmpresaEventoHandlerTest {
    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    private EmpresaEventoHandler empresaEventoHandler;

    @Test
    void deveChamarSalvarQuandoEventoCadastro() {
        EmpresaEventoDto evento = new EmpresaEventoDto();
        evento.setEventoTipo("CADASTRO_EMPRESA");

        empresaEventoHandler.tratarEvento(evento);

        verify(empresaService).salvar(evento);
    }

    @Test
    void deveChamarAtualizarQuandoEventoAtualizacao() {
        EmpresaEventoDto evento = new EmpresaEventoDto();
        evento.setEventoTipo("ATUALIZACAO_EMPRESA");
        evento.setIdEmpresa(1L);

        empresaEventoHandler.tratarEvento(evento);

        verify(empresaService).atualizar(1L, evento);
    }

    @Test
    void deveChamarExcluirQuandoEventoExclusao() {
        EmpresaEventoDto evento = new EmpresaEventoDto();
        evento.setEventoTipo("EXCLUSAO_EMPRESA");
        evento.setIdEmpresa(2L);

        empresaEventoHandler.tratarEvento(evento);

        verify(empresaService).excluir(2L);
    }

    @Test
    void deveLogarEventoDesconhecido() {
        EmpresaEventoDto evento = new EmpresaEventoDto();
        evento.setEventoTipo("EVENTO_INVALIDO");

        empresaEventoHandler.tratarEvento(evento);

        // Aqui não tem verificação direta de log sem framework externo,
        // mas você pode verificar que nenhum método do service foi chamado:
        verifyNoInteractions(empresaService);
    }

}
