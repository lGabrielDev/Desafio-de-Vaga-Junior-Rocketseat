package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions;

public class DocumentosIguaisException extends RuntimeException{
     
     //constructors
     public DocumentosIguaisException(){
          super("Os documentos informados possuem o mesmo número. Cada documento deve ser único.");
     }
}