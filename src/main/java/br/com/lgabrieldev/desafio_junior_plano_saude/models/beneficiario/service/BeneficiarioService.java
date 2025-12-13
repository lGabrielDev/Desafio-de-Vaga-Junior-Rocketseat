package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioNaoExisteException;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioWithoutDocumentos;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.repository.BeneficiarioRepository;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.repository.DocumentoRepository;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper.BeneficiarioMapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.mapper.DocumentoMapper;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.AllValidations;
import br.com.lgabrieldev.desafio_junior_plano_saude.validations.ValidationsDeAtualizacao;

@Service
public class BeneficiarioService {
     
     //attributes
     private AllValidations allValidations;
     private BeneficiarioRepository beneficiarioRepository;
     private DocumentoRepository documentoRepository;
     private ValidationsDeAtualizacao validationsDeAtualizacao;

     //constructors
     public BeneficiarioService(AllValidations allValidations,  BeneficiarioRepository beneficiarioRepository, DocumentoRepository documentoRepository, ValidationsDeAtualizacao validationsDeAtualizacao){
          this.allValidations = allValidations;
          this.beneficiarioRepository = beneficiarioRepository;   
          this.documentoRepository = documentoRepository;
          this.validationsDeAtualizacao = validationsDeAtualizacao;
     }

     // ******************************************** CRIAR Beneficiário ********************************************
     public BeneficiarioFullDto cadastrarBeneficiario(BeneficiarioCreateDto beneficiarioCreateDto){

          //validar campos
          this.allValidations.validarAttributes(beneficiarioCreateDto);

          //bilateralidade
          Beneficiario beneficiario = BeneficiarioMapper.converterDTOParaBeneficiario(beneficiarioCreateDto);
          //salvar no banco
          this.beneficiarioRepository.save(beneficiario); //nao precisa salvar o lado do 'Documento' porque o Cascade All está ativado. 
          BeneficiarioFullDto beneficiarioFullDto = BeneficiarioMapper.converterBeneficiarioParaFullDTO(beneficiario);
          return beneficiarioFullDto;
 
     }

     // *************************************************** LISTAR todos  ***************************************************
     public  List<BeneficiarioWithoutDocumentos> listarTodos(){
          return this.beneficiarioRepository.findAll().stream()
               .map(beneficiario -> BeneficiarioMapper.converterBeneficiarioParaBeneficiarioWithoutDocumentos(beneficiario))
               .collect(Collectors.toList());
     }

     // *************************************************** LISTAR todos os documentos de um beneficiario  ***************************************************
     public  List<DocumentoFullDto> listarTodosDocumentos(Long beneficiarioId){
          this.allValidations.beneficiarioExiste(beneficiarioId);
           return this.documentoRepository.findDocumentoByBeneficiarioId(beneficiarioId).stream()
               .map(documento -> DocumentoMapper.converterDocumentoParaFullDTO(documento))
               .collect(Collectors.toList());
     }

     // ******************************************** ALTERAR dados de um Beneficiário ********************************************
     public BeneficiarioFullDto atualizarBeneficiario(Long beneficiarioId, BeneficiarioCreateDto dto){

          this.allValidations.beneficiarioExiste(beneficiarioId);
          Beneficiario beneficiario =  this.beneficiarioRepository.findById(beneficiarioId).get();
          this.validationsDeAtualizacao.todosOsCamposEstaoCorretos(beneficiario, dto);
          //salvar no banco
          this.beneficiarioRepository.save(beneficiario); //nao precisa salvar o lado do 'Documento' porque o Cascade All está ativado. 
          BeneficiarioFullDto beneficiarioFullDto = BeneficiarioMapper.converterBeneficiarioParaFullDTO(beneficiario);
          return beneficiarioFullDto;
     }

     // ******************************************** DELETAR dados de um Beneficiário ********************************************
     public String deletarBeneficiario(Long beneficiarioId){
          this.beneficiarioRepository.findById(beneficiarioId).orElseThrow(() -> new BeneficiarioNaoExisteException(beneficiarioId));
          this.beneficiarioRepository.deleteById(beneficiarioId);
          return String.format("Beneficiario #%d deletado com sucesso", beneficiarioId);
     }
}