package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions;

public class FormatacaoDeDataIncorretaException extends RuntimeException {

     //constructors
     public  FormatacaoDeDataIncorretaException(){
          super(" 'dataNascimento' deve estar no formato BR --> 11-02-1990' ");
     }

}
