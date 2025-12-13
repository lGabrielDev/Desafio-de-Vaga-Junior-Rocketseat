package br.com.lgabrieldev.desafio_junior_plano_saude.models.documento;

import java.time.LocalDateTime;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.enums.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "documento")
public class Documento {
    
     //attributes
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Setter(AccessLevel.NONE)
     private Long id;

     @Enumerated(EnumType.STRING)
     private TipoDocumento tipoDocumento;

     private String numeroDocumento;

     @Column(name = "data_inclusao")
     private LocalDateTime dataInclusao;

     @Column(name = "data_ultima_atualizacao")
     private LocalDateTime dataUltimaAtualizacao;

     @ManyToOne(targetEntity = Beneficiario.class)
     @JoinColumn(name = "beneficiario_id")
     private Beneficiario beneficiario;


     //constructors
     public Documento(){
          this.dataInclusao = LocalDateTime.now();
          this.dataUltimaAtualizacao = LocalDateTime.now();
     }
}

