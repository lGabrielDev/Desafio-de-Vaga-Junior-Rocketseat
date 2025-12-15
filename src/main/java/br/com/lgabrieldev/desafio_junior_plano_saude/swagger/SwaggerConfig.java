package br.com.lgabrieldev.desafio_junior_plano_saude.swagger;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
     
      @Bean
      public OpenAPI setandoConfig(){
            return new OpenAPI()
                  .info(
                        new Info()
                              .title("Desafio Backend Java Rocketseat")
                              .description("Cadastro de beneficiários de um plano de saúde.")
                              .license(new License().name("MIT").url("https://opensource.org/license/mit"))
                              .contact(new Contact().name("lGabrielDev"))
                              .version("1.0")
                  )
                  .servers(
                        List.of(
                              new Server().url("http://localhost:8080")
                        )
                  );
      }
}