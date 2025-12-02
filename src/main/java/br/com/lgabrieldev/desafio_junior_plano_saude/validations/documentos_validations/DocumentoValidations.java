package br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.TipoDocumentoNaoEncontradoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoTemMuitosCharactersException;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;


@Component
public  class DocumentoValidations implements DocumentoValidationImp{


     // ****************** validacoes do campo 'tipoDocumento' ****************** 
     @Override
     public  Boolean tipoDocumentoNaoPodeSerNull(String tipoDocumento) {
          if(tipoDocumento == null || tipoDocumento.isBlank()){
               throw new CampoNaoPodeSerNullException("O campo 'tipoDocumento' não pode ser null.");
          }
          return true;
     }

     @Override
     public  Boolean tipoDocumentoExiste(String tipoDocumento) {
          switch (tipoDocumento.toUpperCase()) {
               case "RG":
               case "CPF":
               case "CNH":
                    return true;
               default:
                    throw new TipoDocumentoNaoEncontradoException(" 'tipoDocumento' não encontrado!  Os tipos disponíveis são:\n'RG', 'CPF' e 'CNH' ");
          }
     } 

     @Override
     public Boolean tipoDocumentoTudoCerto(String tipoDocumento) {
          this.tipoDocumentoNaoPodeSerNull(tipoDocumento);
          this.tipoDocumentoExiste(tipoDocumento);
          return true;
     }

     // ****************** validacoes do campo 'numeroDocumento' ******************
     @Override
     public Boolean numeroDocumentoNaoPodeSerNull(String numeroDocumento) {
           if(numeroDocumento == null || numeroDocumento.isBlank()){
               throw new CampoNaoPodeSerNullException("O campo 'numeroDocumento' não pode ser null.");
          }
          return true;
     }

     @Override
     public Boolean numeroDocumentoQuantidadeCharacteresEstaCorreto(DocumentoCreateDto documentoCreateDto) {
          String tipoDocumento = documentoCreateDto.getTipoDocumento();
          String numeroDocumento = documentoCreateDto.getNumeroDocumento();

          switch (tipoDocumento.toUpperCase()) {

               case "CPF":
                    if(numeroDocumento.length() != 11){
                         throw new CampoTemMuitosCharactersException(" 'CPF' deve ter exatamente 11 dígitos");
                    }
                    break;
               case "RG":
                    if(numeroDocumento.length() != 9 ){
                         throw new CampoTemMuitosCharactersException("  'RG' deve ter exatamente 9 dígitos");   
                    }
                    break;
               case "CNH":
                     if(numeroDocumento.length() != 11){
                         throw new CampoTemMuitosCharactersException(" 'CNH' deve ter exatamente 11 dígitos");
                    }
                    break;
          }
          return true;
     }

     @Override
     public Boolean numeroDocumentoTudoCerto(DocumentoCreateDto documentoCreateDto) {
          String numeroDocumento = documentoCreateDto.getNumeroDocumento();
          this.numeroDocumentoNaoPodeSerNull(numeroDocumento);
          this.numeroDocumentoQuantidadeCharacteresEstaCorreto(documentoCreateDto);
          return true;
     }

     // ****************** todas as validacoes passaram ******************
     @Override
     public Boolean todosOsCamposEstaoCorretos(DocumentoCreateDto documentoCreateDto) {
          String tipoDocumento = documentoCreateDto.getTipoDocumento();

          this.tipoDocumentoTudoCerto(tipoDocumento);
          this.numeroDocumentoTudoCerto(documentoCreateDto);

          return true;
     }
}