package br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations;

import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;

public interface DocumentoValidationImp {
     

     public Boolean tipoDocumentoNaoPodeSerNull(String tipoDocumento);
     public Boolean tipoDocumentoExiste(String tipoDocumento);
     public Boolean tipoDocumentoTudoCerto(String tipoDocumento);
   

     public Boolean numeroDocumentoNaoPodeSerNull(String numeroDocumento);
     public Boolean numeroDocumentoQuantidadeCharacteresEstaCorreto(DocumentoCreateDto documentoCreateDto);
     public Boolean numeroDocumentoTudoCerto(DocumentoCreateDto documentoCreateDto);

     public Boolean todosOsCamposEstaoCorretos(DocumentoCreateDto documentoCreateDto);
     
}
