package spring.boot.webflu.ms.op.credito.app.client;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.dto.CuentaCredito;

@Service
public class ProductoCreditoClient {

	private static final Logger log = LoggerFactory.getLogger(CuentaCredito.class);
	
	@Autowired
	@Qualifier("productoCredito")
	private WebClient productoCreditoClient;

	//DEPOSITO A UNA CUENTA DE CREDITO
	public Mono<CuentaCredito> despositoBancarioCredito(String cuenta_origen,Double monto,String codigo_bancario) {
		
		log.info("Actualizando: cuenta origen --> deposito credito : "+ cuenta_origen + " monto : " + monto + " cod_bancario" + codigo_bancario);
	
		Map<String, String> pathVariable = new HashMap<String,String>();
		pathVariable.put("numero_cuenta",cuenta_origen);
		pathVariable.put("monto",Double.toString(monto));
		pathVariable.put("codigo_bancario",codigo_bancario);
		
		return productoCreditoClient
					.put()
				   .uri("/pago/{numero_cuenta}/{monto}/{codigo_bancario}",pathVariable)
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .bodyToMono(CuentaCredito.class).log();				
	}
	
	
	public Mono<CuentaCredito> retiroBancarioCredito(String cuenta_origen,Double monto,String codigo_bancario) {
		
		log.info("Actualizando: cuenta origen --> deposito credito : "+ cuenta_origen + " monto : " + monto);
	
		Map<String, String> pathVariable = new HashMap<String,String>();
		pathVariable.put("numero_cuenta",cuenta_origen);
		pathVariable.put("monto",Double.toString(monto));//Casteamos la cantidad para envia en el map
		pathVariable.put("codigo_bancario",codigo_bancario);
		
		return productoCreditoClient
					.put()
				   .uri("/consumo/{monto}/{numero_cuenta}/{codigo_bancario}",pathVariable)
				   .accept(MediaType.APPLICATION_JSON)
				   .contentType(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .bodyToMono(CuentaCredito.class).log();		
		
	}
	
	
}
