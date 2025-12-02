package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.name_validations;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoTemMuitosCharactersException;

@Component
public class NomeValidations implements NomeValidationsImp{

     @Override
     public Boolean nomeNaoPodeSerNull(String nome) {
           if(nome == null || nome.isBlank()){
               throw new CampoNaoPodeSerNullException("O campo 'nome' não pode ser null.");
          }
          return true;
     }

     @Override
     public Boolean nomeMaxCharactersOk(String nome) {
          if(nome.length() > 100){
               throw new CampoTemMuitosCharactersException(" 'nome' deve ter no máximo 100 characters.");
          }
          return true;
     }

     @Override
     public Boolean nomeTudoCerto(String nome) {
          this.nomeNaoPodeSerNull(nome);
          this.nomeMaxCharactersOk(nome);

          return true;
     }
}
