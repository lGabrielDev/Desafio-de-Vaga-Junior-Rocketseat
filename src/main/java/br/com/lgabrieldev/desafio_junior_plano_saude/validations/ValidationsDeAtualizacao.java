package br.com.lgabrieldev.desafio_junior_plano_saude.validations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
          //novos docs
          List<Documento> documentosNovos = DocumentoMapper.converterTodosDtosParaDocumentos(dto.getDocumentos());

          //verificamos o tipo dos novos documentos enviados. Esses sao os tipos que precisamos substituir
          Set<String> tiposParaSubstituir = documentosNovos.stream()
               .map((documentos) -> documentos.getTipoDocumento().getTipo())
               .collect(Collectors.toSet());

          //filtramos os documentos antigos que tenham esses tipos
          List<Documento> documentosAntigosParaSeremExcluidos = documentosAntigos.stream()
               .filter((documentoAntigo) -> tiposParaSubstituir.contains(documentoAntigo.getTipoDocumento().getTipo()))
               .collect(Collectors.toList());
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

          if(dto.getDocumentos() != null){
               this.camposDocumentosOk(beneficiario, dto);
          }
          
          beneficiario.setDataUltimaAtualizacao(LocalDateTime.now());

          return true;
     }
}
