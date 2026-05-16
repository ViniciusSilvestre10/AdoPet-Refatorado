package br.com.alura.adopet.api.exception;

import br.com.alura.adopet.api.service.AbrigoService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class AbrigoNaoEncontradoException extends RuntimeException {

    public AbrigoNaoEncontradoException(String message) {
        super(message);
    }
}
