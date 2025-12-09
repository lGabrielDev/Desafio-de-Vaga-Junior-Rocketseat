package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions;

public class TipoDocumentoNaoEncontradoException extends RuntimeException{
     
     //constructors
     public TipoDocumentoNaoEncontradoException(){
          super(" 'tipoDocumento' não encontrado!  Os tipos disponíveis são:\n'RG', 'CPF' e 'CNH' ");
     }
}
