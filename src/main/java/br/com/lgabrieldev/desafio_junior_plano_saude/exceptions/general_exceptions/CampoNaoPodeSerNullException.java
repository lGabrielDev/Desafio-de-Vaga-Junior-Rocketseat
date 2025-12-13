package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions;

public class CampoNaoPodeSerNullException extends RuntimeException{
     
     //constructors
     public CampoNaoPodeSerNullException(String errorField){
          super(String.format("O campo '%s' não pode ser null.", errorField));
     }
}