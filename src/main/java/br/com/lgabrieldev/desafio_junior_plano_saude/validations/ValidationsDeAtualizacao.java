package br.com.lgabrieldev.desafio_junior_plano_saude.validations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.Documento;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper.DocumentoMapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.name_validations.NomeValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.telefoneValidations.TelefoneValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.data_nascimento_validations.DataNascimentoValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.documentos_validations.DocumentoValidations;

@Component
public class ValidationsDeAtualizacao {
     
     //attributes
     private NomeValidations nameValidations;
     private TelefoneValidations telefoneValidations;
     private DataNascimentoValidations dataNascimentoValidations;
     private DocumentoValidations documentoValidations;

      //constructors
     public ValidationsDeAtualizacao(
               NomeValidations nameValidations,
               TelefoneValidations telefoneValidations,
               DataNascimentoValidations dataNascimentoValidations,
               DocumentoValidations documentoValidations
          )
     {
          this.nameValidations = nameValidations;
          this.telefoneValidations = telefoneValidations;
          this.dataNascimentoValidations = dataNascimentoValidations;
          this.documentoValidations = documentoValidations;

     }


     public Boolean camposBeneficiarioOk(Beneficiario beneficiario, BeneficiarioCreateDto dto){
          String nome = dto.getNome();
          if(nome != null){
               this.nameValidations.nomeTudoCerto(nome);
               beneficiario.setNome(nome);
          }

          String telefone = dto.getTelefone();
          if(telefone != null){
               this.telefoneValidations.telefoneTudoCerto(telefone);
               beneficiario.setTelefone(telefone);
          }
 
          LocalDate dataNascimento = dto.getDataNascimento();
          if(dataNascimento != null){
               this.dataNascimentoValidations.dataNascimentoTudoCerto(dataNascimento);
               beneficiario.setDataNascimento(dataNascimento);
          }

          return true;
     }

     public Boolean camposDocumentosOk(Beneficiario beneficiario, BeneficiarioCreateDto dto){
          List<DocumentoCreateDto> documentosDtos = dto.getDocumentos();

          //validar os documentos enviados
          if(documentosDtos != null){
               this.documentoValidations.todosOsCamposEstaoCorretos(dto);
          }

          //docs antigos
          List<Documento> documentosAntigos = beneficiario.getDocumentos();
          //docs antigos para excluir
          List<Documento> documentosAntigosParaSeremExcluidos = new ArrayList<>();

          //novos docs dto
          List<DocumentoCreateDto> documentosNovosDto = dto.getDocumentos();
          //novos docs
          List<Documento> documentosNovos = DocumentoMapper.converterTodosDtosParaDocumentos(dto.getDocumentos());


          //loopamos os novos documentos
          for(DocumentoCreateDto i : documentosNovosDto){
               String tipoNovoDocumento = i.getTipoDocumento();

               //loopamos os documentos antigos
               for(Documento docAntigo : documentosAntigos){
                    String tipoAntigoDocumento = docAntigo.getTipoDocumento().getTipo();


                    if(tipoAntigoDocumento.equals(tipoNovoDocumento.toUpperCase())){
                         documentosAntigosParaSeremExcluidos.add(docAntigo);
                    }
               }
          }
          // adicionamos os novos documentos
          beneficiario.getDocumentos().removeAll(documentosAntigosParaSeremExcluidos); // Não precisamos criar a  query SQL  'DELETE FROM Documento WHERE id = ?'  porque já setamos pra ser criado automaticamente,  usando o 'orphanRemoval = true' na entidade.
          // bilateralidade
          beneficiario.getDocumentos().addAll(documentosNovos); //sempre começa pela entidade que iniciou a relação
          documentosNovos.stream().forEach(documentoNovo -> documentoNovo.setBeneficiario(beneficiario));

          
          return true;
     }

// -------------------------- tudo validado --------------------------
     public Boolean todosOsCamposEstaoCorretos(Beneficiario beneficiario, BeneficiarioCreateDto dto) {
          this.camposBeneficiarioOk(beneficiario, dto);
          this.camposDocumentosOk(beneficiario, dto);
          return true;
     }
}
