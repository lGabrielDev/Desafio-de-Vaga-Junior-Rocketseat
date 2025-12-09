package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioWithoutDocumentos;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.repository.BeneficiarioRepository;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper.BeneficiarioMapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.AllValidations;

@Service
public class BeneficiarioService {
     
     //attributes
     private AllValidations allValidations;
     private BeneficiarioRepository beneficiarioRepository;

     //constructors
     public BeneficiarioService(AllValidations allValidations,  BeneficiarioRepository beneficiarioRepository){
          this.allValidations = allValidations;
          this.beneficiarioRepository = beneficiarioRepository;
     }



     // ******************************************** Criar Beneficiário ********************************************
     public  BeneficiarioFullDto cadastrarBeneficiario(BeneficiarioCreateDto beneficiarioCreateDto){

          //validar campos
          this.allValidations.validarAttributes(beneficiarioCreateDto);

          //bilateralidade
          Beneficiario beneficiario = BeneficiarioMapper.converterDTOParaBeneficiario(beneficiarioCreateDto);
          //salvar no banco
          this.beneficiarioRepository.save(beneficiario); //nao precisa salvar o lado do 'Documento' porque o Cascade All está ativado. 
          BeneficiarioFullDto beneficiarioFullDto = BeneficiarioMapper.converterBeneficiarioParaFullDTO(beneficiario);
          return beneficiarioFullDto;
 
     }


     // *************************************************** Listar todos  ***************************************************
     public  List<BeneficiarioWithoutDocumentos> listarTodos(){
          return this.beneficiarioRepository.findAll().stream()
               .map(beneficiario -> BeneficiarioMapper.converterBeneficiarioParaBeneficiarioWithoutDocumentos(beneficiario))
               .collect(Collectors.toList());
     }
}
