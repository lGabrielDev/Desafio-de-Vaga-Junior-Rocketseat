package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class BeneficiarioDeveSerMaiorDeIdadeException extends RuntimeException{

     //constructors
     public BeneficiarioDeveSerMaiorDeIdadeException(){
          super("A idade miníma para cadastro é de 18 anos.");
     }
}
