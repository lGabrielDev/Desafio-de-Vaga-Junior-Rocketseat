package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions;

public class TipoDocumentoNaoEncontradoException extends RuntimeException{
     
     public TipoDocumentoNaoEncontradoException(String errorMessage){
          super(errorMessage);
     }
}
