package br.com.lgabrieldev.desafio_junior_plano_saude.validations;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.repository.BeneficiarioRepository;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.Documento;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.repository.DocumentoRepository;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.documentos_validations.BeneficiarioQuantidadeDocumentosValidations;
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
     private DocumentoRepository documentoRepository;

      //constructors
     public ValidationsDeAtualizacao(
               NomeValidations nameValidations,
               TelefoneValidations telefoneValidations,
               DataNascimentoValidations dataNascimentoValidations,
               DocumentoValidations documentoValidations,
               DocumentoRepository documentoRepository
          )
     {
          this.nameValidations = nameValidations;
          this.telefoneValidations = telefoneValidations;
          this.dataNascimentoValidations = dataNascimentoValidations;
          this.documentoValidations = documentoValidations;
          this.documentoRepository = documentoRepository;

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


     // -->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> CONTINUAR AQUI
     public Boolean camposDocumentosOk(Beneficiario beneficiario, BeneficiarioCreateDto dto){
          List<DocumentoCreateDto> documentosDtos = dto.getDocumentos();

          //validar os documentos enviados
          if(documentosDtos != null){
               this.documentoValidations.todosOsCamposEstaoCorretos(dto);
          }

          //verificamos se o beneficiario ja possui esse documento. Se sim, removemos ele e adicionamos esse novo
          List<Documento> tiposDocumentosAntigos = beneficiario.getDocumentos().stream()
               .map(documento -> documento)
               .collect(Collectors.toList());

         List<Documento> tiposDocumentosNovos =  dto.getDocumentos().stream()
               .map(dtoDocumento -> dtoDocumento)
               .collect(Collectors.toList());

               for(Documento i : tiposDocumentosAntigos){

                    for(Documento j : tiposDocumentosNovos){
                         if(j.getTipoDocumento().equals(i.getTipoDocumento())){

                              //removemos o documento do banco
                              this.documentoRepository.findDocumentoByBeneficiarioIdAndTipoDocumento(beneficiario.getId(),  j.getTipoDocumento());
                              this.documentoRepository.deleteById(null);

                              Documento documentoAtualizado = j;

                              //removemos o documento do banco
                              beneficiario.getDocumentos().add(documentoAtualizado); //sempre comecams pelo lado que iniciou a relação. 
                              documentoAtualizado.setBeneficiario(beneficiario);
                              
                                   documentoAtualizado.setTipoDocumento(null);
                         }
                    }
               }
            




               tiposDocumentosAntigos.forEach((tipo) -> System.out.println("TIPO DO DOCUMENTO --> " + tipo));
               

          return true;
     }

// -------------------------- tudo validado --------------------------
     public Boolean todosOsCamposEstaoCorretos(Beneficiario beneficiario, BeneficiarioCreateDto dto) {
          this.camposBeneficiarioOk(beneficiario, dto);
          this.camposDocumentosOk(beneficiario, dto);



          // List<DocumentoCreateDto> documentosDto = dto.getDocumentos();
          //  if(documentosDto != null){
          //      this.documentoValidations.todosOsCamposEstaoCorretos(documentosDto);
          // }


          return true;
     }



}
