package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class FormatacaoDeDataIncorretaException extends RuntimeException {
     
     public  FormatacaoDeDataIncorretaException(String errorMessage){
          super(errorMessage);
     }
 
}
