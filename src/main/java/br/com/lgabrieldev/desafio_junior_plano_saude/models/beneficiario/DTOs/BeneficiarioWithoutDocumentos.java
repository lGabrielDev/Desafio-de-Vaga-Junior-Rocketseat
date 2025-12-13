package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    "quantidadeDocumentos"
})
public class BeneficiarioWithoutDocumentos {

     //attributes
     private Long id;
     private String nome;
     private String telefone;
     @JsonFormat(pattern = "dd-MM-yyyy")
     private LocalDate dataNascimento;
     @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
     private LocalDateTime dataInclusao;
     @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
     private LocalDateTime dataUltimaAtualizacao;
     private Integer quantidadeDocumentos;
}


