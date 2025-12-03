package br.com.lgabrieldev.desafio_junior_plano_saude.validations;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.BeneficiarioValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations.DocumentoValidations;

@Component
public class AllValidations {
      //attributes
     private DocumentoValidations documentoValidations; // remover
     private BeneficiarioValidations beneficiarioValidations; //remover e colocar apenas 1 attribute de validacao insano

     //constructors
     public AllValidations(DocumentoValidations documentoValidations,  BeneficiarioValidations beneficiarioValidations){
          this.documentoValidations = documentoValidations;
          this.beneficiarioValidations = beneficiarioValidations;
     }

     public Boolean validarAttributes(@RequestBody BeneficiarioCreateDto beneficiarioCreateDto){
       
          // validando o beneficiario
          this.beneficiarioValidations.todosOsCamposEstaoCorretos(beneficiarioCreateDto);
          // validando os documentos
          this.documentoValidations.todosOsCamposEstaoCorretos(beneficiarioCreateDto); 
          return true;
     }
}