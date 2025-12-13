package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoCreateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BeneficiarioCreateDto {

     //attributes
     private String nome;
     private String telefone;
     @JsonFormat(pattern = "dd-MM-yyyy")
     private LocalDate dataNascimento;
     private List<DocumentoCreateDto> documentos;
}