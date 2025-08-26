package com.br.cadastro_empresa.infra;

import com.br.cadastro_empresa.exception.BusinessException;
import com.br.cadastro_empresa.exception.CepNaoEncontradoException;
import com.br.cadastro_empresa.exception.CnpjDuplicadoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CepNaoEncontradoException.class)
    public ResponseEntity<ApiError> handlerBusinessException(CepNaoEncontradoException ex) {
        ApiError error = new ApiError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(CnpjDuplicadoException.class)
    public ResponseEntity<ApiError> handleUniqueConstraintViolation(CnpjDuplicadoException ex) {
        ApiError error = new ApiError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError>handlerBusinessException(BusinessException ex){
        ApiError error=new ApiError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
