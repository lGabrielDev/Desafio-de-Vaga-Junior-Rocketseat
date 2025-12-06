package br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioApenasPodeTer1DocumentoPorTipoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.TipoDocumentoNaoEncontradoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoDeveTerApenasNumerosException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoTemMuitosCharactersException;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;

@Component
public  class DocumentoValidations implements DocumentoValidationImp{

     //attributes
     private final static Set<String> TIPOS_DOCUMENTOS_VALIDOS = Set.of("RG", "CPF", "CNH");

     // ****************** validacoes do campo 'tipoDocumento' ****************** 
     @Override
     public  Boolean tipoDocumentoNaoPodeSerNull(List<DocumentoCreateDto> documentos) {

               Boolean tipoDocumentoErrado =  documentos.stream().anyMatch( i -> i.getTipoDocumento() == null || i.getTipoDocumento().isBlank());
               
               if(tipoDocumentoErrado){
                    throw new CampoNaoPodeSerNullException("O campo 'tipoDocumento' não pode ser null.");
               }
               return true;
     }

     @Override
     public  Boolean tipoDocumentoExiste(List<DocumentoCreateDto> documentos) {    

          Boolean tipoInvalidoEncontrado = documentos.stream().anyMatch((document) -> {
               String tipoDocumento = document.getTipoDocumento();

               return !(TIPOS_DOCUMENTOS_VALIDOS.contains(tipoDocumento.toUpperCase()));
          });

               if(tipoInvalidoEncontrado){
                    throw new TipoDocumentoNaoEncontradoException(" 'tipoDocumento' não encontrado!  Os tipos disponíveis são:\n'RG', 'CPF' e 'CNH' ");
               }
               return true;
     }

     @Override
     public Boolean umTipoParaCadaDocumento(List<DocumentoCreateDto> documentos) {

         Map<String, Long>  tipoDocumentoAgrupado = documentos.stream()
               .collect(Collectors.groupingBy(

                    //lembrando, que aqui o tipo de documento já está correto porque verificamos no method acima ^^
                    document -> document.getTipoDocumento().toUpperCase(), //  1º parametro --> como quero agrupar
                    Collectors.counting() //  2º parametro --> a informacao que quero informar. Se fosse campo de valor, poderia somar, etc...
               ));

             tipoDocumentoAgrupado.forEach((chave, valor) -> {
                    System.out.println(String.format("%s --> %d", chave, valor));
             });


          Boolean temDocumentoDuplicado = tipoDocumentoAgrupado.values().stream()
               .anyMatch((valor) ->  valor >= 2);

               if(temDocumentoDuplicado){
                    throw new BeneficiarioApenasPodeTer1DocumentoPorTipoException("Nao eh possível ter mais de 1 CPF\nNão eh possivel ter mais de 1 RG\nNao eh possível ter mais de 1 CNH");
               };
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




     // CONTINUAR AQUI -->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
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