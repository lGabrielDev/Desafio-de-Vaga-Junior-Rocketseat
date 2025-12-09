package br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations;
import java.util.List;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;

public interface DocumentoValidationImp {
     
     // validacoes tipo documento
     public Boolean tipoDocumentoNaoPodeSerNull(List<DocumentoCreateDto> documentos);
     public Boolean tipoDocumentoExiste(List<DocumentoCreateDto> documentos);
      public Boolean umTipoParaCadaDocumento(List<DocumentoCreateDto> documentos);
     public Boolean tipoDocumentoTudoCerto(List<DocumentoCreateDto> documentos);
   
     // validacoes numero documento
     public Boolean numeroDocumentoNaoPodeSerNull(List<DocumentoCreateDto> documentos);
     public Boolean numeroDocumentoQuantidadeCharacteresEstaCorreto(List<DocumentoCreateDto> documentos);
     public Boolean numeroDocumentoApenasNumeros(List<DocumentoCreateDto> documentos);
     public Boolean numeroDosDocumentosDevemSerDiferentes(List<DocumentoCreateDto> documentos);
     public Boolean numeroDocumentoNaoExisteNoBanco(List<DocumentoCreateDto> documentos);
     public Boolean numeroDocumentoTudoCerto(List<DocumentoCreateDto> documentos);
     

     public Boolean todosOsCamposEstaoCorretos(BeneficiarioCreateDto beneficiarioCreateDto);
     
}
