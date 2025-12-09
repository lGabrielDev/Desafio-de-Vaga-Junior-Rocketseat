package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;

public interface BeneficiarioValidationsImp {
     
      public Boolean todosOsCamposEstaoCorretos(BeneficiarioCreateDto beneficiarioCreateDto);
}
