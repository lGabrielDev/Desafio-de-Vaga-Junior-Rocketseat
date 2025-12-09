package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions;

public class DocumentoJaExisteNoBancoException extends RuntimeException{
     
     //constructors
     public DocumentoJaExisteNoBancoException(String tipoDocumento){
          super(String.format(" o numero do documento '%s' já existe no banco", tipoDocumento));
     }
}
