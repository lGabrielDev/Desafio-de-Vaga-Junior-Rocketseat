package br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper;

import java.util.List;
import java.util.stream.Collectors;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.Documento;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.enums.TipoDocumento;

public abstract class DocumentoMapper {

     // converter DocumentoCreateDTO --> Documento
     public  static Documento converterDTOParaDocumento(DocumentoCreateDto documentDto){
          var documento = new Documento();

          String tipoDocumentoAsString = documentDto.getTipoDocumento();
          documento.setTipoDocumento(converterTipoDocumentoStringParaEnum(tipoDocumentoAsString)); 
          documento.setNumeroDocumento(documentDto.getNumeroDocumento());
          return documento;
     }

     public static List<Documento> converterTodosDtosParaDocumentos(List<DocumentoCreateDto> documentosDtos){
          return
          documentosDtos.stream()
               .map( dto -> converterDTOParaDocumento(dto))
               .collect(Collectors.toList());
     }

     //converter Documento --> DocumentoFullDto
      public  static DocumentoFullDto converterDocumentoParaFullDTO(Documento documento){
          var fullDTO = new DocumentoFullDto();
          fullDTO.setId(documento.getId());
          fullDTO.setTipoDocumento(documento.getTipoDocumento().getTipo());
          fullDTO.setNumeroDocumento(documento.getNumeroDocumento());
          fullDTO.setDataInclusao(documento.getDataInclusao());
          fullDTO.setDataUltimaAtualizacao(documento.getDataUltimaAtualizacao());

          return fullDTO;
     }

      public static List<DocumentoFullDto> converterTodosDocumentosParaFullDTO(List<Documento> documentos){
          return
          documentos.stream()
               .map( documento -> converterDocumentoParaFullDTO(documento))
               .collect(Collectors.toList());
     }

     //converter documento em String  --> documento Enum
     public static TipoDocumento converterTipoDocumentoStringParaEnum(String tipoDocumentoString){


          switch(tipoDocumentoString.toUpperCase()){
               case "CPF": 
                    return TipoDocumento.CPF;
               case "RG":
                    return TipoDocumento.RG;
               case "CNH":
                    return TipoDocumento.CNH;
               default: return null;
          }

     }
}