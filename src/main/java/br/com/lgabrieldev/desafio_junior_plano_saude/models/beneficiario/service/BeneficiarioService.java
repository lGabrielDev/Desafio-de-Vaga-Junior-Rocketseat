package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper.BeneficiarioMapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.AllValidations;

@Service
public class BeneficiarioService {
     
     //attributes
     @Autowired
     private AllValidations allValidations;

     //constructors
     public BeneficiarioService(AllValidations allValidations){
          this.allValidations = allValidations;
     }



     // ******************************************** Criar Beneficiário ********************************************
     public  BeneficiarioFullDto cadastrarBeneficiario(BeneficiarioCreateDto beneficiarioCreateDto){

          this.allValidations.validarAttributes(beneficiarioCreateDto);



          Beneficiario beneficiario = BeneficiarioMapper.converterDTOParaBeneficiario(beneficiarioCreateDto);
          BeneficiarioFullDto beneficiarioFullDto = BeneficiarioMapper.converterBeneficiarioParaFullDTO(beneficiario);

          return beneficiarioFullDto;
 
     }
}
