package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
     

     //tratar data
     @ExceptionHandler(HttpMessageNotReadableException.class)
     public ResponseEntity<ErroPadrao> HttpMessageNotReadableExceptionHandler (HttpMessageNotReadableException e){
          ErroPadrao e1 = new ErroPadrao();
          e1.setErrorMessage(" 'dataNascimento' deve estar no formato BR --> 11-02-1990' ");



          return ResponseEntity
               .status(HttpStatus.CONFLICT)
               .body(e1);

     }
}
