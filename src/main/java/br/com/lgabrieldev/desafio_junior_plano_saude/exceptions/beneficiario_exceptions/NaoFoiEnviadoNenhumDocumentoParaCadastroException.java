package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class NaoFoiEnviadoNenhumDocumentoParaCadastroException extends RuntimeException {
     
     //constructors
     public  NaoFoiEnviadoNenhumDocumentoParaCadastroException(){
          super("Para cadastrar um beneficiário é necessário informar pelo menos 1 documento --> 'CPF', 'RG' ou 'CNH'");
     }
}