package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioFullDto;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioWithoutDocumentos;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.service.BeneficiarioService;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoFullDto;


@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

     //attributes
     private BeneficiarioService beneficiarioService;

     //constructors
     
     public BeneficiarioController(BeneficiarioService beneficiarioService){
          this.beneficiarioService = beneficiarioService;
     }

     // ******************************************** Criar Beneficiário ********************************************
     @PostMapping("")
     public  ResponseEntity<BeneficiarioFullDto> cadastrarBeneficiario(@RequestBody BeneficiarioCreateDto beneficiarioCreateDto){

         BeneficiarioFullDto beneficiarioFullDto = this.beneficiarioService.cadastrarBeneficiario(beneficiarioCreateDto);

          return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(beneficiarioFullDto);
 
     }

     // *************************************************** Listar todos  ***************************************************
     @GetMapping("")
     public  ResponseEntity<List<BeneficiarioWithoutDocumentos>> listarTodos(){
          return ResponseEntity
               .status(HttpStatus.OK)
               .body(this.beneficiarioService.listarTodos());
     }

     // *************************************************** Listar todos os documentos de um beneficiario  ***************************************************
     @GetMapping("{id}/documentos")
     public  ResponseEntity<List<DocumentoFullDto>> listarTodosDocumentos( @PathVariable(name = "id")  Long beneficiarioId){
          return ResponseEntity
               .status(HttpStatus.OK)
               .body(this.beneficiarioService.listarTodosDocumentos(beneficiarioId));
 
     }

     // ******************************************** Alterar Beneficiário ********************************************
     @PutMapping("{id}")
     public ResponseEntity<BeneficiarioFullDto> atualizarBeneficiario(@PathVariable(value = "id") Long beneficiarioId, @RequestBody BeneficiarioCreateDto dto){

         BeneficiarioFullDto beneficiarioFullDto = this.beneficiarioService.atualizarBeneficiario(beneficiarioId, dto);

          return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(beneficiarioFullDto);
 
     }

}
