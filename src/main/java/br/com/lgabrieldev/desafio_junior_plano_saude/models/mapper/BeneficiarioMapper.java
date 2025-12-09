package br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioWithoutDocumentos;

public abstract class BeneficiarioMapper {
     
    public static Beneficiario converterDTOParaBeneficiario(BeneficiarioCreateDto beneficiarioDto){
          Beneficiario beneficiario = new Beneficiario();
               beneficiario.setNome(beneficiarioDto.getNome());
               beneficiario.setTelefone(beneficiarioDto.getTelefone());
               beneficiario.setDataNascimento(beneficiarioDto.getDataNascimento());
          //bilateralidade
          DocumentoMapper.converterTodosDtosParaDocumentos(beneficiarioDto.getDocumentos()).stream()
               .forEach((documento) -> {
                    beneficiario.getDocumentos().add(documento); //sempre comecams pelo lado que iniciou a relação. 
                    documento.setBeneficiario(beneficiario);
               });
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

     public  static BeneficiarioWithoutDocumentos converterBeneficiarioParaBeneficiarioWithoutDocumentos(Beneficiario beneficiario){
          BeneficiarioWithoutDocumentos beneficiarioWithoutDocumentos = new BeneficiarioWithoutDocumentos();
               beneficiarioWithoutDocumentos.setId(beneficiario.getId());
               beneficiarioWithoutDocumentos.setNome(beneficiario.getNome());
               beneficiarioWithoutDocumentos.setTelefone(beneficiario.getTelefone());
               beneficiarioWithoutDocumentos.setDataNascimento(beneficiario.getDataNascimento());
               beneficiarioWithoutDocumentos.setQuantidadeDocumentos(beneficiario.getDocumentos().size());
          return beneficiarioWithoutDocumentos;
     }

     //ATUALIZAR dados beneficiario
     // public  static BeneficiarioWithoutDocumentos atualizarDados(Beneficiario beneficiario, BeneficiarioCreateDto dto){
     //      if(dto.getNome() == null){

     //      }

     //      return null;
     // }

}
