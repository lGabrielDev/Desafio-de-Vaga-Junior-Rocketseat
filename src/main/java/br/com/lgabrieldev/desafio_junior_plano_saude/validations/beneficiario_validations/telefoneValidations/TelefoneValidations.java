package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.telefoneValidations;

import org.springframework.stereotype.Component;

import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoDeveTerApenasNumerosException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoTemMuitosCharactersException;

@Component

public class TelefoneValidations implements TelefoneValidationsImp  {

     @Override
     public Boolean telefoneNaoPodeSerNull(String telefone) {
           if(telefone == null || telefone.isBlank()){
               throw new CampoNaoPodeSerNullException("telefone");
          }
          return true;
     }

     @Override
     public Boolean telefoneNumeroCharacters(String telefone) {
          if(telefone.length() != 11){
               throw new CampoTemMuitosCharactersException(" 'telefone' deve ter exatamente 11 characteres. \n Exemplo de um número válido --> '11911112222'  ");
          }
          return true;
     }

     @Override
     public Boolean telefoneApenasNumeros(String telefone) {
          if(  telefone.chars().allMatch(c ->  Character.isDigit(c))) {
               return true;
          }
          throw new CampoDeveTerApenasNumerosException("telefone");
     }
     
     @Override
     public Boolean telefoneTudoCerto(String telefone) {

          this.telefoneNaoPodeSerNull(telefone);
          this.telefoneNumeroCharacters(telefone);
          this.telefoneApenasNumeros(telefone);

          return true;
     }

    


     
}
