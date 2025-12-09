package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.beneficiarioExiste;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioNaoExisteException;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.repository.BeneficiarioRepository;

@Component
public class BeneficiarioExisteValidation {
     
     //attributes
     BeneficiarioRepository beneficiarioRepository;

     //constructors
     public BeneficiarioExisteValidation(BeneficiarioRepository beneficiarioRepository){
          this.beneficiarioRepository = beneficiarioRepository;
     }

     public Boolean BeneficiarioExiste(Long beneficiarioId){
          this.beneficiarioRepository.findById(beneficiarioId).orElseThrow( () -> new BeneficiarioNaoExisteException(beneficiarioId) );
          return true;
     }
}
