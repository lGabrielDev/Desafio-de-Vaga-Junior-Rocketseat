package br.com.lgabrieldev.desafio_junior_plano_saude.validations;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.BeneficiarioValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.beneficiarioExiste.BeneficiarioExisteValidation;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations.DocumentoValidations;

@Component
public class AllValidations {
      //attributes
     private DocumentoValidations documentoValidations; 
     private BeneficiarioValidations beneficiarioValidations; 
     private BeneficiarioExisteValidation beneficiarioExisteValidation; 
     

     //constructors
     public AllValidations(DocumentoValidations documentoValidations,  BeneficiarioValidations beneficiarioValidations, BeneficiarioExisteValidation beneficiarioExisteValidation){
          this.documentoValidations = documentoValidations;
          this.beneficiarioValidations = beneficiarioValidations;
          this.beneficiarioExisteValidation = beneficiarioExisteValidation;
     }

     //****************** validacoes para CRIAR beneficiario ******************
     public Boolean validarAttributes(@RequestBody BeneficiarioCreateDto beneficiarioCreateDto){
       
          // validando o beneficiario
          this.beneficiarioValidations.todosOsCamposEstaoCorretos(beneficiarioCreateDto);
          // validando os documentos
          this.documentoValidations.todosOsCamposEstaoCorretos(beneficiarioCreateDto); 
          return true;
     }


     // ****************** verificar se um beneficiario existe pelo #ID ******************
     public Boolean beneficiarioExiste(Long beneficiarioId){
          this.beneficiarioExisteValidation.BeneficiarioExiste(beneficiarioId);
          return true;
     }
}