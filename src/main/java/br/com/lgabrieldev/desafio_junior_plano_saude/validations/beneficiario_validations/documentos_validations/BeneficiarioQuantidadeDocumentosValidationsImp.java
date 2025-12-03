package br.com.lgabrieldev.desafio_junior_plano_saude.validations.beneficiario_validations.documentos_validations;

import br.com.lgabrieldev.desafio_junior_plano_saude.models.beneficiario.DTOs.BeneficiarioCreateDto;

public interface BeneficiarioQuantidadeDocumentosValidationsImp {

     public Boolean  peloMenosUmDocumento(BeneficiarioCreateDto beneficiarioCreateDto);
     public Boolean  quantidadeDocumentosTudoCerto(BeneficiarioCreateDto beneficiarioCreateDto);
}
