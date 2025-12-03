package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.documentos_validations;

import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.NaoFoiEnviadoNenhumDocumentoParaCadastroException;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;

@Component
public class BeneficiarioQuantidadeDocumentosValidations  implements BeneficiarioQuantidadeDocumentosValidationsImp {

     
       @Override
     public Boolean peloMenosUmDocumento(BeneficiarioCreateDto beneficiarioCreateDto) {
         try{
               beneficiarioCreateDto.getDocumentos().size();
          }
          catch(Exception e){
               throw new NaoFoiEnviadoNenhumDocumentoParaCadastroException("Para cadastrar um beneficiário é necessário informar pelo menos 1 documento --> 'CPF', 'RG' ou 'CNH' ");
          }
          return true;
     }


     public Boolean quantidadeDocumentosTudoCerto(BeneficiarioCreateDto beneficiarioCreateDto){
          this.peloMenosUmDocumento(beneficiarioCreateDto);
          return true;
          
     }
}
