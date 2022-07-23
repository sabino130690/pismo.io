package com.pismo.io.evaluation.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Pismo.io - APIs Transacionais (Pismo.io evaluation test)",
        version = "1.0.0",
        description = "Micro serviço responsável por realizar a criação de conta, consulta e envio de transações bancarias"
))
public class OpenApiConfiguration {

}