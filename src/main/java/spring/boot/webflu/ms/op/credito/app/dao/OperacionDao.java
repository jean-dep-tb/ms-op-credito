package spring.boot.webflu.ms.op.credito.app.dao;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.OperationCreditAccount;



public interface OperacionDao extends ReactiveMongoRepository<OperationCreditAccount, String> {

	
	/*@Query("{ 'dni' : ?0 , 'idTipo : ?0'}")
	Flux<Operacion> viewDniCliente2(String dni, String idTipo);*/
	
	@Query("{ 'dni' : ?0 }")
	Flux<OperationCreditAccount> viewDniCliente(String dni);

	@Query("{ 'dni' : ?0, cuenta_origen: ?1}")
	Flux<OperationCreditAccount> consultarMovimientos(String dni, String numTarjeta);

	
	
}
