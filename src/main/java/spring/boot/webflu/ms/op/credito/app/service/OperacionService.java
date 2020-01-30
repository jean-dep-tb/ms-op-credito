package spring.boot.webflu.ms.op.credito.app.service;


import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.OperationCreditAccount;

public interface OperacionService {

	Flux<OperationCreditAccount> findAllOperacion();
	
	Mono<OperationCreditAccount> findByIdOperacion(String id);
	
	Mono<OperationCreditAccount> saveOperacionCredito(OperationCreditAccount producto); //saveOperacion
	
	Flux<OperationCreditAccount> findAllByIdOperacionDniCliente(String dni);

	Flux<OperationCreditAccount> saveOperacionCredito(List<OperationCreditAccount> producto); //saveOperacionList
	
	Mono<OperationCreditAccount> saveOperacionRetiro(OperationCreditAccount producto);

	Mono<OperationCreditAccount> saveOperacionDeposito(OperationCreditAccount producto);

	Flux<OperationCreditAccount> transaccionMovimiento(String dni, String numTarjeta); //consultaMovimientos
}
