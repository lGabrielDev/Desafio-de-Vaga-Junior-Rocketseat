package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class NaoFoiEnviadoNenhumDocumentoParaCadastroException extends RuntimeException {
     
     public  NaoFoiEnviadoNenhumDocumentoParaCadastroException(String errorMessage){
          super(errorMessage);
     }
}
 
