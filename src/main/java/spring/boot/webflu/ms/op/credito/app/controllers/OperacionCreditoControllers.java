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

import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.OperacionCuentaCredito;
import spring.boot.webflu.ms.op.credito.app.service.OperacionCreditoService;

@RequestMapping("/api/OperCuentasCreditos")
@RestController
public class OperacionCreditoControllers {

	@Autowired
	private OperacionCreditoService operacionService;
	
	@ApiOperation(value = "LISTAR OP DE CREDITO", notes="")
	@GetMapping
	public Mono<ResponseEntity<Flux<OperacionCuentaCredito>>> findAll() {
		return Mono.just(
				ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(operacionService.findAllOperacion())
		);
	}
	
	@ApiOperation(value = "BUSCAR POR ID", notes="")
	@GetMapping("/{id}")
	public Mono<ResponseEntity<OperacionCuentaCredito>> viewId(@PathVariable String id) {
		return operacionService.findByIdOperacion(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "ACTUALIZAR OP-CREDITO", notes="")
	@PutMapping
	public Mono<OperacionCuentaCredito> updateProducto(@RequestBody OperacionCuentaCredito oper) {
		System.out.println(oper.toString());
		return operacionService.saveOperacionCredito(oper);
	}

	@ApiOperation(value = "REGISTRO DE LAS OPERACIONES DE CREDITO", notes="")
	@PostMapping
	public Mono<OperacionCuentaCredito> operacionCredito(@RequestBody OperacionCuentaCredito oper) {
		return operacionService.saveOperacionCredito(oper);
	}

	@ApiOperation(value = "BUSCAR POR OP-CREDITO POR DOCUEMENTO", notes="")
	@GetMapping("/doc/{dni}")
	public Flux<OperacionCuentaCredito> operacionCreditoCliente(@PathVariable String dni) {
		Flux<OperacionCuentaCredito> operacion = operacionService.findAllByIdOperacionDniCliente(dni);
		return operacion;
	}
	
	@ApiOperation(value = "TRANSACCIN CONSUMO - MS-CREDITO", notes="")
	@PutMapping("/consumo")
	public Mono<OperacionCuentaCredito> operacionRetiro(@RequestBody OperacionCuentaCredito oper) {
		
		return operacionService.saveOperacionRetiro(oper);
	}

	@ApiOperation(value = "TRANSACCIN PAGO - MS-CREDITO", notes="")
	@PostMapping("/pago")
	public Mono<OperacionCuentaCredito> operacionDeposito(@RequestBody OperacionCuentaCredito producto) {
		
		System.out.println("codigo banco : " + producto.getCodigoBanco());
		System.out.println("numero de cuenta : " +  producto.getCuenta_origen());
		System.out.println("numero de cuenta : " +  producto.getCuenta_destino());
		System.out.println("numero de cuenta : " + producto.getNumeroCuenta());
		
		//System.out.println(producto.toString());
		return operacionService.saveOperacionDeposito(producto);
	}

	@ApiOperation(value = "REGISTRAR TODOS LOS MOVIMIENTOS DE CREDITO", notes="")
	@GetMapping("/movimientoBancoCredito/{dni}/{num_cuenta}")
	public Flux<OperacionCuentaCredito> transaccionesBancarias(@PathVariable String dni, @PathVariable String num_cuenta) {
		Flux<OperacionCuentaCredito> oper = operacionService.transaccionMovimiento(dni, num_cuenta);
		return oper;
	}
	
}



