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
public class OperacionControllers {

	@Autowired
	private OperacionService operacionService;

	@Autowired
	private TipoOperacionService tipoOperacionService;

	//Muestra todos las cuentas bancarias existentes
	@GetMapping
	public Mono<ResponseEntity<Flux<OperationCreditAccount>>> findAll() {
		return Mono.just(
				ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(operacionService.findAllOperacion())
		);
	}
	
	//Filtra todas cuentas bancarias por id
	@GetMapping("/{id}")
	public Mono<ResponseEntity<OperationCreditAccount>> viewId(@PathVariable String id) {
		return operacionService.findByIdOperacion(id)
				.map(p -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	//actualiza operacion con el numero de id, si no existe el di vuelve a crearlo
	@PutMapping
	public Mono<OperationCreditAccount> updateProducto(@RequestBody OperationCreditAccount oper) {
		System.out.println(oper.toString());
		return operacionService.saveOperacion(oper);
	}
	
	/*Guarda una operacion bancaria */
	@PostMapping
	public Mono<OperationCreditAccount> guardarOperacion(@RequestBody OperationCreditAccount oper) {
		return operacionService.saveOperacion(oper);
	}

	//Muestra todas las Operaciones bancarias por el numero de dni del cliente
	@GetMapping("/dni/{dni}")
	public Flux<OperationCreditAccount> listOperacionByDicliente(@PathVariable String dni) {
		Flux<OperationCreditAccount> operacion = operacionService.findAllByIdOperacionDniCliente(dni);
		return operacion;
	}
	
	//Realiza una Transaccion(RETIROS) 
	//guardando en el microservicio operaciones(movimientos) 
	// Y Actualiza el saldo de la tarjeta 
	@PostMapping("/consumo")
	public Mono<OperationCreditAccount> saveOperacionRetiro(@RequestBody OperationCreditAccount oper) {
		//System.out.println(producto.toString());
		return operacionService.saveOperacionRetiro(oper);
	}

	//Realiza una Transaccion(Deposito) 
	//guardando en el microservicio operaciones(movimientos) 
	// Y Actualiza el saldo de la tarjeta 
	@PostMapping("/pago")
	public Mono<OperationCreditAccount> saveOperacionDeposito(@RequestBody OperationCreditAccount producto) {
		//System.out.println(producto.toString());
		return operacionService.saveOperacionDeposito(producto);
	}

	//Muestra todos los movimientos bancarios por cliente y numero tarjeta(cuentas credito)
	@GetMapping("/MovimientosBancarios/{dni}/{num_cuenta}")
	public Flux<OperationCreditAccount> movimientosBancarios(@PathVariable String dni, @PathVariable String num_cuenta) {
		Flux<OperationCreditAccount> oper = operacionService.consultaMovimientos(dni, num_cuenta);
		return oper;
	}
}



