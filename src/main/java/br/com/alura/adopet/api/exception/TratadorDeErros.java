package br.com.alura.adopet.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros extends RuntimeException {

    @ExceptionHandler(AbrigoNaoEncontradoException.class)
    public ResponseEntity tartarErroAbrigoNaoEncontrado(AbrigoNaoEncontradoException ex){

        return ResponseEntity.notFound().build();
    }
}
