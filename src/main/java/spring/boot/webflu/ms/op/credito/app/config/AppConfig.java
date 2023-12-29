package spring.boot.webflu.ms.op.credito.app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	@Bean
	@Qualifier("productoCredito")
	public WebClient registrarWebClient() {
		return WebClient.create("http://localhost:8022/api/ProductoCredito");
	}
	
}
