package spring.boot.webflu.ms.op.credito.app;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.OperacionCuentaCredito;
import spring.boot.webflu.ms.op.credito.app.documents.TipoOperacionCredito;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootWebfluMsOpCreditoApplicationTests {

	@Autowired
	private WebTestClient client;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void listarCuentaCredito() {
		client.get().uri("/api/OperCuentasCreditos")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk() 
		.expectHeader().contentType(MediaType.APPLICATION_JSON) //.hasSize(2);
		.expectBodyList(OperacionCuentaCredito.class).consumeWith(response -> {
			
			List<OperacionCuentaCredito> cuentaCredito = response.getResponseBody();
			
			cuentaCredito.forEach(p -> {
				System.out.println(p.getTipoOperacion());
			});
			
			Assertions.assertThat(cuentaCredito.size() > 0).isTrue();
		});
	}
	
	@Test
	void crearCuentaCredito() {
		
		TipoOperacionCredito ticd= new TipoOperacionCredito();
		ticd.setIdTipo("1");
		ticd.setDescripcion("deposito");
		
		OperacionCuentaCredito ctCredito = new OperacionCuentaCredito();
		ctCredito.setDni("47305710");
		ctCredito.setCuenta_origen("100001");
		ctCredito.setCuenta_destino("100002");		
		ctCredito.setTipoOperacion(ticd);
		ctCredito.setMontoPago(3000.0);
		ctCredito.setCodigoBanco("bcp");
		
		client.post()
		.uri("/api/OperCuentasCreditos")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(ctCredito), OperacionCuentaCredito.class)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(OperacionCuentaCredito.class)
		.consumeWith(response -> {
			OperacionCuentaCredito b = response.getResponseBody();
			Assertions.assertThat(b.getDni()).isNotEmpty().isEqualTo("47305710");
			Assertions.assertThat(b.getCuenta_origen()).isNotEmpty().isEqualTo("100001");
			Assertions.assertThat(b.getCuenta_destino()).isNotEmpty().isEqualTo("100002");
			Assertions.assertThat(b.getTipoOperacion().getDescripcion()).isNotEmpty().isEqualTo("deposito");
			Assertions.assertThat(b.getMontoPago()).isEqualTo(3000.0);
			Assertions.assertThat(b.getCodigoBanco()).isNotEmpty().isEqualTo("bcp");
		});
	}
	
	
}
