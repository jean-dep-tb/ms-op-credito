package spring.boot.webflu.ms.op.credito.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import spring.boot.webflu.ms.op.credito.app.documents.OperacionCuentaCredito;
import spring.boot.webflu.ms.op.credito.app.documents.TipoOperacionCredito;
import spring.boot.webflu.ms.op.credito.app.service.OperacionCreditoService;
import spring.boot.webflu.ms.op.credito.app.service.impl.TipoOperacionCreditoServiceImpl;

@SpringBootApplication
public class SpringBootWebfluMsOpCreditoApplication implements CommandLineRunner{
	
	@Autowired
	private OperacionCreditoService operacionService;
	
	@Autowired
	private TipoOperacionCreditoServiceImpl tipoOperacionServiceImpl;
	
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
		
		TipoOperacionCredito deposito = new TipoOperacionCredito("1","Deposito");
		TipoOperacionCredito retiro = new TipoOperacionCredito("2","Retiro");
		
		Flux.just(deposito,retiro)
		.flatMap(tipoOperacionServiceImpl::saveTipoProducto)
		.doOnNext(c -> {
			log.info("Tipo de producto creado: " +  c.getDescripcion() + ", Id: " + c.getIdTipo());
		}).thenMany(					
				Flux.just(
					
						new OperacionCuentaCredito("47305710","100001","100003", "2019-01-28",deposito,9000.0,"bcp"),
						new OperacionCuentaCredito("47305710","100002","100004", "2019-01-28",retiro,8000.0,"bbva")
						
						)					
					.flatMap(operacion -> {
						return operacionService.saveOperacionCredito(operacion);
					})					
				).subscribe(operacion -> log.info("Insert: " + operacion.getCuenta_destino() 
					+ " " + operacion.getCuenta_origen() + " " + operacion.getMontoPago()));
		
	}

}
