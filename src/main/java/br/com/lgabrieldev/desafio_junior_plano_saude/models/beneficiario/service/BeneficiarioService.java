package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.service;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper.BeneficiarioMapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.BeneficiarioValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations.DocumentoValidations;

@Service
public class BeneficiarioService {
     
     //attributes
     private DocumentoValidations documentoValidations; // remover
     private BeneficiarioValidations beneficiarioValidations; //remover e colocar apenas 1 attribute de validacao insano

     //constructors
     public BeneficiarioService(DocumentoValidations documentoValidations,  BeneficiarioValidations beneficiarioValidations){
          this.documentoValidations = documentoValidations;
          this.beneficiarioValidations = beneficiarioValidations;
     }



     // ******************************************** Criar Beneficiário ********************************************
     @PostMapping("")
     public  BeneficiarioFullDto cadastrarBeneficiario(@RequestBody BeneficiarioCreateDto beneficiarioCreateDto){

          // validando os documentos
          this.documentoValidations.todosOsCamposEstaoCorretos(beneficiarioCreateDto.getDocumentos().get(0)); // FIXXXXXXX. FAZER O LOOP BOLADO
          this.beneficiarioValidations.todosOsCamposEstaoCorretos(beneficiarioCreateDto);

          // validando o beneficiario



          Beneficiario beneficiario = BeneficiarioMapper.converterDTOParaBeneficiario(beneficiarioCreateDto);
          BeneficiarioFullDto beneficiarioFullDto = BeneficiarioMapper.converterBeneficiarioParaFullDTO(beneficiario);

          return beneficiarioFullDto;
 
     }
}
