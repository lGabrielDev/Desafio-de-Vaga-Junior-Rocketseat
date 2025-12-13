package br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentoCreateDto {
     
     //attributes
     private String tipoDocumento;
     private String numeroDocumento;
}