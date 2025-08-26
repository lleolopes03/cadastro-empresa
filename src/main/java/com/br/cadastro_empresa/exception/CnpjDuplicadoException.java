package com.br.cadastro_empresa.exception;

public class CnpjDuplicadoException extends RuntimeException{
    public CnpjDuplicadoException (String message){
        super(message);
    }
}
