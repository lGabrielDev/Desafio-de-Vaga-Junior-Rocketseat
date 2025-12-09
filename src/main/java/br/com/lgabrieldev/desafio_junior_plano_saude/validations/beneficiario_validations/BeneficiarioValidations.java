package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.documentos_validations.BeneficiarioQuantidadeDocumentosValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.name_validations.NomeValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.telefoneValidations.TelefoneValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.data_nascimento_validations.DataNascimentoValidations;

@Component
public class BeneficiarioValidations implements BeneficiarioValidationsImp {

     //attributes
     private NomeValidations nameValidations;
     private TelefoneValidations telefoneValidations;
     private DataNascimentoValidations dataNascimentoValidations;
     private BeneficiarioQuantidadeDocumentosValidations beneficiarioQuantidadeDocumentosValidations;

     //constructors
     public BeneficiarioValidations(
               NomeValidations nameValidations,
               TelefoneValidations telefoneValidations,
               DataNascimentoValidations dataNascimentoValidations,
               BeneficiarioQuantidadeDocumentosValidations beneficiarioQuantidadeDocumentosValidations
          )
     {
          this.nameValidations = nameValidations;
          this.telefoneValidations = telefoneValidations;
          this.dataNascimentoValidations = dataNascimentoValidations;
          this.beneficiarioQuantidadeDocumentosValidations = beneficiarioQuantidadeDocumentosValidations;
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
          this.beneficiarioQuantidadeDocumentosValidations.quantidadeDocumentosTudoCerto(beneficiarioCreateDto);

          return true;
     }
}


