     package br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.repository;

     import java.util.List;
     import java.util.Optional;
     import org.springframework.data.jpa.repository.JpaRepository;
     import org.springframework.data.jpa.repository.Query;
     import br.com.lgabrieldev.desafio_junior_plano_saude.models.documento.Documento;

     public interface DocumentoRepository extends JpaRepository<Documento, Long>{

          @Query(value = "SELECT d FROM Documento d WHERE d.numeroDocumento = :numeroDocumento")
          public Optional<Documento> findDocumentoByNumeroDocumento(String numeroDocumento);

          @Query(value = "SELECT d FROM Documento d WHERE d.beneficiario.id = :beneficiarioId")
          public List<Documento> findDocumentoByBeneficiarioId(Long beneficiarioId);
     }