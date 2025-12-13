package br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs;

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
     "tipoDocumento",
     "numeroDocumento",
     "dataInclusao",
     "dataUltimaAtualizacao"
})
public class DocumentoFullDto {

     //attributes
     private Long id;
     private String tipoDocumento;
     private String numeroDocumento;
     @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
     private LocalDateTime dataInclusao;
     @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
     private LocalDateTime dataUltimaAtualizacao;
}