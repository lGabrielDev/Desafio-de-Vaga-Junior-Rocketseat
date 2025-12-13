package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.name_validations;

public interface NomeValidationsImp {

     public Boolean nomeNaoPodeSerNull(String nome);
     public Boolean nomeMaxCharactersOk(String nome);

     public Boolean nomeTudoCerto(String nome);
}