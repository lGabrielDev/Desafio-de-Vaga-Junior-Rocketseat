package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs.DocumentoFullDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ //ordenando getters na resposta da requisicao
    "id",
     "nome",
    "telefone",
    "dataNascimento",
    "dataInclusao",
    "dataUltimaAtualizacao",
    "documentos" // O último da sua lista de declaração
})
public class BeneficiarioFullDto {

     //attributes
     private Long id;
     private String nome;
     private String telefone;
     @JsonFormat(pattern = "dd-MM-yyyy")
     private LocalDate dataNascimento;
     private LocalDateTime dataInclusao;
     private LocalDateTime dataUltimaAtualizacao;
     List<DocumentoFullDto> documentos;
}
