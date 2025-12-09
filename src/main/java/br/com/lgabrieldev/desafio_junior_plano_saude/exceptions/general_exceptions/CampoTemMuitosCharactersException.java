package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions;

public class CampoTemMuitosCharactersException extends RuntimeException{
     
     //constructors
     public CampoTemMuitosCharactersException(String errorMessage){
          super(errorMessage);
     }
}

