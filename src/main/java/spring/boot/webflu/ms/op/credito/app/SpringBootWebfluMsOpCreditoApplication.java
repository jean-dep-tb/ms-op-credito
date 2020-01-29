package spring.boot.webflu.ms.op.credito.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import reactor.core.publisher.Flux;
import spring.boot.webflu.ms.op.credito.app.documents.OperationCreditAccount;
import spring.boot.webflu.ms.op.credito.app.documents.TypeOperation;
import spring.boot.webflu.ms.op.credito.app.impl.TipoOperacionServiceImpl;
import spring.boot.webflu.ms.op.credito.app.service.OperacionService;

@EnableEurekaClient
@SpringBootApplication
public class SpringBootWebfluMsOpCreditoApplication implements CommandLineRunner{
	
	@Autowired
	private OperacionService operacionService;
	
	@Autowired
	private TipoOperacionServiceImpl tipoOperacionServiceImpl;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(SpringBootWebfluMsOpCreditoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluMsOpCreditoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		mongoTemplate.dropCollection("Operaciones").subscribe();
		mongoTemplate.dropCollection("tipoOperacion").subscribe();
		
		TypeOperation deposito = new TypeOperation("1","Deposito");
		TypeOperation retiro = new TypeOperation("2","Retiro");
		
		Flux.just(deposito,retiro)
		.flatMap(tipoOperacionServiceImpl::saveTipoProducto)
		.doOnNext(c -> {
			log.info("Tipo de producto creado: " +  c.getDescripcion() + ", Id: " + c.getIdTipo());
		}).thenMany(					
				Flux.just(
					
						new OperationCreditAccount("47305710","100001","100003", "2019-01-28",deposito,9000.0),
						new OperationCreditAccount("47305710","100002","100004", "2019-01-28",retiro,8000.0)
						
						)					
					.flatMap(operacion -> {
						return operacionService.saveOperacion(operacion);
					})					
				).subscribe(operacion -> log.info("Insert: " + operacion.getCuenta_destino() 
					+ " " + operacion.getCuenta_origen() + " " + operacion.getMontoPago()));
		
	}

}
