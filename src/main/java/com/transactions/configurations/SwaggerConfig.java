package com.transactions.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${version}")
    private String version;

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(this.info());
    }

    private Info info() {
        return new Info()
                .title("Transactions Service Operations")
                .description("This service is responsible for management transactions")
                .version(this.version);
    }
}