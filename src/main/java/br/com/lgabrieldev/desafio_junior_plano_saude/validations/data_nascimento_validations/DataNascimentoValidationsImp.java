package br.com.lgabrieldev.desafio_junior_plano_saude.validations.data_nascimento_validations;
import java.time.LocalDate;

public interface DataNascimentoValidationsImp {

     public Boolean dataNascimentoNaoPodeSerNull(LocalDate data);
     public Boolean dataNascimentoMaior18Anos(LocalDate data);

     public Boolean dataNascimentoTudoCerto(LocalDate dataNascimento);
}
