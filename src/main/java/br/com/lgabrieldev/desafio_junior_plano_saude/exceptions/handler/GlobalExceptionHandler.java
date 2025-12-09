package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioApenasPodeTer1DocumentoPorTipoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.BeneficiarioDeveSerMaiorDeIdadeException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.FormatacaoDeDataIncorretaException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.beneficiario_exceptions.NaoFoiEnviadoNenhumDocumentoParaCadastroException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.DocumentoJaExisteNoBancoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.DocumentosIguaisException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.documentos_exceptions.TipoDocumentoNaoEncontradoException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoDeveTerApenasNumerosException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoNaoPodeSerNullException;
import br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.general_exceptions.CampoTemMuitosCharactersException;

@ControllerAdvice
public class GlobalExceptionHandler {
     

     // // *********************************** beneficiario exceptions ***********************************
     @ExceptionHandler({
          BeneficiarioApenasPodeTer1DocumentoPorTipoException.class,
          BeneficiarioDeveSerMaiorDeIdadeException.class,
          NaoFoiEnviadoNenhumDocumentoParaCadastroException.class,
          DocumentoJaExisteNoBancoException.class,
          DocumentosIguaisException.class,
          TipoDocumentoNaoEncontradoException.class,
          CampoDeveTerApenasNumerosException.class,
          CampoNaoPodeSerNullException.class,
          CampoTemMuitosCharactersException.class
     })
     public ResponseEntity<ErroPadrao> generalExceptionHandler (RuntimeException e){
          ErroPadrao e1 = new ErroPadrao(e);
               e1.setStatus(HttpStatus.BAD_REQUEST.value());
          return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(e1);
     }


     @ExceptionHandler(HttpMessageNotReadableException.class)
     public ResponseEntity<ErroPadrao> httpMessageNotReadableExceptionHandler (HttpMessageNotReadableException e){
          ErroPadrao e1 = new ErroPadrao(new FormatacaoDeDataIncorretaException());
               e1.setStatus(HttpStatus.BAD_REQUEST.value());
          return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body(e1);
     }



     // // *********************************** beneficiario exceptions ***********************************
     // @ExceptionHandler(BeneficiarioApenasPodeTer1DocumentoPorTipoException.class)
     // public ResponseEntity<ErroPadrao> generalExceptionHandler (BeneficiarioApenasPodeTer1DocumentoPorTipoException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // @ExceptionHandler(BeneficiarioDeveSerMaiorDeIdadeException.class)
     // public ResponseEntity<ErroPadrao> beneficiarioDeveSerMaiorDeIdadeExceptionHandler (BeneficiarioDeveSerMaiorDeIdadeException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // @ExceptionHandler(HttpMessageNotReadableException.class)
     // public ResponseEntity<ErroPadrao> httpMessageNotReadableExceptionHandler (HttpMessageNotReadableException e){
     //      ErroPadrao e1 = new ErroPadrao(new FormatacaoDeDataIncorretaException());
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // @ExceptionHandler(NaoFoiEnviadoNenhumDocumentoParaCadastroException.class)
     // public ResponseEntity<ErroPadrao> naoFoiEnviadoNenhumDocumentoParaCadastroExceptionHandler (NaoFoiEnviadoNenhumDocumentoParaCadastroException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // // *********************************** documentos exceptions ***********************************
     // @ExceptionHandler(DocumentoJaExisteNoBancoException.class)
     // public ResponseEntity<ErroPadrao> documentoJaExisteNoBancoExceptionHandler (DocumentoJaExisteNoBancoException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // @ExceptionHandler(DocumentosIguaisException.class)
     // public ResponseEntity<ErroPadrao> documentosIguaisExceptionHandler (DocumentosIguaisException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // @ExceptionHandler(TipoDocumentoNaoEncontradoException.class)
     // public ResponseEntity<ErroPadrao> tipoDocumentoNaoEncontradoExceptionHandler (TipoDocumentoNaoEncontradoException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // // *********************************** general exceptions ***********************************

     // @ExceptionHandler(CampoDeveTerApenasNumerosException.class)
     // public ResponseEntity<ErroPadrao> campoDeveTerApenasNumerosExceptionHandler (CampoDeveTerApenasNumerosException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }

     // @ExceptionHandler(CampoNaoPodeSerNullException.class)
     // public ResponseEntity<ErroPadrao> campoNaoPodeSerNullExceptionHandler (CampoNaoPodeSerNullException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }
     
     // @ExceptionHandler(CampoTemMuitosCharactersException.class)
     // public ResponseEntity<ErroPadrao> campoTemMuitosCharactersExceptionHandler (CampoTemMuitosCharactersException e){
     //      ErroPadrao e1 = new ErroPadrao(e);
     //           e1.setStatus(HttpStatus.BAD_REQUEST.value());
     //      return ResponseEntity
     //           .status(HttpStatus.BAD_REQUEST)
     //           .body(e1);
     // }
}