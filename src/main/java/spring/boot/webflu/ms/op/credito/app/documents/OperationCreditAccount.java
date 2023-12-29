package spring.boot.webflu.ms.op.credito.app.documents;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection ="Operaciones")
public class OperationCreditAccount {

	@NotEmpty
	private String dni;
	@NotEmpty
	private String cuenta_origen;
	@NotEmpty
	private String cuenta_destino;
	@NotEmpty
	private String fechaOperacion;
	@NotEmpty
	private TypeOperation tipoOperacion;
	@NotEmpty
	private double montoPago;
	
	
	public OperationCreditAccount(String dni,String cuenta_origen,String cuenta_destino,String fechaOperacion,
			TypeOperation tipoOperacion,double montoPago) {
		this.dni = dni;
		this.cuenta_origen = cuenta_origen;
		this.cuenta_destino = cuenta_destino;
		this.fechaOperacion = fechaOperacion;
		this.tipoOperacion = tipoOperacion;
		this.montoPago = montoPago;
	}


	public OperationCreditAccount() {
		
	}

	//private tipoProducto tipoCliente;
	
	
	
	
}










