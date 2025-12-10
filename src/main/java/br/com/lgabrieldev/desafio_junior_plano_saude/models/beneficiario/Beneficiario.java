package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.Documento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "beneficiario")
public class Beneficiario {
       
     //attributes
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Setter(AccessLevel.NONE)
     private Long id;

     private String nome;

     private String telefone;

     @Column(name = "data_nascimento")
     private LocalDate dataNascimento;

     @Column(name = "data_inclusao")
     private LocalDateTime dataInclusao;

     @Column(name = "data_ultima_atualizacao")
     private LocalDateTime dataUltimaAtualizacao;

     @OneToMany(
          mappedBy = "beneficiario", 
          cascade = CascadeType.ALL,
          orphanRemoval = true     // ao remover da lista, o documento também é removido do banco de dados automaticamente. Ex:
                                                  // Ao utilizar beneficiario.getDocumentos().removeAll() or beneficiario.getDocumentos().remove(documentoTal) --> É feita uma query SQL automaticamente DELETE FROM Documento WHERE id = ?
     )
     List<Documento> documentos;

     //constructors
     public Beneficiario(){
          this.documentos = new ArrayList<>(); //sempre inicializar sua lista, senao dá BO
     }
}
