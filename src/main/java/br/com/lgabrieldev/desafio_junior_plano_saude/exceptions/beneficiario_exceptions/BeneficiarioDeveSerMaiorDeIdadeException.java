package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class BeneficiarioDeveSerMaiorDeIdadeException extends RuntimeException{
     
     public BeneficiarioDeveSerMaiorDeIdadeException(String errorMessage){
          super(errorMessage);
     }
}
