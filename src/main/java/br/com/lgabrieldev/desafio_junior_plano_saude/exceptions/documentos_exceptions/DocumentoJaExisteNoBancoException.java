package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions;

public class DocumentoJaExisteNoBancoException extends RuntimeException{
     
     public DocumentoJaExisteNoBancoException(String errorMessage){
          super(errorMessage);
     }
}
