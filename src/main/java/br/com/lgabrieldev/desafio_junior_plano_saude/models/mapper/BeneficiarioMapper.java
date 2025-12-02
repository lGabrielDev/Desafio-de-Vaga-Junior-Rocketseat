package br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioFullDto;

public abstract class BeneficiarioMapper {
     
    public  static Beneficiario converterDTOParaBeneficiario(BeneficiarioCreateDto beneficiarioDto){
         
          Beneficiario beneficiario = new Beneficiario();
          beneficiario.setNome(beneficiarioDto.getNome());
          beneficiario.setTelefone(beneficiarioDto.getTelefone());
          beneficiario.setDataNascimento(beneficiarioDto.getDataNascimento());

          //fazer conversao
          beneficiario.setDocumentos(DocumentoMapper.converterTodosDtosParaDocumentos(beneficiarioDto.getDocumentos()));

          return beneficiario;
     }

      public  static BeneficiarioFullDto converterBeneficiarioParaFullDTO(Beneficiario beneficiario){
         
          BeneficiarioFullDto beneficiarioFullDto = new BeneficiarioFullDto();
          beneficiarioFullDto.setId(beneficiario.getId());
          beneficiarioFullDto.setNome(beneficiario.getNome());
          beneficiarioFullDto.setTelefone(beneficiario.getTelefone());
          beneficiarioFullDto.setDataNascimento(beneficiario.getDataNascimento());
          beneficiarioFullDto.setDocumentos(DocumentoMapper.converterTodosDocumentosParaFullDTO(beneficiario.getDocumentos()));

          return beneficiarioFullDto;
     }
}
