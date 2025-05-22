package dev.ApiLavacar.Nego;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiLavacarNego {

	private static final Logger logger = LoggerFactory.getLogger(ApiLavacarNego.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(ApiLavacarNego.class, args);
			logger.info("🚀 API iniciada com sucesso!");
		} catch (Exception e) {
			logger.error("❌ Erro ao iniciar a API", e);
		}
	}
}
