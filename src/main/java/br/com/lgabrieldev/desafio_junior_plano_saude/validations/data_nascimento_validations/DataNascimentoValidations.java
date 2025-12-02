package br.com.lgabrieldev.desafio_junior_plano_saude.validations.data_nascimento_validations;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;

@Component
public class DataNascimentoValidations  implements DataNascimentoValidationsImp {

     @Override
     public Boolean dataNascimentoNaoPodeSerNull(LocalDate data) {
           if(data == null ){
               throw new CampoNaoPodeSerNullException("O campo 'dataNascimento' não pode ser null.");
          }
          return true;
     }


     @Override
     public Boolean dataNascimentoMaior18Anos(LocalDate data) {
          return true;
     }



     @Override
     public Boolean dataNascimentoTudoCerto(LocalDate  dataNascimento) {
        

          this.dataNascimentoNaoPodeSerNull(dataNascimento);

          // this.formatoPadraoBrasil(dataNascimento);
          // this.dataNascimentoMaior18Anos(dataNascimento);

          return true;
     }
}
