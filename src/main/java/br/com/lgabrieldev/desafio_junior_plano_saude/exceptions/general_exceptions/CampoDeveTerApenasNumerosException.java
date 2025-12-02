package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions;

public class CampoDeveTerApenasNumerosException extends RuntimeException{
     
     public CampoDeveTerApenasNumerosException(String errorMessage){
          super(errorMessage);
     }
}
