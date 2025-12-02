package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions;

public class CampoNaoPodeSerNullException extends RuntimeException{
     
     public CampoNaoPodeSerNullException(String errorMessage){
          super(errorMessage);
     }
}
