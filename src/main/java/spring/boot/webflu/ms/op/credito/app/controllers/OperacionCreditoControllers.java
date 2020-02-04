package spring.boot.webflu.ms.op.credito.app.controllers;

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
import spring.boot.webflu.ms.op.credito.app.documents.OperacionCuentaCredito;
import spring.boot.webflu.ms.op.credito.app.service.OperacionCreditoService;

@RequestMapping("/api/OperCuentasCreditos")
@RestController
public class OperacionCreditoControllers {

	@Autowired
	private OperacionCreditoService operacionService;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<OperacionCuentaCredito>>> findAll() {
		return Mono.just(
				ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(operacionService.findAllOperacion())
		);
	}
		
	@GetMapping("/{id}")
	public Mono<ResponseEntity<OperacionCuentaCredito>> viewId(@PathVariable String id) {
		return operacionService.findByIdOperacion(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	
	@PutMapping
	public Mono<OperacionCuentaCredito> updateProducto(@RequestBody OperacionCuentaCredito oper) {
		System.out.println(oper.toString());
		return operacionService.saveOperacionCredito(oper);
	}
	
	//REGISTRO DE LAS OPERACIONES DE CREDITO
	@PostMapping
	public Mono<OperacionCuentaCredito> operacionCredito(@RequestBody OperacionCuentaCredito oper) {
		return operacionService.saveOperacionCredito(oper);
	}

	
	@GetMapping("/doc/{dni}")
	public Flux<OperacionCuentaCredito> operacionCreditoCliente(@PathVariable String dni) {
		Flux<OperacionCuentaCredito> operacion = operacionService.findAllByIdOperacionDniCliente(dni);
		return operacion;
	}
	
	
	@PutMapping("/consumo")
	public Mono<OperacionCuentaCredito> operacionRetiro(@RequestBody OperacionCuentaCredito oper) {
		
		return operacionService.saveOperacionRetiro(oper);
	}

	
	@PutMapping("/pago")
	public Mono<OperacionCuentaCredito> operacionDeposito(@RequestBody OperacionCuentaCredito producto) {
		//System.out.println(producto.toString());
		return operacionService.saveOperacionDeposito(producto);
	}

	//REGISTRAR TODOS LOS MOVIMIENTOS DE CREDITO
	@GetMapping("/movimientoBancoCredito/{dni}/{num_cuenta}")
	public Flux<OperacionCuentaCredito> transaccionesBancarias(@PathVariable String dni, @PathVariable String num_cuenta) {
		Flux<OperacionCuentaCredito> oper = operacionService.transaccionMovimiento(dni, num_cuenta);
		return oper;
	}
	
}



