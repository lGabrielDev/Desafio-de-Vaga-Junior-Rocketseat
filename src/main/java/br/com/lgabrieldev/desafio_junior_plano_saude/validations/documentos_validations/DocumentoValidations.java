package br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations;

import java.util.List;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioApenasPodeTer1DocumentoPorTipoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.TipoDocumentoNaoEncontradoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoDeveTerApenasNumerosException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoTemMuitosCharactersException;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.enums.TipoDocumento;


@Component
public  class DocumentoValidations implements DocumentoValidationImp{


     // ****************** validacoes do campo 'tipoDocumento' ****************** 
     @Override
     public  Boolean tipoDocumentoNaoPodeSerNull(List<DocumentoCreateDto> documentos) {

               Boolean tipoDocumentoErrado =  documentos.stream().anyMatch( i -> i.getTipoDocumento() == null || i.getTipoDocumento().isBlank());
               
               if(tipoDocumentoErrado){
                    throw new CampoNaoPodeSerNullException("O campo 'tipoDocumento' não pode ser null.");
               }
               return true;
     }

     // CONTINUAR AQUI --->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     @Override
     public  Boolean tipoDocumentoExiste(List<DocumentoCreateDto> documentos) {
          
          for(DocumentoCreateDto i : documentos){ 
               try{
                    switch (i.getTipoDocumento().toUpperCase()) { //se o usuario nao informar  o 'tipoDocumento', o method getTipoDocumento() retorna null e dá problema... Por isso o try catch
                         case "RG":
                         case "CPF":
                         case "CNH":
                              break;
                              //
                         default:
                              throw new TipoDocumentoNaoEncontradoException(" 'tipoDocumento' não encontrado!  Os tipos disponíveis são:\n'RG', 'CPF' e 'CNH' ");
                    }
               }
               catch(Exception e){
                    throw new TipoDocumentoNaoEncontradoException(" 'tipoDocumento' não encontrado!  Os tipos disponíveis são:\n'RG', 'CPF' e 'CNH' ");
               }
          }
          return true;
     } 

      @Override
     public Boolean umTipoParaCadaDocumento(List<DocumentoCreateDto> documentos) {
          
          Integer quantosDocumentosCPF = 0;
          Integer quantosDocumentosRG = 0;
          Integer quantosDocumentosCNH = 0;

          for(DocumentoCreateDto i : documentos ){
               if(i.getTipoDocumento().toUpperCase().equals(TipoDocumento.CPF.getTipo())){
                    quantosDocumentosCPF +=1;
               }
               else if(i.getTipoDocumento().toUpperCase().equals(TipoDocumento.RG.getTipo())){
                    quantosDocumentosRG +=1;
               }
               else if(i.getTipoDocumento().toUpperCase().equals(TipoDocumento.CNH.getTipo())){
                     quantosDocumentosCNH +=1;
               }
          }

          if(quantosDocumentosCPF > 1 ||  quantosDocumentosRG > 1 || quantosDocumentosCNH > 1){
               throw new BeneficiarioApenasPodeTer1DocumentoPorTipoException("Nao eh possível ter mais de 1 CPF\nNão eh possivel ter mais de 1 RG\nNao eh possível ter mais de 1 CNH");
          }

          return true;
     }


     @Override
     public Boolean tipoDocumentoTudoCerto(List<DocumentoCreateDto> documentos) {
          this.tipoDocumentoNaoPodeSerNull(documentos);
          this.tipoDocumentoExiste(documentos);
          this.umTipoParaCadaDocumento(documentos);
          return true;
     }

     

     // ****************** validacoes do campo 'numeroDocumento' ******************
     @Override
     public Boolean numeroDocumentoNaoPodeSerNull(List<DocumentoCreateDto> documentos) {
           for(DocumentoCreateDto i : documentos){
                if(i.getNumeroDocumento() == null || i.getNumeroDocumento().isBlank()){
                    throw new CampoNaoPodeSerNullException("O campo 'numeroDocumento' não pode ser null.");
               }
           }
          return true;
     }

    
     
     
     
     
     




     @Override
     public Boolean numeroDocumentoQuantidadeCharacteresEstaCorreto(List<DocumentoCreateDto> documentos) {
          String tipoDocumento = "";
          String numeroDocumento = "";

          for(DocumentoCreateDto i : documentos){
                    tipoDocumento = i.getTipoDocumento();
                    numeroDocumento = i.getNumeroDocumento();

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
          }
          return true;
     }


     @Override
     public Boolean numeroDocumentoApenasNumeros(List<DocumentoCreateDto> documentos) {
          String numeroDocumento = "";

          for(DocumentoCreateDto i : documentos){
               numeroDocumento = i.getNumeroDocumento();
               if(  !(numeroDocumento.chars().allMatch(c ->  Character.isDigit(c))) ) {
                    throw new CampoDeveTerApenasNumerosException(" 'numeroDocumento' deve possuir apenas números.");
               }
          }
          return true;
     }

     @Override
     public Boolean numeroDocumentoTudoCerto(List<DocumentoCreateDto> documentos) {
     
          this.numeroDocumentoNaoPodeSerNull(documentos);
          
          this.numeroDocumentoQuantidadeCharacteresEstaCorreto(documentos);
          this.numeroDocumentoApenasNumeros(documentos);
          return true;
     }

     // ****************** todas as validacoes passaram ******************
     @Override
     public Boolean todosOsCamposEstaoCorretos(BeneficiarioCreateDto beneficiarioCreateDto) {
          List<DocumentoCreateDto> documentos = beneficiarioCreateDto.getDocumentos();

          this.tipoDocumentoTudoCerto(documentos);
          this.numeroDocumentoTudoCerto(documentos);

          return true;
     }
}