package br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioApenasPodeTer1DocumentoPorTipoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.DocumentoJaExisteNoBancoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.DocumentosIguaisException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.TipoDocumentoNaoEncontradoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoDeveTerApenasNumerosException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoTemMuitosCharactersException;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.repository.DocumentoRepository;

@Component
public class DocumentoValidations implements DocumentoValidationImp{

     //attributes
     private final static Set<String> TIPOS_DOCUMENTOS_VALIDOS = Set.of("RG", "CPF", "CNH");
     DocumentoRepository documentoRepository;

     //constructors
     public DocumentoValidations(DocumentoRepository documentoRepository){
          this.documentoRepository = documentoRepository;
     }

     // ****************************************************** validacoes do campo 'tipoDocumento' ****************************************************** 
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

     // ****************************************************** validacoes do campo 'numeroDocumento' ******************************************************
     @Override
     public Boolean numeroDocumentoNaoPodeSerNull(List<DocumentoCreateDto> documentos) {

          Boolean numeroDocumentoErrado =  documentos.stream().anyMatch( i -> i.getNumeroDocumento() == null || i.getNumeroDocumento().isBlank());
          if(numeroDocumentoErrado){
               throw new CampoNaoPodeSerNullException("O campo 'numeroDocumento' não pode ser null.");
          }
          return true;
     }
     
     @Override
     public Boolean numeroDocumentoQuantidadeCharacteresEstaCorreto(List<DocumentoCreateDto> documentos) {

        documentos.stream().anyMatch((documento) -> {
               String tipoDocumento = documento.getTipoDocumento();
               String numeroDocumento = documento.getNumeroDocumento();
               String errorMessage = "";
               Boolean quantidadeErrada = false;

               switch (tipoDocumento.toUpperCase()) {
                    case "RG":
                         if(numeroDocumento.length() != 9){
                              quantidadeErrada = true;
                              errorMessage = String.format(" '%s' deve ter exatamente 9 characteres! ", tipoDocumento);
                         }
                         break;
                    case "CPF":
                    case "CNH":
                         if(numeroDocumento.length() != 11){
                              quantidadeErrada = true;
                              errorMessage = String.format(" '%s' deve ter exatamente 11 characteres! ", tipoDocumento);
                         }
                         break;
               }    
                 if(quantidadeErrada){
                         throw new CampoTemMuitosCharactersException(errorMessage);
                    }
               return false;       // no anyMatch, ou qualquer outro method de filtro, o loop só vai parar se ele retornar true. Enquanto retornar false, o proximo loop acontece.
          }); 
          return true;
     }

     @Override
     public Boolean numeroDocumentoApenasNumeros(List<DocumentoCreateDto> documentos) {
          documentos.stream().anyMatch(documento -> {
               String numeroDocumento = documento.getNumeroDocumento();
               if(  !(numeroDocumento.chars().allMatch(c ->  Character.isDigit(c))) ) {
                    throw new CampoDeveTerApenasNumerosException(" 'numeroDocumento' deve possuir apenas números.");
               }
               return false;  // no anyMatch, ou qualquer outro method de filtro, o loop só vai parar se ele retornar true. Enquanto retornar false, o proximo loop acontece.
          });
          return true;     
     }

     @Override
     public Boolean numeroDosDocumentosDevemSerDiferentes(List<DocumentoCreateDto> documentos) {

          Long numeroDocumentosUnicos = documentos.stream()
               .map((documento) -> documento.getNumeroDocumento())
               .distinct()
               .count();
               
               if(numeroDocumentosUnicos != documentos.size()){
                    throw new DocumentosIguaisException("Os documentos informados possuem o mesmo número. Cada documento deve ser único.");
               }
          return true;
     }

     @Override
     public Boolean numeroDocumentoNaoExisteNoBanco(List<DocumentoCreateDto> documentos) {
          documentos.stream().anyMatch(documento -> {
               String tipoDocumento = documento.getTipoDocumento();
               String numeroDocumento = documento.getNumeroDocumento();

               this.documentoRepository.findDocumentoByNumeroDocumento(numeroDocumento)
                    .ifPresent(documentoEncontrado ->  { //precisa ter {} pra funcionar. No method 'ifPresent()' ele precisa usar 'bloco de código'
                         throw new DocumentoJaExisteNoBancoException(String.format(" o numero do documento '%s' já existe no banco",  tipoDocumento));
                    });

               return false;
          });
          return true;
     }
     
     @Override
     public Boolean numeroDocumentoTudoCerto(List<DocumentoCreateDto> documentos) {
     
          this.numeroDocumentoNaoPodeSerNull(documentos);
          this.numeroDocumentoQuantidadeCharacteresEstaCorreto(documentos);
          this.numeroDocumentoApenasNumeros(documentos);
          this.numeroDosDocumentosDevemSerDiferentes(documentos);
          this.numeroDocumentoNaoExisteNoBanco(documentos);
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