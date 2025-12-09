package br.com.lgabrieldev.desafio_junior_plano_saude.exceptions.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroPadrao {
     
     //attributes
     private final LocalDateTime timestamp = LocalDateTime.now();
    private Integer status;
    private String errorMessage;
    private String trace;

    //constructors
     public ErroPadrao(Exception e){
          this.errorMessage = e.getMessage();
          this.trace = e.getStackTrace()[0].toString();
     }

    //getters and setters
    public String getTimestamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy  HH:mm:ss");
        return dtf.format(this.timestamp);
    }
}