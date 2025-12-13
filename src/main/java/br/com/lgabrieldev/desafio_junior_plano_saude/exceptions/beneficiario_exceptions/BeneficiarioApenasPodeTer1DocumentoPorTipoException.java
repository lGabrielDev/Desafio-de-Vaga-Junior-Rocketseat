package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class BeneficiarioApenasPodeTer1DocumentoPorTipoException extends RuntimeException{
     
     //constructors
      public BeneficiarioApenasPodeTer1DocumentoPorTipoException(){
          super("Erro de validação: Tipos de documentos duplicados");
     }
}
