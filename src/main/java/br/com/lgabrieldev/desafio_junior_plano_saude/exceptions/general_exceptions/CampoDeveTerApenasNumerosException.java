package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions;

public class CampoDeveTerApenasNumerosException extends RuntimeException{
     
     //constructors
     public CampoDeveTerApenasNumerosException(String errorField){
          super(String.format(" '%s' deve possuir apenas números.", errorField));
     }
}

