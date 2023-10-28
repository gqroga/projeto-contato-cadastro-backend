package br.com.cadastro.minsait.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApi {
    @Bean
    public OpenAPI customOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("API MINSAIT")
                        .description("API para cadastro de Pessoas e Produtos")
                        .contact(new Contact().name("Gabriel Queiroga").email("gqueirogat@gmail.com"))
                                .version("1.0.0")
                        );


    }
}
