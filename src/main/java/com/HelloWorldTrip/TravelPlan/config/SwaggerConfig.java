package com.HelloWorldTrip.TravelPlan.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "[HTW] TravelPlan API", version = "1.0", description = "API documentation for HelloWorldTrip application"))
public class SwaggerConfig {
}
