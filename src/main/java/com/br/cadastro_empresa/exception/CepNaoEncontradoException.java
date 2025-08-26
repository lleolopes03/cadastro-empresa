package com.br.cadastro_empresa.exception;

public class CepNaoEncontradoException extends RuntimeException{
    public CepNaoEncontradoException(String message){
        super(message);
    }
}
