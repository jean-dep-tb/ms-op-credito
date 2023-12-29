package spring.boot.webflu.ms.op.credito.app.service;


import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.OperacionCuentaCredito;

public interface OperacionCreditoService {

	Flux<OperacionCuentaCredito> findAllOperacion();
	
	Mono<OperacionCuentaCredito> findByIdOperacion(String id);
	
	Mono<OperacionCuentaCredito> saveOperacionCredito(OperacionCuentaCredito producto); //saveOperacion
	
	Flux<OperacionCuentaCredito> findAllByIdOperacionDniCliente(String dni);

	Flux<OperacionCuentaCredito> saveOperacionCredito(List<OperacionCuentaCredito> producto); //saveOperacionList
	
	Mono<OperacionCuentaCredito> saveOperacionRetiro(OperacionCuentaCredito producto);

	Mono<OperacionCuentaCredito> saveOperacionDeposito(OperacionCuentaCredito producto);

	Flux<OperacionCuentaCredito> transaccionMovimiento(String dni, String numTarjeta); //consultaMovimientos
}
