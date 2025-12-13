package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.telefoneValidations;

public interface TelefoneValidationsImp {
     
     public Boolean telefoneNaoPodeSerNull(String telefone);
     public Boolean telefoneNumeroCharacters(String telefone);
     public Boolean telefoneApenasNumeros(String telefone);
     
     public Boolean telefoneTudoCerto(String nome);
}