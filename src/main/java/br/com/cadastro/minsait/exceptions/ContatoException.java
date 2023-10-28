package br.com.cadastro.minsait.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContatoException extends RuntimeException {
    public  ContatoException(String message) {
        super(message);
    }
}
