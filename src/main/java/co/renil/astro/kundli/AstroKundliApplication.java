package co.renil.astro.kundli;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Kundli Astrology API", version = "1.0", description = "API for generating kundli charts and related astrology calculations"))

public class AstroKundliApplication {

    public static void main(String[] args) {
        SpringApplication.run(AstroKundliApplication.class, args);
    }

}
