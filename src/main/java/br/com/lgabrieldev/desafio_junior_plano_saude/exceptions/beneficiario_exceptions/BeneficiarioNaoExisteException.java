package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class BeneficiarioNaoExisteException extends RuntimeException{
     
     //constructors
     public BeneficiarioNaoExisteException(Long beneficiarioId){
          super(String.format("Beneficiario #%d nao existe!", beneficiarioId));
     }
}
