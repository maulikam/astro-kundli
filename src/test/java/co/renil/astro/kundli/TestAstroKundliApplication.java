package co.renil.astro.kundli;

import org.springframework.boot.SpringApplication;

public class TestAstroKundliApplication {

	public static void main(String[] args) {
		SpringApplication.from(AstroKundliApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
