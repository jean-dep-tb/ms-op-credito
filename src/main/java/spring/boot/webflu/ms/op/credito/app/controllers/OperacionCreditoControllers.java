package spring.boot.webflu.ms.op.credito.app.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.OperationCreditAccount;
import spring.boot.webflu.ms.op.credito.app.service.OperacionService;
import spring.boot.webflu.ms.op.credito.app.service.TipoOperacionService;

@RequestMapping("/api/OperCuentasCreditos")
@RestController
public class OperacionCreditoControllers {

	@Autowired
	private OperacionService operacionService;

	@Autowired
	private TipoOperacionService tipoOperacionService;

	
	@GetMapping
	public Mono<ResponseEntity<Flux<OperationCreditAccount>>> findAll() {
		return Mono.just(
				ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(operacionService.findAllOperacion())
		);
	}
	
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<OperationCreditAccount>> viewId(@PathVariable String id) {
		return operacionService.findByIdOperacion(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	
	@PutMapping
	public Mono<OperationCreditAccount> updateProducto(@RequestBody OperationCreditAccount oper) {
		System.out.println(oper.toString());
		return operacionService.saveOperacionCredito(oper);
	}
	
	
	@PostMapping
	public Mono<OperationCreditAccount> guardarOperacionCredito(@RequestBody OperationCreditAccount oper) {
		return operacionService.saveOperacionCredito(oper);
	}

	
	@GetMapping("/dni/{dni}")
	public Flux<OperationCreditAccount> operacionCliente(@PathVariable String dni) {
		Flux<OperationCreditAccount> operacion = operacionService.findAllByIdOperacionDniCliente(dni);
		return operacion;
	}
	
	
	@PostMapping("/consumo")
	public Mono<OperationCreditAccount> guardarOperacionRetiro(@RequestBody OperationCreditAccount oper) {
		//System.out.println(producto.toString());
		return operacionService.saveOperacionRetiro(oper);
	}

	
	@PostMapping("/pago")
	public Mono<OperationCreditAccount> guardarOperacionDeposito(@RequestBody OperationCreditAccount producto) {
		//System.out.println(producto.toString());
		return operacionService.saveOperacionDeposito(producto);
	}

	
	@GetMapping("/MovimientosBancarios/{dni}/{num_cuenta}")
	public Flux<OperationCreditAccount> transaccionesBancarias(@PathVariable String dni, @PathVariable String num_cuenta) {
		Flux<OperationCreditAccount> oper = operacionService.transaccionMovimiento(dni, num_cuenta);
		return oper;
	}
	
}



