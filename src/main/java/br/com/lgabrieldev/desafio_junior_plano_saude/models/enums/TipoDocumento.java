package br.com.lgabrieldev.desafio_junior_plano_saude.models.enums;

import lombok.Getter;

@Getter
public enum TipoDocumento {
     //CONSTANTS
     RG ("RG", 9),
     CPF("CPF", 11 ),
     CNH("CNH",11);

     //attributes
     private String tipo;
     private Integer maxCaracteres;
     
     //constructors
     private TipoDocumento(String tipo, Integer maxCharaceres){
          this.tipo = tipo;
          this.maxCaracteres = maxCharaceres;
     }
}