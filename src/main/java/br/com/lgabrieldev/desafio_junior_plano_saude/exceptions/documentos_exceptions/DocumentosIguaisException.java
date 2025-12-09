package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions;

public class DocumentosIguaisException extends RuntimeException{
     
     public DocumentosIguaisException(String errorMessage){
          super(errorMessage);
     }
}