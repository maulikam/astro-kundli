package co.renil.astro.kundli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AstroKundliApplication {

    public static void main(String[] args) {
        SpringApplication.run(AstroKundliApplication.class, args);
    }

}
