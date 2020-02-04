package spring.boot.webflu.ms.op.credito.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import spring.boot.webflu.ms.op.credito.app.documents.OperacionCuentaCredito;



public interface OperacionCreditoDao extends ReactiveMongoRepository<OperacionCuentaCredito, String> {

	
	/*@Query("{ 'dni' : ?0 , 'idTipo : ?0'}")
	Flux<Operacion> viewDniCliente2(String dni, String idTipo);*/
	
//	@Query("{ 'dni' : ?0 }")
//	Flux<OperacionCuentaCredito> viewDniCliente(String dni);
	
	Flux<OperacionCuentaCredito> findByDni(String dni);

	@Query("{ 'dni' : ?0, cuenta_origen: ?1}")
	Flux<OperacionCuentaCredito> consultarMovimientos(String dni, String numTarjeta);

	
	
}
