package com.raj.Bank_App.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Banking application API",
                description = "API's for storing data's of Banking Application",
                version = "1.0.0",
                contact = @Contact(
                        name = "Jeshwanth"
                )

        )
)
public class ApplicationConfig {
}
