package spring.boot.webflu.ms.op.credito.app.documents;

import javax.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection ="Operaciones")
public class OperacionCuentaCredito {

	@NotEmpty
	private String dni;
	@NotEmpty
	private String cuenta_origen;
	@NotEmpty
	private String cuenta_destino;
	@NotEmpty
	private String fechaOperacion;
	@NotEmpty
	private TipoOperacionCredito tipoOperacion;
	@NotEmpty
	private double montoPago;
	
	private String numeroCuenta;
	
	@NotEmpty
	private String codigoBanco;//codigo_bancario
	
	
	public OperacionCuentaCredito(String dni,String cuenta_origen,String cuenta_destino,String fechaOperacion,
			TipoOperacionCredito tipoOperacion,double montoPago,String codigoBanco) {
		this.dni = dni;
		this.cuenta_origen = cuenta_origen;
		this.cuenta_destino = cuenta_destino;
		this.fechaOperacion = fechaOperacion;
		this.tipoOperacion = tipoOperacion;
		this.montoPago = montoPago;
		this.codigoBanco = codigoBanco;
	}


	public OperacionCuentaCredito() {
		
	}

	//private tipoProducto tipoCliente;
	
	
	
	
}










