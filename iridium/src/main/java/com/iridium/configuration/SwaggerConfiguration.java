package com.iridium.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    servers = @Server(url = "${springdoc.swagger-ui.server.name:/}", description = "test"),
    security = @SecurityRequirement(name = "test")
)
@SecurityScheme(
    type = SecuritySchemeType.APIKEY,
    name = "test",
    in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfiguration {
}
