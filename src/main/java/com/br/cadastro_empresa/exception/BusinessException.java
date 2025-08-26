package com.br.cadastro_empresa.exception;

public class BusinessException extends RuntimeException{
    public BusinessException (String message){
        super(message);
    }
}
