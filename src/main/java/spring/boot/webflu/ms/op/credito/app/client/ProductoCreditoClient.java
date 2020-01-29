package spring.boot.webflu.ms.op.credito.app.client;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.dto.CreditAccount;

@Service
public class ProductoCreditoClient {

	private static final Logger log = LoggerFactory.getLogger(CreditAccount.class);
	
	@Autowired
	@Qualifier("productoCredito")
	private WebClient productoCreditoClient;
	
	
	
//	Mono<CreditAccount> oper=WebClient
//			.builder()
//			.baseUrl("http://gateway:8099/productos_creditos/api/ProductoCredito/")
//			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
//			.build().put().uri("/pago/"+operacion.getCuenta_origen()+"/"+operacion.getMontoPago()).retrieve()
//			.bodyToMono(CreditAccount.class)
//			.log();
	
	
	//deposito de la cuenta de credito
	public Mono<CreditAccount> despositoBancario(String cuenta_origen,Double monto) {
		
		log.info("Actualizando: cuenta origen --> deposito credito : "+ cuenta_origen + " monto : " + monto);
	
		Map<String, String> pathVariable = new HashMap<String,String>();
		pathVariable.put("numero_cuenta",cuenta_origen);
		pathVariable.put("monto",Double.toString(monto));//Casteamos la cantidad para envia en el map
		
		return productoCreditoClient
					.put()
				   .uri("/pago/{monto}/{numero_cuenta}/",pathVariable)
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .bodyToMono(CreditAccount.class).log();				
	}
	
	
//	Mono<CreditAccount> oper=WebClient
//			.builder()
//			.baseUrl("http://gateway:8099/productos_creditos/api/ProductoCredito/")
//			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
//			.build().put().uri("/consumo/"+operacion.getCuenta_origen()+"/"+operacion.getMontoPago()).retrieve()
//			.bodyToMono(CreditAccount.class)
//			.log();
	
	public Mono<CreditAccount> retiroBancario(String cuenta_origen,Double monto) {
		
		log.info("Actualizando: cuenta origen --> deposito credito : "+ cuenta_origen + " monto : " + monto);
	
		Map<String, String> pathVariable = new HashMap<String,String>();
		pathVariable.put("numero_cuenta",cuenta_origen);
		pathVariable.put("monto",Double.toString(monto));//Casteamos la cantidad para envia en el map
		
		return productoCreditoClient
					.put()
				   .uri("/consumo/{monto}/{numero_cuenta}/",pathVariable)
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .bodyToMono(CreditAccount.class).log();		
		
	}
	
	
}
