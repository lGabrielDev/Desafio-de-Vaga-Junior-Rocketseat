package br.com.lgabrieldev.desafio_junior_plano_saude.validations.data_nascimento_validations;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioDeveSerMaiorDeIdadeException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;

@Component
public class DataNascimentoValidations  implements DataNascimentoValidationsImp {

     @Override
     public Boolean dataNascimentoNaoPodeSerNull(LocalDate data) {
           if(data == null ){
               throw new CampoNaoPodeSerNullException("dataNascimento");
          }
          return true;
     }

     @Override
     public Boolean dataNascimentoMaior18Anos(LocalDate data) {
          Integer idade = Period.between(data, LocalDate.now()).getYears() ;
          
          if(idade < 18){
               throw new BeneficiarioDeveSerMaiorDeIdadeException();
          }
          return true;
     }

     @Override
     public Boolean dataNascimentoTudoCerto(LocalDate  dataNascimento) {
          this.dataNascimentoNaoPodeSerNull(dataNascimento);
          this.dataNascimentoMaior18Anos(dataNascimento);

          return true;
     }
}