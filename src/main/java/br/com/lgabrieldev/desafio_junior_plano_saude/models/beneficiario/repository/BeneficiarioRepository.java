package br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.Beneficiario;


public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
     
     @Query(value = "SELECT b FROM Beneficiario b ORDER BY b.id ASC")
     public List<Beneficiario> findAll();


     @Query(value = "SELECT b FROM Beneficiario b WHERE  b.id = :id")
     public Optional<Beneficiario> findById(Long id);
}
