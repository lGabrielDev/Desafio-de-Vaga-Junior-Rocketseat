package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.name_validations.NomeValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.telefoneValidations.TelefoneValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.data_nascimento_validations.DataNascimentoValidations;

@Component
public class BeneficiarioValidations implements BeneficiarioValidationsImp {


     //attributes
     private NomeValidations nameValidations;
     private TelefoneValidations telefoneValidations;
     private DataNascimentoValidations dataNascimentoValidations;

     //constructors
     public BeneficiarioValidations(
               NomeValidations nameValidations,
               TelefoneValidations telefoneValidations,
               DataNascimentoValidations dataNascimentoValidations)
          {
          this.nameValidations = nameValidations;
          this.telefoneValidations = telefoneValidations;
          this.dataNascimentoValidations = dataNascimentoValidations;
     }



     // -------------------------- tudo validado --------------------------
     @Override
     public Boolean todosOsCamposEstaoCorretos(BeneficiarioCreateDto beneficiarioCreateDto) {
          String nome = beneficiarioCreateDto.getNome();
          String telefone = beneficiarioCreateDto.getTelefone();
          LocalDate dataNascimento = beneficiarioCreateDto.getDataNascimento();

          this.nameValidations.nomeTudoCerto(nome);
          this.telefoneValidations.telefoneTudoCerto(telefone);
          this.dataNascimentoValidations.dataNascimentoTudoCerto(dataNascimento);

          return true;
     }

     
     
}


